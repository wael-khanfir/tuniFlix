<?php

namespace App\Controller;

use App\Entity\Users;
use App\Form\ConnexionUserType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Services\Cart\CartService;
class CartController extends AbstractController
{
    /**
     *  @Route("/panier", name="cart_index")
     */
    public function index(CartService $cartService,Request $request, SessionInterface $session)
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
                    return $this->render('cart/index.html.twig', [
                        'items' => $cartService->getFullCart(),
                        'total' => $cartService->getTotal(),

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
     *  @Route("/panier/add/{id}", name="cart_add")
     */
    public function add($id, CartService $cartService)
    {

        $cartService->add($id);
        return $this->redirectToRoute('cart_index');

    }
    /**
     * @Route("/panier/remove/{id}", name="cart_remove")
     */
    public function remove($id, CartService $cartService)
    {
        $cartService->remove($id);

        return $this->redirectToRoute('cart_index');

    }




}
