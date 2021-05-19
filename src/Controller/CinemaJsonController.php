<?php

namespace App\Controller;

use App\Entity\Cinema;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class CinemaJsonController extends AbstractController
{
    /**
     * @Route("/cinema/json", name="cinema_json")
     */
    public function index(): Response
    {
        return $this->render('cinema_json/index.html.twig', [
            'controller_name' => 'CinemaJsonController',
        ]);
    }

    /**
     * @Route ("/AllCinemas", name="AllCinemas")
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function AllStudents(NormalizerInterface $Normalizer)
    {
        $repository= $this->getDoctrine()->getRepository(Cinema::class);
        $cinemas =$repository ->findAll();
        $jsonContent= $Normalizer->normalize($cinemas,'json',['groups'=>'post:read']);




        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/{id}/CinemaId", name="CinemaId")
     */
    public function CinemaId(Request $request,$id,NormalizerInterface $Normalizer)
    {
        $em = $this ->getDoctrine()->getManager();
        $cinema= $em->getRepository(Cinema::class)->find($id);
        $jsonContent= $Normalizer->normalize($cinema,'json',['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/addCinemaJSON/new", name="addCinemaJSON")
     */
    public function addCinemaJSON(Request $request,NormalizerInterface $Normalizer)
    {
        $em = $this ->getDoctrine()->getManager();
        $cinema= new Cinema();
        $cinema ->setNom($request->get('nom'));
        $cinema ->setDateCreation(new \DateTime($request->get('date_creation')));
        $cinema ->setAdresse($request->get('adresse'));
        $cinema ->setEmail($request->get('email'));
        $em->persist($cinema);
        $em->flush();


        $jsonContent= $Normalizer->normalize($cinema,'json',['groups' => 'post:read']);
        // return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //    'data' => $jsonContent,
        // ]);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/updateCinemaJson/{id}", name="updateCinemaJson")
     */
    public function updateCinemaJson(Request $request,NormalizerInterface $Normalizer,$id)
    {
        $em = $this ->getDoctrine()->getManager();
        $cinema= $em->getRepository(Cinema::class)->find($id);
        $cinema ->setNom($request->get('nom'));
        $cinema ->setDateCreation(new \DateTime($request->get('date_creation')));
        $cinema ->setAdresse($request->get('adresse'));
        $cinema ->setEmail($request->get('email'));

        $em->flush();


        $jsonContent= $Normalizer->normalize($cinema,'json',['groups' => 'post:read']);

        // return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //    'data' => $jsonContent,
        // ]);
        return new Response("cinema updated successfully".json_encode($jsonContent));
    }

    /**
     * @Route ("/deleteCinemaJson/{id}", name="deleteCinemaJSON")
     */
    public function deleteCinemaJSON(Request $request,NormalizerInterface $Normalizer,$id)
    {
        $em = $this ->getDoctrine()->getManager();
        $cinema= $em->getRepository(Cinema::class)->find($id);
        $em->remove($cinema);
        $em->flush();


        $jsonContent= $Normalizer->normalize($cinema,'json',['groups' => 'post:read']);

        //   return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //      'data' => $jsonContent,
        // ]);

        return new Response("cinema deleted successfully".json_encode($jsonContent));
    }


}
