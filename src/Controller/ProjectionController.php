<?php

namespace App\Controller;

use App\Entity\Projection;
use App\Form\ProjectionType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/projection")
 */
class ProjectionController extends AbstractController
{
    /**
     * @Route("/", name="projection_index", methods={"GET"})
     */
    public function index(): Response
    {
        $projections = $this->getDoctrine()
            ->getRepository(Projection::class)
            ->findAll();

        return $this->render('projection/index.html.twig', [
            'projections' => $projections,
        ]);
    }

    /**
     * @Route("/new", name="projection_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $projection = new Projection();
        $form = $this->createForm(ProjectionType::class, $projection);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($projection);
            $entityManager->flush();

            return $this->redirectToRoute('projection_index');
        }

        return $this->render('projection/new.html.twig', [
            'projection' => $projection,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="projection_show", methods={"GET"})
     */
    public function show(Projection $projection): Response
    {
        return $this->render('projection/show.html.twig', [
            'projection' => $projection,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="projection_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Projection $projection): Response
    {
        $form = $this->createForm(ProjectionType::class, $projection);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('projection_index');
        }

        return $this->render('projection/edit.html.twig', [
            'projection' => $projection,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="projection_delete", methods={"POST"})
     */
    public function delete(Request $request, Projection $projection): Response
    {
        if ($this->isCsrfTokenValid('delete'.$projection->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($projection);
            $entityManager->flush();
        }

        return $this->redirectToRoute('projection_index');
    }
}
