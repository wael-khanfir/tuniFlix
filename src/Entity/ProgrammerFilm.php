<?php

namespace App\Entity;

use App\Repository\ProgrammerFilmRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ProgrammerFilmRepository::class)
 */
class ProgrammerFilm
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="datetime")
     *  @Assert\GreaterThan("today UTC")
     */
    private $date_projection;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $langue;

    /**
     * @ORM\ManyToOne(targetEntity=Projection::class, inversedBy="programmerFilms")
     */
    private $projection;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateProjection(): ?\DateTimeInterface
    {
        return $this->date_projection;
    }

    public function setDateProjection(\DateTimeInterface $date_projection): self
    {
        $this->date_projection = $date_projection;

        return $this;
    }

    public function getLangue(): ?string
    {
        return $this->langue;
    }

    public function setLangue(string $langue): self
    {
        $this->langue = $langue;

        return $this;
    }

    public function getProjection(): ?Projection
    {
        return $this->projection;
    }

    public function setProjection(?Projection $projection): self
    {
        $this->projection = $projection;

        return $this;
    }
}
