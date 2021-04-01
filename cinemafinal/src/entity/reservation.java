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
public class reservation {
     private int idR;
     private int quantiteR ;
      int ref_produitR ;
      private String sto;
      Produit p;

 public reservation(int idR, int quantiteR,int ref_produitR) {
        this.idR = idR;
        this.quantiteR = quantiteR;
        
      this.ref_produitR= ref_produitR;
        
            
    }
     public reservation(int quantiteR,int ref_produitR) {
         
        this.quantiteR = quantiteR;
        
        this.ref_produitR = ref_produitR;
            
    }
     
     
     public reservation(int quantiteR,Produit p) {
         
        this.quantiteR = quantiteR;
                 this.p = p;
    }

      public reservation() {
    }
     
      
       public Produit getA() {
        return p;
    }

    public void setA(Produit p) {
        this.p = p;
    }
     
     public int getIdR() {
        return idR;
    }

    public void setIdR(int id) {
        this.idR = idR;
    }
    
     public int getQuantiteR() {
        return quantiteR;
    }

    public void setQuantiteR(int quantiteR) {
        this.quantiteR = quantiteR;
    }
    
    
     public int getRef_produitR() {
        return ref_produitR;
    }

    public void setRef_produitR(int ref_produitR) {
        this.ref_produitR = ref_produitR;
    }

public String getSto() {
        return sto;
    }

    public void setSto(String sto) {
        this.sto = sto;
    }
    
    
   
}
    