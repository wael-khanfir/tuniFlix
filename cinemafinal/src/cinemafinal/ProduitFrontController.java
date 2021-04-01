/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemafinal;

import entity.Produit;
import entity.reservation;
import java.io.IOException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.teknikindustries.bulksms.SMS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import service.ProduitCrud;
import service.reservationCrud;

import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProduitFrontController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> coldescription;
    @FXML
    private TableColumn<?, ?> colprix;
    @FXML
    private TableColumn<?, ?> ActionCol;
    List<Produit> listP = new ArrayList<>();
ProduitCrud ps = new ProduitCrud();
    @FXML
    private Button nombtn;
    @FXML
    private ComboBox<String> Cstock;
    @FXML
    private TextField tquantité;
    @FXML
    private Button ajout;
    public ObservableList<reservation> tables = FXCollections.observableArrayList();
   
      Produit p =new Produit();
      private reservation ss=null;
    public static reservation reclamationPourReponse;
    public static String responsestatic;
    @FXML
    private Button bte;
    @FXML
    private TextField email;
 
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
                afficher();
        }         
    }   catch (SQLException ex) {    
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   
    private void afficher() {
        
       listP=ps.afficherAll();
ObservableList<Produit> platListe=FXCollections.observableArrayList(listP);
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
    
    idCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    //coldate.setCellValueFactory(new PropertyValueFactory<>("datefin"));
      table.setItems(platListe); 
        }

   

    @FXML
    private void TrierNom(ActionEvent event) {
         
        List<Produit> liste = ps.TrierNomPer();
        ObservableList et = FXCollections.observableArrayList(liste);
        table.setItems(et);//  ouiii chnouwa 5orm hedha ???  
        idCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
         int quantite= Integer.parseInt(tquantité.getText());
        ProduitCrud Produits = new ProduitCrud();
        Produit p=new Produit();
        p=Produits.getProduit(Cstock.getValue());
        
        reservationCrud ac = new reservationCrud();
        reservation dd = new reservation(quantite, p.getRef());
        ac.ajouter(dd); 
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Stock!");
        alert.setHeaderText("information!");
        alert.setContentText("Added  Stock!");
        alert.showAndWait();}

    @FXML
    private void envoyer(ActionEvent event) {
     
    }

    
}

    
    
