<?php

namespace App\Repository;

use App\Entity\ProgrammerFilm;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ProgrammerFilm|null find($id, $lockMode = null, $lockVersion = null)
 * @method ProgrammerFilm|null findOneBy(array $criteria, array $orderBy = null)
 * @method ProgrammerFilm[]    findAll()
 * @method ProgrammerFilm[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProgrammerFilmRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ProgrammerFilm::class);
    }

    // /**
    //  * @return ProgrammerFilm[] Returns an array of ProgrammerFilm objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ProgrammerFilm
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function recherche($id)
    {
        $em=$this->getEntityManager();
        $query=$em->createQuery('select s from App\Entity\ProgrammerFilm s  JOIN s.projection c where c.id=:id')
            ->setParameter('id',$id);
        return $query->getResult();
    }
    public function tri_date()
    {
        $em=$this->getEntityManager();
        $query=$em->createQuery('select s from App\Entity\ProgrammerFilm s order by s.date_projection ASC ');
        return $query->getResult();
    }
}
