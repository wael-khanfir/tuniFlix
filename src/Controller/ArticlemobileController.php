<?php

namespace App\Controller;

use App\Entity\Article;
use App\Repository\ProjectionRepository;
use App\Repository\ArticleRepository;
use phpDocumentor\Reflection\Types\Object_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

class ArticlemobileController extends AbstractController
{
    /**
     * @Route("/art", name="articlemobile")
     */
    public function index()
    {
    $article=$this->getDoctrine()->getManager()->getRepository(Article::class)->findAll();
    $serialiser=new Serializer([new ObjectNormalizer()]);
    $formatted=$serialiser->normalize($article);
    return new JsonResponse($formatted);

}
}
