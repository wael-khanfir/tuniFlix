/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nchlh;

import entity.Produit;
import entity.ProduitCrud;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tstock;
    @FXML
    private TextField tdate;
    @FXML
    private Button bAjouter;
    
    
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private TableColumn<?, ?> col_stock;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableView<Produit> table;
    public ObservableList<Produit> tables = FXCollections.observableArrayList();
    @FXML
    private TextField recherche1;
    @FXML
    private TableColumn<?, ?> col_iduser;
    @FXML
    private TableColumn<?, ?> col_prix;
    @FXML
    private TextField tprix;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        

        try {
            ProduitCrud act = new ProduitCrud();
            Produit An = new Produit();
            ObservableList<Produit> tables = act.afficher(An);
        } catch (SQLException ex) {

        }
        
        refresh();
        
         table.setItems((ObservableList<Produit>) tables);
        FilteredList<Produit> filteredData = new FilteredList<>(tables, b -> true);
        recherche1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (A.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
                    
                }// Filter matches first name.
                else {
                    return false;
                }
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
      
        
        
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        String a = tnom.getText();
        
        String d = tdescription.getText();
        String e = tstock.getText();
        String c = tdate.getText();
        String f = tprix.getText();
        int stock ,prix;
        stock = Integer.parseInt(e);
        prix = Integer.parseInt(f);
       
 
        ProduitCrud ac = new ProduitCrud();
        Produit aaa = new Produit( a,d,stock,c,prix);
        ac.ajouter(aaa);
        refresh();

    }

    @FXML
    private void SelectItemes(MouseEvent event) {
        
        
        ObservableList<Produit> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            tnom.setText(oblist.get(0).getNom());
           
            int max = oblist.get(0).getRef();
            tdescription.setText(oblist.get(0).getDescription());
            tstock.setText("" + oblist.get(0).getStock());
            tdate.setText(oblist.get(0).getDatefin());
            
             tprix.setText("" + oblist.get(0).getPrix());
            
            
            //cat_id.setText("" + oblist.get(0).getCategorie_id());
           // cat.setVisible(false);
           // cat_id.setVisible(false);
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        
        Produit A = new Produit();
        A.setNom(tnom.getText());
        
        A.setDescription(tdescription.getText());
        A.setStock(Integer.parseInt(tstock.getText()));
        A.setDatefin(tdate.getText());
        A.setPrix(Integer.parseInt(tprix.getText()));
        

        ObservableList<Produit> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getRef();

        ProduitCrud act = new ProduitCrud();
        try {          

            act.modifier(A, max);
            System.out.println(max);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        refresh();

    }

    @FXML
    private void supp(ActionEvent event) {
        
        ObservableList<Produit> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getRef();

        Produit A = new Produit();
        ProduitCrud act = new ProduitCrud();
        try {
            act.supprimer(max);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        refresh();
    }
 
    
    
     
    public void refresh() {

        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        // table.setItems(tables);
        try {
            ProduitCrud act = new ProduitCrud();
            Produit Ans = new Produit();
            tables = act.afficher(Ans);

        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<Produit>) tables);

    }
    
    
}
