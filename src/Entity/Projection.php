<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Projection
 *
 * @ORM\Table(name="projection")
 * @ORM\Entity
 */
class Projection
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
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="genre", type="string", length=255, nullable=false)
     */
    private $genre;

    /**
     * @var int
     *
     * @ORM\Column(name="age_recommande", type="integer", nullable=false)
     */
    private $ageRecommande;

    /**
     * @var string
     *
     * @ORM\Column(name="duree", type="string", length=44, nullable=false)
     */
    private $duree;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=10000, nullable=false)
     */
    private $image;

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
    public function getNom()
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
     * @return string
     */
    public function getGenre()
    {
        return $this->genre;
    }

    /**
     * @param string $genre
     */
    public function setGenre(string $genre): void
    {
        $this->genre = $genre;
    }

    /**
     * @return int
     */
    public function getAgeRecommande()
    {
        return $this->ageRecommande;
    }

    /**
     * @param int $ageRecommande
     */
    public function setAgeRecommande(int $ageRecommande): void
    {
        $this->ageRecommande = $ageRecommande;
    }

    /**
     * @return string
     */
    public function getDuree()
    {
        return $this->duree;
    }

    /**
     * @param string $duree
     */
    public function setDuree(string $duree): void
    {
        $this->duree = $duree;
    }

    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage(string $image): void
    {
        $this->image = $image;
    }


}
