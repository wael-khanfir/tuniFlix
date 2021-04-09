<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cinema
 *
 * @ORM\Table(name="cinema")
 * @ORM\Entity
 */
class Cinema
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_cinema", type="string", length=255, nullable=false)
     */
    private $nomCinema;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_place", type="integer", nullable=false)
     */
    private $nbPlace;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_salle", type="integer", nullable=false)
     */
    private $nbSalle;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_diffusion", type="date", nullable=false)
     */
    private $dateDiffusion;


}
