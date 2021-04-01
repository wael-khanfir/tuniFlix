/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemafinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button gerEvent;
    @FXML
    private Button gerPart;
    @FXML
    private Button gerCours;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void gererEvennement(MouseEvent event) throws IOException {
             AnchorPane pane=FXMLLoader.load(getClass().getResource("Produit.fxml"));
        ap.getChildren().setAll(pane);
    }

    @FXML
    private void gererCours(MouseEvent event) throws IOException {
          AnchorPane pane=FXMLLoader.load(getClass().getResource("Stock.fxml"));
        ap.getChildren().setAll(pane);
    }

  

  
    

    @FXML
    private void gererParticipation(MouseEvent event) throws IOException {
         AnchorPane pane=FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
        ap.getChildren().setAll(pane);
    }

    
}
