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

    /**
     * @Route("/map2",name="street2")
     */
    public function mapAction2(): Response
    {
        return $this->render('cinema/newMap2.html.twig');
    }

    /**
     * @Route("/map3",name="street3")
     */
    public function mapAction3(): Response
    {
        return $this->render('cinema/newMap3.html.twig');
    }



}