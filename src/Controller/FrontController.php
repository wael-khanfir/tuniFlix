<?php

namespace App\Controller;

use App\Entity\Produit;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{
    /**
     * @Route("/produitfront", name="produitfront")
     */
    public function index( Request $request,PaginatorInterface $paginator): Response
    {
        $donnees = $this->getDoctrine()->getRepository(produit::class)->findAll([],
            ['created_at'=>'desc']);


        $produit = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
            4
        );
        return $this->render('produitfront.html.twig', [
            'produits' => $produit,
        ]);

    }



}
