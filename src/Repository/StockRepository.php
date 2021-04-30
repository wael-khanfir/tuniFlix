<?php

namespace App\Repository;

use App\Entity\Stock;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Stock|null find($id, $lockMode = null, $lockVersion = null)
 * @method Stock|null findOneBy(array $criteria, array $orderBy = null)
 * @method Stock[]    findAll()
 * @method Stock[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class StockRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Stock::class);
    }

    // /**
    //  * @return Stock[] Returns an array of Stock objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Stock
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    function SearchID($id,$quantite,$fournisseur){
        return $this->createQueryBuilder('stock')
            ->where('stock.id LIKE :id')
            ->orWhere('stock.quantite LIKE :quantite')
            ->orWhere('stock.fournisseur LIKE :fournisseur')
            ->setParameter('id','%'.$id.'%')
            ->setParameter('quantite','%'.$quantite.'%')
            ->setParameter('fournisseur','%'.$fournisseur.'%')

            ->getQuery()->getResult();
    }

    public function OrderById()
    { $em=$this->getEntityManager();
        $query=$em->createQuery('select s from App\Entity\Stock s order by s.quantite ASC');
        return $query->getResult();
    }
}