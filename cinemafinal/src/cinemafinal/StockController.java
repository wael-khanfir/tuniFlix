/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemafinal;

import com.sun.javaws.Main;
import entity.Produit;
import service.ProduitCrud;
import entity.Stock;
import service.StockCrud;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StockController implements Initializable {

    @FXML
    private TextField tfounisseur;
    @FXML
    private TextField tquantité;
    @FXML
    private TableColumn<Stock, String> col_four;
    @FXML
    private TableColumn<Stock, String> col_produit;
    @FXML
    private TableColumn<Stock, Integer> col_quantite;
    @FXML
    private TableView<Stock> table;
     public ObservableList<Stock> tables = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<String> Cstock;
      Produit p =new Produit();
      private Stock ss=null;
    @FXML
    private Label lfournisseur;
    @FXML
    private Label lquantité;
    @FXML
    private Label lstock;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner produit");
        ProduitCrud a=new  ProduitCrud();
        String req = "SELECT * FROM produit";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                p.setRef(rs.getInt("ref"));
                p.setNom(rs.getString("nom"));
                availableChoices.add(p.getNom());
                Cstock.setItems(availableChoices);
                Cstock.getSelectionModel().selectFirst();
            
        }         
    }   catch (SQLException ex) {    
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    @FXML
   private void ajouter(ActionEvent event) throws SQLException {
     lfournisseur.setText("");
     lquantité.setText("");
     lstock.setText("");
     
 if(tfounisseur.getText().isEmpty()||tquantité.getText().isEmpty()||Cstock.getSelectionModel().getSelectedItem().equals("Selectionner produit")){
     if (tfounisseur.getText().isEmpty()) {
          lfournisseur.setText("Fournisseur Vide");
        }   
     
     if (tquantité.getText().isEmpty()) {
          lquantité.setText("Quantité Vide");
        }
     if (Cstock.getSelectionModel().getSelectedItem().equals("Selectionner produit")) {
           lstock.setText("Produit Vide");
        } 
 }else { 
        int quantite= Integer.parseInt(tquantité.getText());
         if (quantite<0) {
            lquantité.setText("quantite doit etre > 0 ");}
       else {
             
       // String f = tquantité.getText();
        String a = tfounisseur.getText();
        
       
       
        ProduitCrud Produits = new ProduitCrud();
        Produit p=new Produit();
        p=Produits.getProduit(Cstock.getValue());
        
        StockCrud ac = new StockCrud();
        Stock dd = new Stock(quantite,a, p.getRef());
        ac.ajouter(dd); 
        Alert alert =new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Stock!");
        alert.setHeaderText("information!");
        alert.setContentText("Added  Stock!");
        alert.showAndWait();}
 }
 }
   
    @FXML
    private void SelectItemes(MouseEvent event) {
        
        
        
        Connection cn2= MyConnection.getInstance().getCnx();
        
        ObservableList<Stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        ss = (Stock)table.getSelectionModel().getSelectedItem();
        ProduitCrud Produits = new ProduitCrud();
        if (oblist != null) {
            tfounisseur.setText(oblist.get(0).getFournisseur());
            tquantité.setText(""+oblist.get(0).getQuantite());
            try {
                 Cstock.setValue(Produits.getProduitNom(ss.getRef_produit()));
                 
                } catch (SQLException ex) {
                  Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                }                      

        }
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner produit");
        ProduitCrud a=new ProduitCrud();
        String req = "SELECT * FROM produit";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                p.setRef(rs.getInt("ref"));
                p.setNom(rs.getString("nom"));
                availableChoices.add(p.getNom());
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supp(ActionEvent event) {
        ObservableList<Stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getId();
        Stock A = new Stock();
        StockCrud act = new StockCrud();
        try {
            act.supprimer(max);
             Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("delete Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("Deleted  Stock!");
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    
    
      @FXML
    private void afficher(ActionEvent event) {

     StockCrud sp = new  StockCrud();
      List events=sp.displayALLStock();
       ObservableList et=FXCollections.observableArrayList(events);
       table.setItems(et);
       
        col_four.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        col_produit.setCellValueFactory(new PropertyValueFactory<>("sto"));

    }


    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        
     
        if(tfounisseur.getText().isEmpty()||tquantité.getText().isEmpty()||Cstock.getSelectionModel().getSelectedItem().equals("Selectionner produit")){
     if (tfounisseur.getText().isEmpty()) {
          lfournisseur.setText("Fournisseur Vide");
        }   
     
     if (tquantité.getText().isEmpty()) {
          lquantité.setText("Quantité Vide");
        }
     if (Cstock.getSelectionModel().getSelectedItem().equals("Selectionner produit")) {
           lstock.setText("Produit Vide");
        } 
 }else { 
        int quantite= Integer.parseInt(tquantité.getText());
         if (quantite<0) {
            lquantité.setText("quantite doit etre > 0 ");}
       else {
             
              
                Stock A = new Stock();
                A.setFournisseur(tfounisseur.getText());
                A.setQuantite(Integer.parseInt(tquantité.getText()));
                ProduitCrud Produits = new ProduitCrud();
                Produit b=new Produit();
                b=Produits.getProduit(Cstock.getValue());  
                System.out.println("cle etranger"+b.getRef());

               ObservableList<Stock> oblist;
               oblist = table.getSelectionModel().getSelectedItems();
               StockCrud act = new StockCrud();
               Stock a=new Stock(Integer.parseInt(tquantité.getText()),tfounisseur.getText(),b.getRef());
               try {
               act.modifier(a,ss.getId());
            
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Update Don!");
            alert.setHeaderText("information!");
            alert.setContentText("Updated Don!");
            alert.showAndWait();
            } catch (SQLException ex) {
            System.out.println(ex);
        }
 }
        
    }
    }
    
     public void vider (){
       tfounisseur.clear();
       tquantité.clear();
         Connection cn2= MyConnection.getInstance().getCnx();
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Selectionner produit");
        ProduitCrud a=new ProduitCrud();
        String req = "SELECT * FROM produit";
        try {
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                p.setRef(rs.getInt("ref"));
                p.setNom(rs.getString("nom"));
                availableChoices.add(p.getNom());
                Cstock.setItems(availableChoices);
                Cstock.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
      

    @FXML
    private void recherche(KeyEvent event) {
         StockCrud sp = new StockCrud();

         List events=sp.displayALLStock();
       ObservableList et=FXCollections.observableArrayList(events);
       table.setItems(et);
        table.setItems((ObservableList<Stock>) et);
        FilteredList<Stock> filteredData = new FilteredList<>(et, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (A.getFournisseur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }// Filter matches first name.
                else {
                    return false;
                }
            });
        });
        SortedList<Stock> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    
  
  
}
  
    

