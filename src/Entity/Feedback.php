<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Feedback
 *
 * @ORM\Table(name="feedback")
 * @ORM\Entity
 */
class Feedback
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
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="message", type="string", length=255, nullable=false)
     * @Groups("post:read")
     */
    private $message;

    /**
     * @var int
     *
     * @ORM\Column(name="etat", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $etat;



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
     * @return string
     */
    public function getMessage(): ?string
    {
        return $this->message;
    }

    /**
     * @param string $message
     */
    public function setMessage(string $message): void
    {
        $this->message = $message;
    }

    /**
     * @return int
     */
    public function getEtat(): ?int
    {
        return $this->etat;
    }

    /**
     * @param int $etat
     */
    public function setEtat(int $etat): void
    {
        $this->etat = $etat;
    }


}
