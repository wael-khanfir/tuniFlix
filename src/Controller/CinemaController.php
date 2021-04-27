<?php

namespace App\Controller;

use App\Entity\Cinema;
use App\Form\CinemaType;
use App\Repository\CinemaRepository;
use App\Repository\SalleRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/cinema")
 */
class CinemaController extends AbstractController
{
    /**
     * @Route("/", name="cinema_index", methods={"GET"})
     * @param $repository
     * @return Response
     */
    public function index(CinemaRepository $repository): Response
    {
        $cinemas=$repository->OrderByIdDQL();


        return $this->render('cinema/index.html.twig', [
            'cinemas' => $cinemas,
        ]);
    }

    /**
     * @Route("/new", name="cinema_new", methods={"GET","POST"})
     * @param Request $request
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $cinema = new Cinema();
        $form = $this->createForm(CinemaType::class, $cinema);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($cinema);
            $entityManager->flush();

            $flashy->success('ajout avec succès', 'http://your-awesome-link.com');
            return $this->redirectToRoute('cinema_index');

        }

        return $this->render('cinema/new.html.twig', [
            'cinema' => $cinema,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cinema_show", methods={"GET"})
     */
    public function show(Cinema $cinema): Response
    {
        return $this->render('cinema/show.html.twig', [
            'cinema' => $cinema,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="cinema_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Cinema $cinema
     * @param $flashy
     * @return Response
     */
    public function edit(Request $request, Cinema $cinema,FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(CinemaType::class, $cinema);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            $flashy->primaryDark('cinema modifié avec succès', 'http://your-awesome-link.com');
            return $this->redirectToRoute('cinema_index');
        }

        return $this->render('cinema/edit.html.twig', [
            'cinema' => $cinema,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cinema_delete", methods={"POST"})
     * @param Request $request
     * @param Cinema $cinema
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function delete(Request $request, Cinema $cinema,FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cinema->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cinema);
            $entityManager->flush();


        }
        $flashy->primaryDark('cinema supprimé avec succès', 'http://your-awesome-link.com');
        return $this->redirectToRoute('cinema_index');
    }




//    /**
//     * @param CinemaRepository $repository
//     * @return Response
//     * @Route("cinema/Trie",name="trie")
//     */
 /*   function OrderByMailSQL( CinemaRepository $repository,Request $request,PaginatorInterface $paginator): Response
    { $cinema=$repository->OrderByAdress();


        $pagination = $paginator->paginate(
            $cinema,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );
        return $this->render('cinema/index.html.twig',
            ['cinema'=>$pagination]);
    }
 public function OrderByAdress()
    {
        $entityManager = $this->getEntityManager();
        $query = $entityManager->createQuery('SELECT c FROM App\Entity\Cinema c ORDER BY c.adresse ASC');
        return $query->getResult();
    } */

    public function searchAction (Request $request): Response
    {
        $em =$this ->getDoctrine()->getManager();
        $requestString =$request->get('q');
        $cinemas=$em->getRepository('Cinema')->findEntitiesByString($requestString);
        if(!cinemas){
            $result['cinemas']['error']="cinema not found";
        }else{
            $result['cinemas']=$this->getRealEntities($cinemas);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($cinemas)
    {
        foreach($cinemas as $cinemas){
            $realEntities[$cinemas->getId()]=[$cinemas->getNom(),$cinemas->getAdresse()];
        }
        return $realEntities;
    }


//    /**
//     * @Route("/cinema/recherche", name="recherche")
//     */
/*    public function Recherche(CinemaRepository $repository, Request $request): Response
    {
        $data=$request->get('search');
        $cinemas =$repository->findBy(['nom'=>$data]);

        return $this->render('cinema/index.html.twig', [
            'cinemas' => $cinemas,
        ]);
    }
*/

    /**
     * @param CinemaRepository $repository
     * @return Response
     * @Route ("/cinema/ListDQL" , name="triecinemadql")
     */
    function OrderByMailDQL(CinemaRepository $repository): Response
    {

        $cinemas=$repository->OrderByMailDQL();
        return $this->render('cinema/index.html.twig', [
            'cinemas' => $cinemas,
        ]);

    }


} ?>





<?php
if (isset($_POST["submit_address"]))
{
$address = $_POST["address"];
$address = str_replace(" ", "+", $address);
?>
        <div style="margin-left: 259px; ">
<iframe width="100%" margin-left="100px" height="500" src="https://maps.google.com/maps?q= <?php echo $address; ?>&output=embed"></iframe>
    </div>


    <?php
    }
?>







