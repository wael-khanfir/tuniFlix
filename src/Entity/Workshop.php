<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Workshop
 *
 * @ORM\Table(name="workshop")
 * @ORM\Entity
 */
class Workshop
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
     * @ORM\Column(name="nomEvent", type="string", length=30, nullable=false)
     */
    private $nomevent;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateDebut", type="date", nullable=true)
     */
    private $datedebut;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateFin", type="date", nullable=true)
     */
    private $datefin;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="hDebut", type="time", nullable=false)
     */
    private $hdebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="hFin", type="time", nullable=false)
     */
    private $hfin;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=30, nullable=false)
     */
    private $lieu;

    /**
     * @var int
     *  /**
     * @Assert\Range(
     *      min = 0,
     *      max = 180,
     *      notInRangeMessage = "Erreur",
     * )
     *
     * @ORM\Column(name="nbParticipant", type="integer", nullable=false)
     */
    private $nbparticipant;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=30, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=30, nullable=false)
     */
    private $description;

    /**
     * @var float
     *    * @Assert\Range(
     *      min = 0,
     *      notInRangeMessage = "Erreur",
     * )
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

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
    public function getNomevent(): ?string
    {
        return $this->nomevent;
    }

    /**
     * @param string $nomevent
     */
    public function setNomevent(string $nomevent): void
    {
        $this->nomevent = $nomevent;
    }

    /**
     * @return string
     */
    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    /**
     * @param string $lieu
     */
    public function setLieu(string $lieu): void
    {
        $this->lieu = $lieu;
    }

    /**
     * @return int
     */
    public function getNbparticipant(): ?int
    {
        return $this->nbparticipant;
    }

    /**
     * @param int $nbparticipant
     */
    public function setNbparticipant(int $nbparticipant): void
    {
        $this->nbparticipant = $nbparticipant;
    }

    /**
     * @return string
     */
    public function getType(): ?string
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): void
    {
        $this->type = $type;
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
     * @return float
     */
    public function getPrix(): ?float
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix(float $prix): void
    {
        $this->prix = $prix;
    }

    /**
     * @return \DateTime|null
     */
    public function getDatedebut(): ?\DateTime
    {
        return $this->datedebut;
    }

    /**
     * @param \DateTime|null $datedebut
     */
    public function setDatedebut(?\DateTime $datedebut): void
    {
        $this->datedebut = $datedebut;
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


}
