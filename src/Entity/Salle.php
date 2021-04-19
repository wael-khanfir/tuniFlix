<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Salle
 *
 * @ORM\Table(name="salle")
 * @ORM\Entity
 */
class Salle
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
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="le champ est vide")
     */
    private $nom;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_diffusion", type="date", nullable=false)

     */
    private $dateDiffusion;

    /**
     * @var string
     *
     * @ORM\Column(name="nb_places", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="le champ est vide")
     */
    private $nbPlaces;

    /**
     * @var string
     *
     * @ORM\Column(name="disponible", type="string", length=50, nullable=false)
     *
     * @Assert\Choice(
     *     choices = {"oui", "non"},
     *     message = "taper oui ou non")
     *
     */
    private $disponible;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return \DateTime
     */
    public function getDateDiffusion(): ?\DateTime
    {
        return $this->dateDiffusion;
    }

    /**
     * @param \DateTime $dateDiffusion
     */
    public function setDateDiffusion(\DateTime $dateDiffusion): void
    {
        $this->dateDiffusion = $dateDiffusion;
    }

    /**
     * @return string
     */
    public function getNbPlaces(): ?string
    {
        return $this->nbPlaces;
    }

    /**
     * @param string $nbPlaces
     */
    public function setNbPlaces(string $nbPlaces): void
    {
        $this->nbPlaces = $nbPlaces;
    }

    /**
     * @return string
     *
     */
    public function getDisponible(): ?string
    {
        return $this->disponible;
    }

    /**
     * @param string $disponible
     */
    public function setDisponible(string $disponible): void
    {
        $this->disponible = $disponible;
    }


}
