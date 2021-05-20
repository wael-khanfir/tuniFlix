<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Repository\ProduitRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Bundle\FrameworkBundle\Configuration\Method;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
class JsonController extends AbstractController
{

    /******************Afficher Produit*****************************************/
    /**
     * @Route ("/AllProduits", name="AllProduits")
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function AllProduits(NormalizerInterface $Normalizer)
    {
        $repository= $this->getDoctrine()->getRepository(Produit::class);
        $produits =$repository ->findAll();
        $jsonContent= $Normalizer->normalize($produits,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }

    /*public function findAction($prix)
     {
         $tasks = $this->getDoctrine()->getManager()
             ->getRepository('Produit')
             ->find($prix);
         $serializer = new Serializer([new ObjectNormalizer()]);
         $formatted = $serializer->normalize($tasks);
         return new JsonResponse($formatted);
     }*/




}