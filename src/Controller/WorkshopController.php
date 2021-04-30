<?php

namespace App\Controller;

use App\Entity\Workshop;
use App\Form\WorkshopType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use \Twilio\Rest\Client;
/**
 * @Route("/workshop")
 */
class WorkshopController extends AbstractController
{
    private $twilio;

    public function __construct(Client $twilio) {
        $this->twilio = $twilio;

    }
    /**
     * @Route("/front", name="front", methods={"GET"})
     */
    public function front(): Response
    {
        $workshops = $this->getDoctrine()
            ->getRepository(Workshop::class)
            ->findAll();
        return $this->render('workshop/front.html.twig', [
            'workshops' => $workshops,
        ]);
    }
    /**
     * @Route("/", name="workshop_index", methods={"GET"})
     */
    public function index(): Response
    {
        $workshops = $this->getDoctrine()
            ->getRepository(Workshop::class)
            ->findAll();

        return $this->render('workshop/index.html.twig', [
            'workshops' => $workshops,
        ]);
    }

    /**
     * @Route("/new", name="workshop_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $workshop = new Workshop();
        $form = $this->createForm(WorkshopType::class, $workshop);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($workshop);
            $entityManager->flush();
            $message = $this->twilio->messages->create(
                '+21653920132', // Send text to this number
                array(
                    'from' => '+19493536339', // My Twilio phone number
                    'body' => 'Un nouveau evenement intitulé :'.$workshop->getNomevent() .'  qui débute le :'.$workshop->getDatedebut()->format('Y-M-d')  . ' rendez vous sur notre site web www.tuniflix.com .'
                )
            );

            return $this->redirectToRoute('workshop_index');
        }

        return $this->render('workshop/new.html.twig', [
            'workshop' => $workshop,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="workshop_show", methods={"GET"})
     */
    public function show(Workshop $workshop): Response
    {
        return $this->render('workshop/show.html.twig', [
            'workshop' => $workshop,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="workshop_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Workshop $workshop): Response
    {
        $form = $this->createForm(WorkshopType::class, $workshop);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('workshop_index');
        }

        return $this->render('workshop/edit.html.twig', [
            'workshop' => $workshop,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="workshop_delete", methods={"POST"})
     */
    public function delete(Request $request, Workshop $workshop): Response
    {
        if ($this->isCsrfTokenValid('delete'.$workshop->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($workshop);
            $entityManager->flush();
        }

        return $this->redirectToRoute('workshop_index');
    }

}
