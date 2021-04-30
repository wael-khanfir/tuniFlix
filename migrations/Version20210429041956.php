<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210429041956 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE programmer_film (id INT AUTO_INCREMENT NOT NULL, projection_id INT DEFAULT NULL, date_projection DATETIME NOT NULL, langue VARCHAR(255) NOT NULL, INDEX IDX_574985065ECF66BD (projection_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE programmer_film ADD CONSTRAINT FK_574985065ECF66BD FOREIGN KEY (projection_id) REFERENCES projection (id)');
        $this->addSql('DROP TABLE users');
        $this->addSql('ALTER TABLE article CHANGE img img VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE projection CHANGE duree duree VARCHAR(44) NOT NULL, CHANGE description description VARCHAR(44) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE users (user_id INT AUTO_INCREMENT NOT NULL, username VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, password VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, email VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, type VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, PRIMARY KEY(user_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE programmer_film');
        $this->addSql('ALTER TABLE article CHANGE img img VARCHAR(10000) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`');
        $this->addSql('ALTER TABLE projection CHANGE duree duree VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, CHANGE description description VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`');
    }
}
