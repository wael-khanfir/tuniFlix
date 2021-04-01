/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class ProduitCrud {
    Connection cn2;
    Statement st;
    private PreparedStatement pre;
    
     public ProduitCrud() {
        cn2 = MyConnection.getInstance().getCnx();
     }
      public Produit getProduit(String a) throws SQLException {   
        Produit an = new Produit();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM produit WHERE nom LIKE ?  ;");

        pre.setString(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setRef(rs.getInt("ref"));
           an.setNom(a);


           
        }
        return an;

    }
        public Produit getRefProduit(int a) throws SQLException {
          Produit an = new Produit();
        PreparedStatement pre = cn2.prepareStatement("SELECT * FROM produit WHERE ref = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an.setRef(a);
           an.setNom(rs.getString("nom"));


           
        }
        return an;
     }
    
     public String getProduitNom(int a) throws SQLException {
          String an="" ;
        PreparedStatement pre = cn2.prepareStatement("SELECT nom FROM produit WHERE ref = ?  ;");

        pre.setInt(1, a);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            an=rs.getString(1);


           
        }
        return an;
     }
 public boolean ajouter(Produit a) throws SQLException {
      
         try {
            String req = "INSERT INTO produit (nom,description,datefin,prix,image,img) VALUES "
                    + "('" + a.getNom() + "', '" + a.getDescription() +"', '" + a.getDatefin() +"', '" + a.getPrix() +"',  '" + a.getImage() +"', '"+a.getImg()+ "')";

            st = cn2.createStatement();

            st.executeUpdate(req);

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

}

 public List<Produit> afficherAll() {

       List<Produit> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM produit";

            st = cn2.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Produit p = new Produit();

                p.setRef(res.getInt("ref"));
                p.setNom(res.getString("nom"));
                p.setDescription(res.getString("description"));
                p.setDatefin(res.getDate("datefin"));
                p.setPrix(res.getInt("prix"));
                p.setImage(res.getString("image"));
                p.setImg(res.getString("img"));
                
   ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/Users/ASUS/Documents/NetBeansProjects/cinemafinal/src/"+res.getString("img")));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                p.setPhoto(v);
                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
 
       
        
    

        
public void  modifier(String nom, String description,Date datefin,int prix, String image, String img){
        
          try {
            String req = "update produit set nom=?, description=?,datefin=NOW(),prix=?, image=? ,img=? where ref = ?";

            pre = cn2.prepareStatement(req);
         
            pre.setString(1,nom);
            pre.setString(2,description);
            pre.setDate(3,datefin);
            pre.setInt(4,prix);
            pre.setString(5,img);
            pre.setString(6,image);
  
            pre.executeUpdate();
            

            System.out.println("Update Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
     
    }}

        
    
    
    public void delete(int ref) {
      
         try {
            String req = "delete from produit where ref = ?";

            pre = cn2.prepareStatement(req);

          
            pre.setInt(1, ref);

            pre.executeUpdate();

            System.out.println("supprimer !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

      }

     public  ArrayList<Produit>  TrierNomPer(){
         ArrayList<Produit> list = new ArrayList<>() ;
             try {//chimdakhell image ???
                Statement st=cn2.createStatement();
                String req="Select ref, nom,description,prix ,image ,img from produit order by nom";
                ResultSet rs = st.executeQuery(req);
                while(rs.next()){

                     // public Produit(String nom, String prenom, int age, float nb_h, float prix_h, String categorie, String image) {
Produit e= new  Produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4), rs.getString(5), rs.getString(6));
                  //  Produit e= new Produit(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6),rs.getString(7)); 
  
        
         ImageView v=new ImageView();
                   v.setImage(new Image("file:/C:/Users/ASUS/Documents/NetBeansProjects/cinemafinal/src/"+rs.getString("img")));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                e.setPhoto(v);
                list.add(e);
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(ProduitCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
      return list ;  
       }
      
    
    
     
 
    }

 

