<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping as ORM;

/**
 * Invite
 *
 * @ORM\Table(name="invite")
 * @ORM\Entity
 */
class Invite
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
     * @ORM\Column(name="first_name", type="string", length=50, nullable=false)
     */
    private $firstName;

    /**
     * @var string
     *
     * @ORM\Column(name="last_name", type="string", length=50, nullable=false)
     */
    private $lastName;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="career", type="string", length=50, nullable=false)
     */
    private $career;

    /**
     * @var string
     *
     * @ORM\Column(name="interview_type", type="string", length=50, nullable=false)
     */
    private $interviewType;

    /**
     * @var DateTime|null
     *
     * @ORM\Column(name="interview_date", type="date", nullable=true, options={"default"="NULL"})
     */
    private $interviewDate = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="appointed_num", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $appointedNum = NULL;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $image = 'NULL';

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
    public function getFirstName(): string
    {
        return $this->firstName;
    }

    /**
     * @param string $firstName
     */
    public function setFirstName(string $firstName): void
    {
        $this->firstName = $firstName;
    }

    /**
     * @return string
     */
    public function getLastName(): string
    {
        return $this->lastName;
    }

    /**
     * @param string $lastName
     */
    public function setLastName(string $lastName): void
    {
        $this->lastName = $lastName;
    }

    /**
     * @return string
     */
    public function getEmail(): string
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
    public function getCareer(): string
    {
        return $this->career;
    }

    /**
     * @param string $career
     */
    public function setCareer(string $career): void
    {
        $this->career = $career;
    }

    /**
     * @return string
     */
    public function getInterviewType(): string
    {
        return $this->interviewType;
    }

    /**
     * @param string $interviewType
     */
    public function setInterviewType(string $interviewType): void
    {
        $this->interviewType = $interviewType;
    }

    /**
     * @return DateTime|null
     */
    public function getInterviewDate(): DateTime|string|null
    {
        return $this->interviewDate;
    }

    /**
     * @param DateTime|null $interviewDate
     */
    public function setInterviewDate(DateTime|string|null $interviewDate): void
    {
        $this->interviewDate = $interviewDate;
    }

    /**
     * @return int|null
     */
    public function getAppointedNum(): ?int
    {
        return $this->appointedNum;
    }

    /**
     * @param int|null $appointedNum
     */
    public function setAppointedNum(?int $appointedNum): void
    {
        $this->appointedNum = $appointedNum;
    }

    /**
     * @return string|null
     */
    public function getImage(): ?string
    {
        return $this->image;
    }

    /**
     * @param string|null $image
     */
    public function setImage(?string $image): void
    {
        $this->image = $image;
    }


}
