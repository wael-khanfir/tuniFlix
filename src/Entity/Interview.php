<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Interview
 *
 * @ORM\Table(name="interview")
 * @ORM\Entity
 */
class Interview
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
     * @ORM\Column(name="nom_invite", type="string", length=255, nullable=false)
     */
    private $nomInvite;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_interview", type="date", nullable=false)
     */
    private $dateInterview;

    /**
     * @var int
     *
     * @ORM\Column(name="dure_interview", type="integer", nullable=false)
     */
    private $dureInterview;


}
