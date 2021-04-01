/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

import entity.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class StockCrud { 
    Connection cn2;
    Statement st;

    public StockCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
     public ObservableList<Stock> displayALLStock() {
        ObservableList<Stock> myList = FXCollections.observableArrayList();
        try {
            String req ="select s.*,p.nom from stock s INNER JOIN produit p on s.ref_produit = p.ref ";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Stock s = new Stock();
                s.setId(rs.getInt("id"));
                s.setFournisseur(rs.getString("fournisseur"));
                s.setQuantite(rs.getInt("quantite"));
                s.setRef_produit(rs.getInt("ref_produit"));
                s.setSto(rs.getString("nom"));
                myList.add(s);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public void ajouter(Stock a) {
        try {
            String requete = "INSERT INTO stock (id,quantite,fournisseur,ref_produit) VALUES (?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
             pst.setInt(1, a.getId());
            pst.setInt(2, a.getQuantite());
            pst.setString(3, a.getFournisseur());
           pst.setInt(4, a.getRef_produit());
   
            pst.executeUpdate();
            System.out.println("stock ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   public ObservableList<Stock> afficher(Stock A) throws SQLException {

        ObservableList<Stock> arr = FXCollections.observableArrayList();
         
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from stock ");

        while (rs.next()) {
        
            arr.add(new Stock(rs.getInt("id"), rs.getInt("quantite"), rs.getString("fournisseur"), rs.getInt("ref_produit")));

        }
         
              
        return arr;

    }

   

   public void supprimer(int idd) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from stock where id= " + idd;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }

       
    
   public void modifier(Stock A, int idd) throws SQLException {

        try {
          if ((A.getQuantite() != 0) && (A.getFournisseur() != "")  && (A.getRef_produit() != 0) ) {
              String query = "update stock set quantite='" + A.getQuantite() + "',fournisseur='" + A.getFournisseur() +  "',ref_produit='" + A.getRef_produit() + "' where id=" + idd;
              st = cn2.createStatement();
              st.executeUpdate(query);
              System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
           }catch (SQLException ex) {

            }
   }
    
    
}

