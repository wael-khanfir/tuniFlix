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
public class Stock {
   
     private int id;
     private int quantite ;
     private String fournisseur ;
     int ref_produit ;
     private String sto;
     Produit p;

    public Stock(int id, int quantite, String fournisseur,int ref_produit) {
        this.id = id;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
      this.ref_produit= ref_produit;
        
            
    }
     public Stock(int quantite, String fournisseur,int ref_produit) {
         
        this.quantite = quantite;
        this.fournisseur = fournisseur;
       // this.nom=nom;
        this.ref_produit = ref_produit;
            
    }
     
     
     public Stock(int quantite, String fournisseur,Produit p) {
         
        this.quantite = quantite;
        this.fournisseur = fournisseur;
             this.p = p;
    }

      public Stock() {
    }
     
      
       public Produit getA() {
        return p;
    }

    public void setA(Produit p) {
        this.p = p;
    }
     
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
     public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    
     public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }
    
    
    
    public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }
    
    
    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", quantite=" + quantite + ", fournisseur=" + fournisseur +", ref_produit=" + ref_produit + '}';
    }
}
