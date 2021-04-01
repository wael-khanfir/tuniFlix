/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;



import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class Produit {

    private int ref;
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

    
   public Produit(int ref,String nom, String description,Date datefin,int prix, String image) {
        this.ref=ref;
        this.nom = nom;
        this.image = image;
        this.description = description;
       this.datefin = datefin;
      this.prix = prix;
    }
     public Produit(int ref,String nom, String description,int prix, String image, String img) {
        this.ref=ref;
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
public Produit(int ref,String nom, String description, Date datefin,int prix, String image, String img) {
        this.ref=ref;
        this.nom = nom;
        this.image = image;
        this.img = img;
        this.description = description;
       this.datefin = datefin;
        this.prix = prix;
    }
     
       public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref =ref;
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

    
  
    private ImageView photo= new ImageView();

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
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
   
  

}

