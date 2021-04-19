<?php

namespace App\Controller;

use App\Entity\Cinema;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/frontcinema")
 */
class CinemaFrontController extends AbstractController
{
    /**
     * @Route("/", name="cinemafront", methods={"GET"})
     */
    public function index(): Response
    {
        $cinemas = $this->getDoctrine()
            ->getRepository(Cinema::class)
            ->findAll();

        return $this->render('cinema/indexfront.html.twig', [
            'cinemas' => $cinemas,
        ]);
    }

}
