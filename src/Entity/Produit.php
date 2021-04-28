<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use App\Repository\StockRepository;
use App\Repository\ProduitRepository;

/**
/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */

class Produit
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
     * @Assert\NotBlank(message="Merci d'indiquer le nom du produit")
     * @ORM\Column(type="string", length=255)
     * @Groups("depense:read")
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;


    /**
     * @Assert\NotBlank(message="Add jpg image")
     * @Assert\File(mimeTypes={ "image/jpeg" ,"image/png","image/jpg"})
     * @ORM\Column(type="string", length=255, nullable=false)
     * @Groups("depense:read")
     */
    private $image;

    /**
     * @var int
     *
     * @ORM\Column(name="prix", type="integer", nullable=true)
     */
    private $prix;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="datefin", type="date", nullable=true)
     */
    private $datefin;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=255, nullable=false)
     */
    private $img;

    /**
     * @ORM\OneToMany(targetEntity=Stock::class, mappedBy="produit",cascade={"all"},orphanRemoval=true)
     */
    private $stock;

    public function __construct()
    {
        $this->stock = new ArrayCollection();
    }






    /**
     * @return int
     */
    public function getId(): ?int
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
     * @return string
     */
    public function getDescription(): ?string
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
    public function setImage( $image)
    {
        $this->image = $image;
        return $this;
    }

    /**
     * @return int
     */
    public function getPrix(): ?int
    {
        return $this->prix;
    }

    /**
     * @param int $prix
     */
    public function setPrix(int $prix): self
    {
        $this->prix = $prix;
        return $this;
    }

    /**
     * @return \DateTime|null
     */
    public function getDatefin(): ?\DateTime
    {
        return $this->datefin;
    }

    /**
     * @param \DateTime|null $datefin
     */
    public function setDatefin(?\DateTime $datefin): void
    {
        $this->datefin = $datefin;
    }

    /**
     * @return string
     */
    public function getImg(): ?string
    {
        return $this->img;
    }

    /**
     * @param string $img
     */
    public function setImg(string $img): void
    {
        $this->img = $img;
    }

    /**
     * @return Collection|Stock[]
     */
    public function getStock(): Collection
    {
        return $this->stock;
    }

    public function addStock(Stock $stock): self
    {
        if (!$this->stock->contains($stock)) {
            $this->stock[] = $stock;
            $stock->setProduit($this);
        }

        return $this;
    }

    public function removeStock(Stock $stock): self
    {
        if ($this->stock->removeElement($stock)) {
            // set the owning side to null (unless already changed)
            if ($stock->getProduit() === $this) {
                $stock->setProduit(null);
            }
        }

        return $this;
    }



}
