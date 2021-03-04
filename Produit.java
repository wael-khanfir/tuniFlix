/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ASUS
 */
public class Produit {
    private int ref ;
    private int stock ;
    private String nom ;
    private String description ;
    private String datefin ;
    private int prix ;

    public Produit(int ref, String nom, String description,int stock, String datefin,int prix) {
        this.ref = ref;
        this.stock = stock;
        this.nom = nom;
        this.description = description;
        this.datefin = datefin;
        this.prix = prix;
        
    }
     public Produit( String nom, String description, int stock,String datefin,int prix) {
        this.stock = stock;
        this.nom = nom;
        this.description = description;
        this.datefin = datefin;
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
     public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    

    public Produit() {
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

  
 
    
   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    

    @Override
    public String toString() {
        return "produit{" + "ref=" + ref + ", stock=" + stock + ", nom=" + nom + ", description=" + description + ", datefin=" + datefin +", prix=" + prix +  '}';
    }
    
    
}
