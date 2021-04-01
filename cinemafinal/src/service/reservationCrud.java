/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.reservation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.net.ssl.HttpsURLConnection;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class reservationCrud {
     Connection cn2;
    Statement st;

    public reservationCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
     public ObservableList<reservation> displayALLStock() {
        ObservableList<reservation> myList = FXCollections.observableArrayList();
        try { 
            String req ="select r.*,p.nom from reserver r INNER JOIN produit p on r.ref_produitR = p.ref ";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                reservation r = new reservation();
                r.setIdR(rs.getInt("idR"));
                r.setQuantiteR(rs.getInt("quantiteR"));
                r.setRef_produitR(rs.getInt("ref_produitR"));
                r.setSto(rs.getString("nom"));
                myList.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public void ajouter(reservation a) {
        try {
            String requete = "INSERT INTO reserver (idR,quantiteR,ref_produitR) VALUES (?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
             pst.setInt(1, a.getIdR());
            pst.setInt(2, a.getQuantiteR());
            pst.setInt(3, a.getRef_produitR());
   
            pst.executeUpdate();
            System.out.println("ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
     
      

  
}
