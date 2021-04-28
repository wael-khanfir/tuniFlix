<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Entity\Stock;
use App\Form\StockType;
use App\Repository\ProduitRepository;
use App\Repository\StockRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;



/**
 * @Route("/stock")
 */
class StockController extends AbstractController
{
    /**
     * @Route("/", name="stock_index", methods={"GET"})
     */
    public function index(StockRepository $stockRepository,Request $request,PaginatorInterface $paginator,FlashyNotifier $flashy): Response
    {
        $donnees = $this->getDoctrine()->getRepository(stock::class)->findAll([],
            ['created_at'=>'desc']);


        $stock = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
            2
        );
        $flashy->success('Welcome!', 'http://your-awesome-link.com');
        return $this->render('stock/index.html.twig', [
            'stocks' => $stock,
        ]);

    }

    /**
     * @Route("/new", name="stock_new", methods={"GET","POST"})
     */
    public function new(Request $request,FlashyNotifier $flashy): Response
    {
        $stock = new Stock();
        $form = $this->createForm(StockType::class, $stock);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($stock);
            $entityManager->flush();

            return $this->redirectToRoute('stock_index');
        }
        $flashy->success('New Stock!', 'http://your-awesome-link.com');
        return $this->render('stock/new.html.twig', [
            'stock' => $stock,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="stock_show", methods={"GET"})
     */
    public function show(StockRepository $stockRepository, Stock $stock,FlashyNotifier $flashy): Response
    {
        $flashy->success('Show Stock!', 'http://your-awesome-link.com');
        return $this->render('stock/show.html.twig', [
            'stock' => $stock,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="stock_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Stock $stock,FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(StockType::class, $stock);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('stock_index');
        }
        $flashy->success('Edit Stock!', 'http://your-awesome-link.com');
        return $this->render('stock/edit.html.twig', [
            'stock' => $stock,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="stock_delete", methods={"POST"})
     */

    public function delete($id, StockRepository $repository)
    {
        $stock = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($stock);
        $em->flush();
        $this->addFlash('delete', 'Stock deleted !');

        return $this->redirectToRoute('stock_index');
    }


    /**
     * @Route("imprim", name="imprim")
     */
    public function imprimmaison(StockRepository $StockRepository): Response

    {

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $stocks = $StockRepository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('stock/imprim.html.twig', [
            'stocks' => $stocks,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("Liste stock.pdf", [
            "Attachment" => true
        ]);
    }


    /**
     * @Route("stock/tri", name="stock/tri")
     */
    public function Tri(Request $request)
    {
        $em = $this->getDoctrine()->getManager();


        $query = $em->createQuery(
            'SELECT n FROM App\Entity\Stock n
            ORDER BY n.quantite '
        );

        $stocks = $query->getResult();

        return $this->render('stock/index.html.twig',
            array('stocks' => $stocks));

    }

    /**
     * @param StockRepository $repository
     * @param Request $request
     * @return Response
     * @Route("stock/searchMultiple",name="rechercheMultiple")
     */

    function Search(StockRepository $repository, Request $request,PaginatorInterface $paginator)
    {
        $quantite = $request->get('recherche');
        $id = $request->get('recherche');
        $fournisseur = $request->get('recherche');


        $stock = $repository->SearchID($id, $quantite, $fournisseur);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         *
         */

        $pagination = $paginator->paginate(
            $stock,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)/*nbre d'éléments par page*/
        );

        return $this->render('stock/index.html.twig',
            ['stocks'=>$pagination]);
    }
}