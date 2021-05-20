<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

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
     * @Groups("post:read")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="genre", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $genre;

    /**
     * @var int
     *
     * @ORM\Column(name="age_recommande", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $ageRecommande;

    /**
     * @var string
     *
     * @ORM\Column(name="duree", type="string", length=44, nullable=false)
     * @Groups("post:read")
     */
    private $duree;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=10000, nullable=false)
     * @Groups("post:read")
     */
    private $image;

    /**
     * @ORM\OneToMany(targetEntity=ProgrammerFilm::class, mappedBy="projection",cascade={"all"},orphanRemoval=true)

     */
    private $programmerFilms;

    public function __construct()
    {
        $this->programmerFilms = new ArrayCollection();
    }
    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=44, nullable=false)
     * @Groups("post:read")
     */
    private $description;


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
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     * @return Collection|ProgrammerFilm[]
     */
    public function getProgrammerFilms(): Collection
    {
        return $this->programmerFilms;
    }

    public function addProgrammerFilm(ProgrammerFilm $programmerFilm): self
    {
        if (!$this->programmerFilms->contains($programmerFilm)) {
            $this->programmerFilms[] = $programmerFilm;
            $programmerFilm->setProjection($this);
        }

        return $this;
    }

    public function removeProgrammerFilm(ProgrammerFilm $programmerFilm): self
    {
        if ($this->programmerFilms->removeElement($programmerFilm)) {
            // set the owning side to null (unless already changed)
            if ($programmerFilm->getProjection() === $this) {
                $programmerFilm->setProjection(null);
            }
        }

        return $this;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }






}
