<?php

namespace App\Controller;
use App\Entity\Article;
use App\Entity\Projection;
use App\Form\ProjectionType;
use App\Repository\ProgrammerFilmRepository;
use App\Repository\ProjectionRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\Loader;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/projection")
 */
class ProjectionController extends AbstractController
{
    /**
     * @Route("/", name="projection_index", methods={"GET"})
     */

        public function index(ProjectionRepository $repository ): Response
    {
        $projections=$repository->tri_id();
        return $this->render('base_projection.html.twig', [
            'projections' => $projections,
        ]);
    }



    /**
     * @Route("/new", name="projection_new", methods={"GET","POST"})
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $projection = new Projection();
        $form = $this->createForm(ProjectionType::class, $projection);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$projection->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'), $fileName);
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $projection->setImage($fileName);
            $entityManager->persist($projection);
            $entityManager->flush();
            $flashy->success('Projection created!');

            return $this->redirectToRoute('projection_index');
        }

        return $this->render('projection/add_projection.html.twig', [
            'projection' => $projection,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="projection_show", methods={"GET"})
     */
    public function show(Projection $projection): Response
    {
        return $this->render('projection/show.html.twig', [
            'projection' => $projection,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="projection_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Projection $projection): Response
    {
        $form = $this->createForm(ProjectionType::class, $projection);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$projection->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'), $fileName);
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $projection->setImage($fileName);
            $entityManager->persist($projection);
            $entityManager->flush();

            return $this->redirectToRoute('projection_index');
        }

        return $this->render('projection/updateprojection.html.twig', [
            'projection' => $projection,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="projection_delete", methods={"POST"})
     */
    public function delete(Request $request, Projection $projection): Response
    {
        if ($this->isCsrfTokenValid('delete'.$projection->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($projection);
            $entityManager->flush();
        }

        return $this->redirectToRoute('projection_index');
    }
    /**
     * @Route("/{id}/prog", name="liste_prog", methods={"GET"})
     */
 public  function listeprog(ProjectionRepository $repproj ,ProgrammerFilmRepository $repprog,$id)
 {
 $projection=$repproj->find($id);
 $programme=$repprog->recherche($projection->getId());
 return $this->render("rech.html.twig",[
     'programmer_films'=>$programme,'projection'=>$projection
 ]);
  }

    /**
     * @Route("/searchevenement ", name="searchevenement")
     */
    public function searchEvenement(Request $request, ProjectionRepository $repository, NormalizerInterface $Normalizer)
    {

        $requestString = $request->get('searchValue');
        if(strlen($requestString)==0)
        {
            $projection = $repository->findAll();
        }
        else
        {
            $projection = $repository->findProjectionByid($requestString);
        }

        $encoders = [new XmlEncoder(), new JsonEncoder()];
        $normalizers = [new ObjectNormalizer()];

        $serializer = new Serializer($normalizers, $encoders);
        $jsonContent = $serializer->serialize($projection, 'json',[
            'ignored_attributes' => ['nom'],
            'circular_reference_handler' => function ($object) {
                return $object->getId();
            }
        ]);


        $response = new Response(json_encode($jsonContent));
        $response->headers->set('Content-Type', 'application/json; charset=utf-8');

        return $response;

    }

}
