<?php

namespace App\Controller;

use App\Entity\Users;
use App\Form\ConnexionUserType;
use App\Form\UsersType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/users")
 */
class UsersController extends AbstractController
{
    /**
     * @Route("/logout", name="logout")
     */
    public function deconnexion(Request $request, SessionInterface $session): Response
    {
        $session->clear();


        return $this->redirectToRoute("users_connection");
    }

    /**
     * @Route("/connection", name="users_connection", methods={"GET","POST"})
     */
    public function connexion(Request $request, SessionInterface $session): Response
    {
        $user = new Users();
        $form = $this->createForm(ConnexionUserType::class, $user);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $verifuser = $this->getDoctrine()->getRepository(Users::class)->findOneBy(['email' => $user->getEmail(), 'password' => $user->getPassword()]);
            if (is_null($verifuser)) {
                return $this->redirectToRoute("users_connection");
            } else {
                if ($verifuser->getType() == "admin") {
                    $session->set("user", $verifuser);
                    return $this->redirectToRoute("users_index");


                } else {

                    $session->set("user", $verifuser);
                    return $this->render('home/acceuilfront.html.twig', [
                        'user' => $user,
                        'form' => $form->createView(),
                    ]);

                }

            }


        }

        return $this->render('users/cnx.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/", name="users_index", methods={"GET"})
     */
    public function index(): Response
    {
        $users = $this->getDoctrine()
            ->getRepository(Users::class)
            ->findAll();

        return $this->render('users/index.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/new", name="users_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $user = new Users();
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);
        $user->setType("client");
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('home_index');
        }

        return $this->render('users/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{userId}", name="users_show", methods={"GET"})
     */
    public function show(Users $user): Response
    {
        return $this->render('users/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @Route("/{userId}/edit", name="users_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Users $user): Response
    {
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('users_index');
        }

        return $this->render('users/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{userId}", name="users_delete", methods={"POST"})
     */
    public function delete(Request $request, Users $user): Response
    {
        if ($this->isCsrfTokenValid('delete' . $user->getUserId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('users_index');
    }
}