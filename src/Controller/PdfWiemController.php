<?php

namespace App\Controller;

use App\Repository\ProduitRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PdfWiemController extends AbstractController
{
    /**
     * @Route("/pdf/wiem", name="pdf_wiem")
     */
    public function imprim(ProduitRepository $ProduitRepository): Response

    {

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $produits = $ProduitRepository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('pdf_wiem/imprim.html.twig', [
            'produits' => $produits,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("Listeproduit.pdf", [
            "Attachment" => true
        ]);
    }


}
