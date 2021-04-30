<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210429003234 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE stock (id INT AUTO_INCREMENT NOT NULL, produit_id INT DEFAULT NULL, quantite INT NOT NULL, fournisseur VARCHAR(255) NOT NULL, INDEX IDX_4B365660F347EFB (produit_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT FK_4B365660F347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
        $this->addSql('DROP TABLE article');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE projection');
        $this->addSql('DROP TABLE promotion');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE reserver');
        $this->addSql('DROP TABLE users');
        $this->addSql('DROP TABLE workshop');
        $this->addSql('ALTER TABLE produit CHANGE datefin datefin DATE DEFAULT NULL, CHANGE prix prix INT DEFAULT NULL, CHANGE img img VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE salle CHANGE cinema_id cinema_id INT NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE article (id INT AUTO_INCREMENT NOT NULL, titre VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, nom_film VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE client (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, num_telephone INT NOT NULL, adresse_mail VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, genre VARCHAR(55) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE projection (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, genre VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, age_recommande INT NOT NULL, duree VARCHAR(44) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, image VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE promotion (id INT AUTO_INCREMENT NOT NULL, dateDebut DATE NOT NULL, dateFin DATE NOT NULL, type VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prix DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id INT NOT NULL, date_reservation VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, type_de_place VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, mode_de_paiement VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prix INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reserver (idR INT AUTO_INCREMENT NOT NULL, quantiteR INT NOT NULL, ref_produitR INT NOT NULL, INDEX ref_produitR (ref_produitR), PRIMARY KEY(idR)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE users (user_id INT AUTO_INCREMENT NOT NULL, username VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, password VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, email VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, type VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, PRIMARY KEY(user_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE workshop (id INT AUTO_INCREMENT NOT NULL, nomEvent VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, dateDebut DATE DEFAULT NULL, dateFin DATE DEFAULT NULL, hDebut INT NOT NULL, hFin INT NOT NULL, lieu VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, nbParticipant INT NOT NULL, type VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, description VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, prix DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE stock');
        $this->addSql('ALTER TABLE produit CHANGE prix prix INT NOT NULL, CHANGE datefin datefin DATE NOT NULL, CHANGE img img VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`');
        $this->addSql('ALTER TABLE salle CHANGE cinema_id cinema_id INT DEFAULT NULL');
    }
}
