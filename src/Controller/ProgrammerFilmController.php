<?php

namespace App\Controller;

use App\Entity\ProgrammerFilm;
use App\Form\ProgrammerFilmType;
use App\Repository\ProgrammerFilmRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/programmer/film")
 */
class ProgrammerFilmController extends AbstractController
{
    /**
     * @Route("/", name="pfilm", methods={"GET"})
     */
    public function index(ProgrammerFilmRepository $repository): Response
    {       $programmes=$repository->tri_date();
        return $this->render('programmer_film.html.twig', [
            'programmer_films' => $programmes,
        ]);

    }

    /**
     * @Route("/new", name="pfilm_new", methods={"GET","POST"})
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $programmerFilm = new ProgrammerFilm();
        $form = $this->createForm(ProgrammerFilmType::class, $programmerFilm);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($programmerFilm);
            $entityManager->flush();
            $flashy->success('Programme created!');
            return $this->redirectToRoute('pfilm');
        }

        return $this->render('programmer_film/addpfilm.html.twig', [
            'programmer_film' => $programmerFilm,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="programmer_film_show", methods={"GET"})
     */
    public function show(ProgrammerFilm $programmerFilm): Response
    {
        return $this->render('programmer_film/show.html.twig', [
            'programmer_film' => $programmerFilm,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="programmer_film_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, ProgrammerFilm $programmerFilm): Response
    {
        $form = $this->createForm(ProgrammerFilmType::class, $programmerFilm);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('pfilm');
        }

        return $this->render('programmer_film/updateprogramme.html.twig', [
            'programmer_film' => $programmerFilm,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="programmer_film_delete", methods={"POST"})
     */
    public function delete(Request $request, ProgrammerFilm $programmerFilm): Response
    {
        if ($this->isCsrfTokenValid('delete'.$programmerFilm->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($programmerFilm);
            $entityManager->flush();
        }

        return $this->redirectToRoute('pfilm');
    }


}
