<?php


namespace App\Controller;

use Symfony\Component\Routing\Annotation\Route;

use App\Entity\Cinema;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;

class JsonCinemaController extends  AbstractController
{


    /******************Ajouter Cinema*****************************************/
    /**
     * @Route("/addCinema", name="add_cinema")
     * @Method("POST")
     */

    public function ajouterCinemaAction(Request $request)
    {
        $cinema = new Cinema();
        $nom = $request->query->get("nom");
        //$dateCreation= new \DateTime('now');
        $dateCreation= $request->query->get('dateCreation');
        $adresse = $request->query->get("adresse");
        $email = $request->query->get("email");
        $em = $this->getDoctrine()->getManager();


        $cinema->setNom($nom);
        $cinema->setDateCreation($dateCreation);
        $cinema->setAdresse($adresse);
        $cinema->setEmail($email);
        $cinema->setDateCreation(new \DateTime ($dateCreation));

        $em->persist($cinema);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($cinema);
        return new JsonResponse($formatted);

    }

    /******************Supprimer Cinema*****************************************/

    /**
     * @Route("/deleteCinema", name="delete_cinema")
     * @Method("DELETE")
     */

    public function deleteCinemaAction(Request $request) {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $cinema = $em->getRepository(Cinema::class)->find($id);
        if($cinema!=null ) {
            $em->remove($cinema);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Cinema a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id Cinema invalide.");


    }

    /******************Modifier Cinema*****************************************/
    /**
     * @Route("/updateCinema", name="update_cinema")
     * @Method("PUT")
     */
    public function modifierCinemaAction(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $cinema = $this->getDoctrine()->getManager()
            ->getRepository(Cinema::class)
            ->find($request->get("id"));

        $cinema->setNom($request->get("nom"));
        $cinema->setAdresse($request->get("adresse"));
        $cinema->setEmail($request->get("email"));

        $em->persist($cinema);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($cinema);
        return new JsonResponse("Cinema a ete modifiee avec success.");

    }


//mezelet tekhdemch
    /******************affichage Cinema*****************************************/

    /**
     * @Route("/displayCinemas", name="display_cinema")
     */
    public function allRecAction()
    {

        $cinema = $this->getDoctrine()->getManager()->getRepository(Cinema::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($cinema);

        return new JsonResponse($formatted);

    }


    /******************Detail Cinema*****************************************/

    /**
     * @Route("/detailCinema", name="detail_cinema")
     * @Method("GET")
     */

    //Detail Cinema
    public function detailCinemaAction(Request $request)
    {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $cinema = $this->getDoctrine()->getManager()->getRepository(Cinema::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($cinema);
        return new JsonResponse($formatted);
    }


}