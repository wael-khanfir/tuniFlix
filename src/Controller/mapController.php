<?php

namespace App\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class mapController extends AbstractController
{

    /**
     * @Route("/map",name="street")
     */
    public function mapAction(): Response
    {
        return $this->render('cinema/newMap.html.twig');
    }



}