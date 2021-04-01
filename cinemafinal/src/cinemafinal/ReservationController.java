/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemafinal;

import entity.Produit;
import entity.reservation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ProduitCrud;
import service.reservationCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationController implements Initializable {

    @FXML
    private TableColumn<?, ?> tnom;
    @FXML
    private TableColumn<?, ?> tquantite;
    @FXML
    private TableView<reservation> table;
reservation p =new reservation();
 List<reservation> listP = new ArrayList<>();
reservationCrud ps = new reservationCrud();
 private reservation reservation;
 private Produit Produit;
    @FXML
    private Label sal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
               
                afficher();
        }         
      
    
      
     private void afficher() {
        
       listP=ps.displayALLStock();
ObservableList<reservation> platListe=FXCollections.observableArrayList(listP);
       
    
    tnom.setCellValueFactory(new PropertyValueFactory<>("sto"));
    tquantite.setCellValueFactory(new PropertyValueFactory<>("quantiteR"));
    
      table.setItems(platListe); 
        }
     
     
     

    
        
   

}
