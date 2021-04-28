<?php

namespace App\Controller;

use App\Entity\Invite;
use App\Form\InviteType;
use App\Repository\InviteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;


/**
 * @Route("/inv")
 */
class InvitefrontController extends AbstractController
{



    /**
     * @Route("/", name="aa", methods={"GET"})
     * @param InviteRepository $repo
     * @return Response
     */
    public function index(InviteRepository $repo): Response
    {
        $invites = $repo
            ->findkolchay();

        return $this->render('inv/inviteFront.html.twig', [
            'invites' => $invites,
        ]);
    }





}
