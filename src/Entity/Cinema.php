<?php

namespace App\Entity;

use App\Repository\CinemaRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * Cinema
 *
 * @ORM\Table(name="cinema")
 * @ORM\Entity(repositoryClass=CinemaRepository::class)
 */
class Cinema
{
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
     * @var \DateTime
     *
     * @ORM\Column(name="date_creation", type="date", nullable=false)
     * @Groups("post:read")
     */
    private $dateCreation;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     * @Assert\Email
     * @Groups("post:read")
     */
    private $email;

    /**
     * @ORM\OneToMany(targetEntity=Salle::class, mappedBy="cinema", orphanRemoval=true)
     */
    private $salles;

    public function __construct()
    {
        $this->salles = new ArrayCollection();
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
    public function getDateCreation(): ?\DateTime
    {
        return $this->dateCreation;
    }

    /**
     * @param \DateTime $dateCreation
     */
    public function setDateCreation(\DateTime $dateCreation): void
    {
        $this->dateCreation = $dateCreation;
    }

    /**
     * @return string
     */
    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    /**
     * @param string $adresse
     */
    public function setAdresse(string $adresse): void
    {
        $this->adresse = $adresse;
    }

    /**
     * @return string
     */
    public function getEmail(): ?string
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    /**
     * @return Collection|Salle[]
     */
    public function getSalles(): Collection
    {
        return $this->salles;
    }

    public function addSalle(Salle $salle): self
    {
        if (!$this->salles->contains($salle)) {
            $this->salles[] = $salle;
            $salle->setCinema($this);
        }

        return $this;
    }

    public function removeSalle(Salle $salle): self
    {
        if ($this->salles->removeElement($salle)) {
            // set the owning side to null (unless already changed)
            if ($salle->getCinema() === $this) {
                $salle->setCinema(null);
            }
        }

        return $this;
    }


}
