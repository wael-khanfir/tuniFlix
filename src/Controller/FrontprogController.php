<?php

namespace App\Controller;

use App\Repository\ProgrammerFilmRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontprogController extends AbstractController
{
    /**
     * @Route("/frontprog", name="frontprog")
     */
    public function index(ProgrammerFilmRepository $repository): Response
    {
        $programmes=$repository->tri_date();
        return $this->render('frontprog.html.twig', [
            'programmer_films' => $programmes,
        ]);
    }

}
