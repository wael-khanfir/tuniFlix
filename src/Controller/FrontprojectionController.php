<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Projection;
use App\Repository\ProjectionRepository;
use App\Repository\ProjectionRepositoryRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontprojectionController extends AbstractController
{
    /**
     * @Route("/frontt", name="frontt")
     */
    public function index(ProjectionRepository $repository ): Response
    {$projections=$repository->tri_id();
        return $this->render('front_projection.html.twig', [
            'projections' => $projections,
        ]);
    }


}
