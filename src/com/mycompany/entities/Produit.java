/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Produit {
    
     private int id;
    private String nom ;
    private String image; 
    private String img;
    private String description;
    private Date datefin;
    private int prix;

    public Produit() {
    }
    
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
   public Produit(int id,String nom, String description,Date datefin,int prix, String image) {
        this.id=id;
        this.nom = nom;
        this.image = image;
        this.description = description;
       this.datefin = datefin;
      this.prix = prix;
    }
   public Produit(int id,String nom, String description,int prix, String image) {
        this.id=id;
        this.nom = nom;
        this.image = image;
        this.description = description;
      this.prix = prix;
    }

     public Produit(int id,String nom, String description,int prix, String image, String img) {
        this.id=id;
        this.nom = nom;
        this.image = image;
        this.description = description;
       this.img = img;
       this.prix = prix;
    }
    public Produit(String nom, String description,int prix, String image) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }
   public Produit(String nom, String description, Date datefin,int prix, String image) {
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.datefin = datefin;
        this.prix = prix;
    }
     public  Produit(String nom, String description, Date datefin,int prix, String image, String img) {
        this.nom = nom;
        this.image = image;
        this.img = img;
        this.description = description;
        this.datefin = datefin;
        this.prix = prix;
    }
public Produit(int id,String nom, String description, Date datefin,int prix, String image, String img) {
        this.id=id;
        this.nom = nom;
        this.image = image;
        this.img = img;
        this.description = description;
       this.datefin = datefin;
        this.prix = prix;
    }

     
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }
     
     
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
 public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

     
      public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
   
     @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return "produit{" + "id=" + id + ", datefin=" + datefin + ", prix=" + prix + ", description=" + description + ", nom=" + nom  +", img=" + img  + ", image=" + image + '}';
    }
    
}
