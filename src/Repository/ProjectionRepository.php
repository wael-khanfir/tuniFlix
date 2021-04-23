<?php

namespace App\Repository;

use App\Entity\Projection;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
/**
* @method Projection|null find($id, $lockMode = null, $lockVersion = null)
* @method Projection|null findOneBy(array $criteria, array $orderBy = null)
    * @method Projection[]    findAll()
* @method Projection[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
    */
class ProjectionRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Projection::class);
    }
    public function tri_id()
    {
        $em=$this->getEntityManager();
        $query=$em->createQuery('select s from App\Entity\Projection s order by s.id DESC ');
        return $query->getResult();
    }
    public function recherche(String $id)
    {
        $em=$this->getEntityManager();
        $query=$em->createQuery('select s from App\Entity\ProgrammerFilm s  JOIN s.projection c where c.id=:id')
            ->setParameter('id',$id);
        return $query->getResult();
    }
}