/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author FK Info
 */
public class Projection {
    int id;
    String nom;
    String genre;
    int age_recommande;
    String duree;
   String image;
   String description;
  

  public Projection(int id, String nom, String genre, int age_recommande, String duree, String image, String description) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
        this.age_recommande = age_recommande;
        this.duree = duree;
        this.image = image;
        this.description = description;
    }
   
    public Projection() {
    }
    public String getImage(){
        return image;
    }
    public void set_Image(String image)
    {
        this.image=image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge_recommande() {
        return age_recommande;
    }

    public void setAge_recommande(int age_recommande) {
        this.age_recommande = age_recommande;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   @Override
    public String toString() {
        return "projection{" + "id=" + id + ", nom=" + nom + ", genre=" + genre + ", age=" +age_recommande +", duree=" +duree +", img=" +image +", description=" +description + '}';
    }
    
}
