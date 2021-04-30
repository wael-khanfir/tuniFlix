<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
/**
 * @Route("/produit")
 */
class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="produit_index", methods={"GET"})
     */
    public function index(Request $request,PaginatorInterface $paginator,FlashyNotifier $flashy): Response
    {
        $donnees = $this->getDoctrine()->getRepository(produit::class)->findAll([],
            ['created_at'=>'desc']);


        $produit = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
            2
        );
        $flashy->success('Welcome!', 'http://your-awesome-link.com');
        return $this->render('produit/index.html.twig', [
            'produits' => $produit,
        ]);
    }



    /**
     * @Route("/new", name="produit_new", methods={"GET","POST"})
     */
    public function new(Request $request ,FlashyNotifier $flashy): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $file */
            $file = $produit->getImage();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('images_directory'),$fileName
                );
            }catch(FileException $e){}
            $produit->setImage($fileName);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($produit);
            $entityManager->flush();

            return $this->redirectToRoute('produit_index');
        }
        $flashy->success('New Product!', 'http://your-awesome-link.com');
        return $this->render('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="produit_show", methods={"GET"})
     */
    public function show(Produit $produit,FlashyNotifier $flashy): Response
    {
        $flashy->success('Show Product!', 'http://your-awesome-link.com');
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="produit_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Produit $produit,FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $file */
            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move(
                $this->getParameter('images_directory'), $fileName
            );
            $produit->setImage($fileName);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_index');
        }
        $flashy->success('Edit Product!', 'http://your-awesome-link.com');
        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="produit_delete", methods={"POST"})
     */
    public function delete(Request $request, Produit $produit): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($produit);
            $entityManager->flush();
        }

        return $this->redirectToRoute('produit_index');
    }

    /**
     * @param ProduitRepository $repository
     * @param Request $request
     * @return Response
     * @Route("produit/searchMultiple",name="rechercheMultiple2")
     */

    function Search(ProduitRepository $repository, Request $request,PaginatorInterface $paginator)
    {
        $id = $request->get('recherche');
        $nom = $request->get('recherche');
        $description = $request->get('recherche');
        $prix = $request->get('recherche');
        $date = $request->get('recherche');
        $produit = $repository->SearchID($id, $nom, $description,$prix,$date);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         *
         */

        $pagination = $paginator->paginate(
            $produit,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)/*nbre d'éléments par page*/
        );

        return $this->render('produit/index.html.twig',
            ['produits'=>$pagination]);
    }
}