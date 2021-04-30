<?php

namespace App\Repository;

use App\Entity\Produit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Produit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit[]    findAll()
 * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Produit::class);
    }

    // /**
    //  * @return Produit[] Returns an array of Produit objects
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
    public function findOneBySomeField($value): ?Produit
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    function SearchID($id, $nom, $description,$prix,$date){
        return $this->createQueryBuilder('produit')
            ->where('produit.id LIKE :id')
            ->orWhere('produit.nom LIKE :nom')
            ->orWhere('produit.description LIKE :description')
            ->orWhere('produit.prix LIKE :prix')
            ->orWhere('produit.datefin LIKE :datefin')
            ->setParameter('id','%'.$id.'%')
            ->setParameter('nom','%'.$nom.'%')
            ->setParameter('description','%'.$description.'%')
            ->setParameter('prix','%'.$prix.'%')
            ->setParameter('datefin','%'.$date.'%')

            ->getQuery()->getResult();
    }
}
