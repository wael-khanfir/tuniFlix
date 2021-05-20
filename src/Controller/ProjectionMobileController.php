<?php

namespace App\Controller;
use App\Entity\Projection;
use App\Repository\ProjectionRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ProjectionMobileController extends AbstractController
{
    /**
     * @Route ("/test", name="mm")
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function mm(NormalizerInterface $Normalizer)
    {
        $repository= $this->getDoctrine()->getRepository(Projection::class);
        $projection =$repository ->findAll();
        $jsonContent= $Normalizer->normalize($projection,'json',['groups'=>'post:read']);




        return new Response(json_encode($jsonContent));
    }

}
