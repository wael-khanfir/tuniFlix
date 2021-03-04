/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
/**
 *
 * @author ASUS
 */
public class ProduitCrud {
    
    
    Connection cn2;
    Statement st;

    public ProduitCrud() {
        cn2 = MyConnection.getInstance().getCnx();
    }

    
    
    
    public ObservableList<Produit> afficher(Produit A) throws SQLException {
        ObservableList<Produit> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from produit");

        while (rs.next()) {

            arr.add(new Produit(rs.getInt("ref"), rs.getString("nom"), rs.getString("description"),rs.getInt("stock"), rs.getString("datefin"),rs.getInt("prix")));

        }
        return arr;

    }

    
    
   public void ajouter(Produit a) {
        try {
            String requete = "INSERT INTO produit (ref,nom,description,stock,datefin,prix) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
             pst.setInt(1, a.getRef());

            pst.setString(2, a.getNom());
            pst.setString(3, a.getDescription());
            
            pst.setInt(4, a.getStock());
           pst.setString(5, a.getDatefin());
          
            pst.setInt(6, a.getPrix());
           
            pst.executeUpdate();
            System.out.println("Produit ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Produit> afficher() {

        ArrayList<Produit> ann = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM produit";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Produit a = new Produit();
                a.setRef(rs.getInt("ref"));
                
                a.setNom(rs.getString("nom"));
                a.setDescription(rs.getString("description"));
                a.setRef(rs.getInt("stock"));
                a.setDatefin(rs.getString("datefin"));
                 a.setRef(rs.getInt("prix"));
                
                ann.add(a);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ann;

    }

    
    public void supprimer(int id_annonce) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from produit where ref= " + id_annonce;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }

    
    public void modifier(Produit A, int id) throws SQLException {

        try {
         
      String query = "update produit set nom='" + A.getNom()+ "',description='" + A.getDescription()+ "',stock='" + A.getStock() + "',datefin='" + A.getDatefin()+"',prix='" + A.getPrix()+"' where ref=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

           
        } catch (SQLException ex) {

        }

    }
    
    
    
}
