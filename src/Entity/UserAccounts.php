<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * UserAccounts
 *
 * @ORM\Table(name="user_accounts")
 * @ORM\Entity
 */
class UserAccounts
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
     * @ORM\Column(name="full_name", type="string", length=50, nullable=false)
     */
    private $fullName;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=50, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=50, nullable=false)
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="user_type", type="string", length=50, nullable=false)
     */
    private $userType;

    /**
     * @var bool|null
     *
     * @ORM\Column(name="is_online", type="boolean", nullable=true)
     */
    private $isOnline = '0';


}
