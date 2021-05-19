<?php

namespace App\Controller;

use App\Entity\Feedback;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class FeedbackJsonController extends AbstractController
{
    /**
     * @Route("/feedback/json", name="feedback_json")
     */
    public function index(): Response
    {
        return $this->render('feedback_json/index.html.twig', [
            'controller_name' => 'FeedbackJsonController',
        ]);
    }

    /**
     * @Route ("/AllFeedbacks", name="AllFeedbacks")
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function AllFeedbacks(NormalizerInterface $Normalizer)
    {
        $repository= $this->getDoctrine()->getRepository(Feedback::class);
        $feedbacks =$repository ->findAll();
        $jsonContent= $Normalizer->normalize($feedbacks,'json',['groups'=>'post:read']);




        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/{id}/FeedbackId", name="FeedbackId")
     */
    public function FeedbackId(Request $request,$id,NormalizerInterface $Normalizer)
    {
        $em = $this ->getDoctrine()->getManager();
        $feedback= $em->getRepository(Feedback::class)->find($id);
        $jsonContent= $Normalizer->normalize($feedback,'json',['groups' => 'post:read']);


        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/addFeedbackJSON/new", name="addFeedbackJSON")
     */
    public function addFeedbackJSON(Request $request,NormalizerInterface $Normalizer)
    {
        $em = $this ->getDoctrine()->getManager();
        $feedback= new Feedback();
        $feedback ->setNom($request->get('nom'));
        $feedback ->setEmail($request->get('email'));
        $feedback ->setMessage($request->get('message'));
        $feedback ->setEtat(0);
        //$feedback ->setDate(new \DateTime($request->get('date')));

        $em->persist($feedback);
        $em->flush();


        $jsonContent= $Normalizer->normalize($feedback,'json',['groups' => 'post:read']);
        // return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //    'data' => $jsonContent,
        // ]);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/updateFeedbackJson/{id}", name="updateFeedbackJson")
     */
    public function updateFeedbackJson(Request $request,NormalizerInterface $Normalizer,$id)
    {
        $em = $this ->getDoctrine()->getManager();
        $feedback= $em->getRepository(Feedback::class)->find($id);
        $feedback ->setNom($request->get('nom'));
        $feedback ->setEmail($request->get('email'));
        $feedback ->setMessage($request->get('message'));
        $feedback ->setEtat($request->get('etat'));
        //$feedback ->setDate(new \DateTime($request->get('date')));


        $em->flush();


        $jsonContent= $Normalizer->normalize($feedback,'json',['groups' => 'post:read']);

        // return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //    'data' => $jsonContent,
        // ]);
        return new Response("feedback updated successfully".json_encode($jsonContent));
    }

    /**
     * @Route ("/deleteFeedbackJson/{id}", name="deleteFeedbackJSON")
     */
    public function deleteFeedbackJSON(Request $request,NormalizerInterface $Normalizer,$id)
    {
        $em = $this ->getDoctrine()->getManager();
        $feedback= $em->getRepository(Feedback::class)->find($id);
        $em->remove($feedback);
        $em->flush();


        $jsonContent= $Normalizer->normalize($feedback,'json',['groups' => 'post:read']);

        //   return $this->render('cinema_json/allCinemaJSON.html.twig', [
        //      'data' => $jsonContent,
        // ]);

        return new Response(json_encode($jsonContent));
    }

}
