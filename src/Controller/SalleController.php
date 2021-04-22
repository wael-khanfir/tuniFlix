<?php

namespace App\Controller;

use App\Entity\Salle;
use App\Form\SalleType;
use App\Repository\SalleRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/salle")
 */
class SalleController extends AbstractController
{
    /**
     * @Route("/", name="salle_index", methods={"GET"})
     */
    public function index(): Response
    {
        $salles = $this->getDoctrine()
            ->getRepository(Salle::class)
            ->findAll();

        return $this->render('salle/index.html.twig', [
            'salles' => $salles,
        ]);
    }

    /**
     * @Route("/new", name="salle_new", methods={"GET","POST"})
     * @param Request $request
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $salle = new Salle();
        $form = $this->createForm(SalleType::class, $salle);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($salle);
            $entityManager->flush();

            $flashy->success('ajout avec succès', 'http://your-awesome-link.com');
            return $this->redirectToRoute('salle_index');
        }

        return $this->render('salle/new.html.twig', [
            'salle' => $salle,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="salle_show", methods={"GET"})
     */
    public function show(Salle $salle): Response
    {
        return $this->render('salle/show.html.twig', [
            'salle' => $salle,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="salle_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Salle $salle
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function edit(Request $request, Salle $salle,FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(SalleType::class, $salle);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            $flashy->primaryDark('salle modifié avec succès', 'http://your-awesome-link.com');
            return $this->redirectToRoute('salle_index');
        }

        return $this->render('salle/edit.html.twig', [
            'salle' => $salle,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="salle_delete", methods={"POST"})
     * @param Request $request
     * @param Salle $salle
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function delete(Request $request, Salle $salle,FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$salle->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($salle);
            $entityManager->flush();
        }

        $flashy->primaryDark('salle supprimée avec succés', 'http://your-awesome-link.com');
        return $this->redirectToRoute('salle_index');
    }

    /**
     * @Route("/salle/recherche", name="recherches")
     */
    public function Recherche(SalleRepository $repository, Request $request): Response
    {
        $data=$request->get('searchs');
        $salles =$repository->findBy(['nom'=>$data]);

        return $this->render('salle/index.html.twig', [
            'salles' => $salles,
        ]);
    }


    /**
     * @param SalleRepository $repository
     * @return Response
     * @Route ("/salle/ListQB" , name="triesalleqb")
     */
    function OrderByMailQB(SalleRepository $repository): Response
    {

        $salles=$repository->OrderByNomQB();
        return $this->render('salle/index.html.twig', [
            'salles' => $salles,
        ]);

    }
}
