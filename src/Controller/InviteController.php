<?php

namespace App\Controller;

use App\Entity\Invite;
use App\Form\InviteType;
use App\Repository\InviteRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;


/**
 * @Route("/invite")
 */
class InviteController extends AbstractController
{
    /**
     * @Route("/", name="invite_index", methods={"GET"})
     * @param InviteRepository $repo
     * @return Response
     */
    public function index(InviteRepository $repo,Request $request, PaginatorInterface $paginator): Response
    {
        $invites = $repo
            ->findkolchay();
        $invites = $paginator->paginate(
            $invites, /* query NOT result */
            $request->query->getInt('page', 1),
            4
        );
        return $this->render('invite/index.html.twig', [
            'invites' => $invites,
        ]);

    }



    /**
     * @Route("/new", name="invite_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $invite = new Invite();
        $form = $this->createForm(InviteType::class, $invite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            if($file){
                $originalFilename = pathinfo($file->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL

                $fileName =  $originalFilename.'-'.uniqid().'.'.$file->guessExtension();
                try {
                    $file->move(
                        $this->getParameter('invite_directory'),
                        $fileName
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $invite->setImage($fileName);
            }
            $ff = rand(1000,9999);
            $invite->setAppointedNum($ff);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($invite);
            $entityManager->flush();

            return $this->redirectToRoute('invite_index');
        }

        return $this->render('invite/new.html.twig', [
            'invite' => $invite,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="invite_show", methods={"GET"})
     */
    public function show(Invite $invite): Response
    {
        return $this->render('invite/show.html.twig', [
            'invite' => $invite,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="invite_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Invite $invite): Response
    {
        $form = $this->createForm(InviteType::class, $invite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            if($file){
                $originalFilename = pathinfo($file->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL

                $fileName =  $originalFilename.'-'.uniqid().'.'.$file->guessExtension();
                try {
                    $file->move(
                        $this->getParameter('invite_directory'),
                        $fileName
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $invite->setImage($fileName);
            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('invite_index');
        }

        return $this->render('invite/edit.html.twig', [
            'invite' => $invite,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="invite_delete", methods={"POST"})
     */
    public function delete(Request $request, Invite $invite): Response
    {
        if ($this->isCsrfTokenValid('delete'.$invite->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($invite);
            $entityManager->flush();
        }

        return $this->redirectToRoute('invite_index');
    }

}
