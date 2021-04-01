/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemafinal;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Produit;
import entity.Stock;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import service.ProduitCrud;
import utils.MyConnection;
import java.io.File;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;
import service.StockCrud;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> idCol;
    @FXML
    private TableColumn<Button, Boolean> ActionCol;
    @FXML
    private TableColumn<?, ?> coldescription;
    @FXML
    private TableColumn<Produit, String> colprix;
    @FXML
    private TableColumn<Produit, String> coldate;
    @FXML
    private TextField titre;
    @FXML
    private Button image;
    @FXML
    private Label erreurTitre;
    private Label ErreurDesc;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tprix;
    @FXML
    private DatePicker dated;
    @FXML
    private ImageView imgg;
    @FXML
    private Button modifier1;
    @FXML
    private Button ajout;
       
 List<Produit> listP = new ArrayList<>();
        
ProduitCrud ps = new ProduitCrud();
   List<String> typee;
            String img="";
    @FXML
    private TextField search;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         affichAllProduit();
          typee =new ArrayList();
        typee.add("*.jpg");
         typee.add("*.png");
    }    

   @FXML
    private void affichDet(MouseEvent event) {
        Produit x=table.getSelectionModel().getSelectedItem();
      //imgg.setImage(x.getImage());
       imgg.setImage(new Image("file:/C:/Users/ASUS/Documents/NetBeansProjects/cinemafinal/src/"+x.getImage()));

      
       titre.setText(x.getNom());
       tdescription.setText(x.getDescription());
            dated.setValue(LocalDate.now());
            tprix.setText("" + x.getPrix());

    }

    String immg="";
    String immmg="";
    @FXML
    private void upload(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg,png", typee));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
             img=fc.getAbsoluteFile().toURI().toString();
             immg=fc.getPath();
             immmg=fc.getName();
             System.out.println(img);
          // System.out.print(img);
             Image i = new Image(img);
           imgg.setImage(i);
        }//dkhalt? oui 
    }

   

    
    String tit="";
    @FXML
    private void ajout(ActionEvent event) throws FileNotFoundException, SQLException {
                if (titre.getText().isEmpty() || img.isEmpty()||tdescription.getText().isEmpty())
{Error("Veuillez remplir tous le champs");

}else if(!titre.getText().matches("^[a-zA-Z\\s]*$"))
{Error("invalide nom : le champs nom ne contient que des lettres");}
else if(!tdescription.getText().matches("^[a-zA-Z\\s]*$"))
{Error("invalide description : le champs description ne contient que des lettres");}
 else
    {   tit=titre.getText();
    
        
   
   FileInputStream fis=new FileInputStream(new File(immg));

     LocalDate d= (LocalDate)dated.getValue();
        java.sql.Date sqlDate1 = java.sql.Date.valueOf(d);
         
         String f = tprix.getText();
        int prix;
        prix = Integer.parseInt(f);
        Produit produit=new Produit(titre.getText(),tdescription.getText(),sqlDate1,prix,immmg,immmg);
         
                
if(img=="")
{img="cinema.jpg";
produit.setImage(img);
} 
         Boolean test= ps.ajouter(produit);
    if(test)
     {
       
     Success("Votre ajout a ete efectue");
     titre.setText("");
     tdescription.setText("");
     tprix.setText("");
     dated.setValue(LocalDate.now());
     img="";
     erreurTitre.setText("");
    
      
      
     }affichAllProduit();
    }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException{
      Produit x=table.getSelectionModel().getSelectedItem();
ps.delete(x.getRef());
titre.setText("");
tdescription.setText("");
tprix.setText("");
dated.setValue(LocalDate.now());
            
 //imgg.setImage(new Image(this.getClass().getResourceAsStream(img)));
imgg.setImage(new Image(this.getClass().getResourceAsStream("cinema.jpg")));
affichAllProduit();
    }
    
    
    
     @FXML
    private void modifier(ActionEvent event) {
       Produit x= table.getSelectionModel().getSelectedItem();
     
                     
  LocalDate d= (LocalDate)dated.getValue();
               java.sql.Date datee = java.sql.Date.valueOf(d);
if(img == "")
{  
    ps.modifier(titre.getText(),tdescription.getText(),datee,Integer.parseInt(tprix.getText()),x.getImage(),x.getImg());

}
else { ps.modifier(titre.getText(),tdescription.getText(),datee,Integer.parseInt(tprix.getText()),img,immmg);

}
affichAllProduit();
    }
    
    
       private void affichAllProduit()
   {        listP=ps.afficherAll();
ObservableList<Produit> platListe=FXCollections.observableArrayList(listP);
        ActionCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
    
    idCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coldate.setCellValueFactory(new PropertyValueFactory<>("datefin"));
      table.setItems(platListe); 
   }/*************************************/
      
        private void Success(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter un produit");
 
        // Header Text: nullCla
        alert.setHeaderText(null);
        alert.setContentText(msg);
 
        alert.showAndWait();
    }
       
       private void Error(String msg) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText(msg);
alert.showAndWait();
    }
    
       
       
      

    @FXML
    private void convertirEnPdf(ActionEvent event) {
        String file="C:\\Users\\ASUS\\Documents\\NetBeansProjects\\nchlh\\produit.pdf";
    Document document =new Document();
 // Notifications notificationBuilder = Notifications.create()
   // .title("Download completed")
  // .text("saved In C:\\ ")
   // .hideAfter(Duration.seconds(4))
  //  .position(Pos.BOTTOM_CENTER);
    //notificationBuilder.showInformation();
     try{
           Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.UNDERLINE));
           f.setColor(0, 153, 255);
           Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD));
           f2.setColor(0, 0, 0);
           PdfWriter.getInstance(document, new FileOutputStream(new File(file)));
           document.open();
           Paragraph p =new Paragraph("LISTE  DES  PRODUITS " ,f);
           p.setAlignment(Element.ALIGN_CENTER);
           //document.add(Image.getInstance("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\nchlh\\produit.pdf"));
           Paragraph pm =new Paragraph();
           pm.add("   \n  ");
           document.add(p);
           document.add(pm);
           document.add(pm);
           Paragraph posss= new Paragraph("__________________________________________________");
           document.add(posss);
           Paragraph pos= new Paragraph("nom"+"      "+"description"+"      "+" prix"+"      "+"image",f2);
           document.add(pos);
           document.add(posss);
           Connection cn2 = MyConnection.getInstance().getCnx();
           String req ="SELECT * FROM produit";
           Statement pst = cn2.createStatement();
           ResultSet rs = pst.executeQuery(req);
      while (rs.next()) {
           Paragraph p1= new Paragraph( "   ");
           Paragraph po= new Paragraph(rs.getString("nom")+"  "+rs.getString("description")+"       "+rs.getString("prix")+"        "+rs.getString("image"));
           document.add(p1);
           document.add(po);
            }
         document.close();
         System.out.println("Produit");
     }catch(Exception e){
         e.printStackTrace();
     }}

    @FXML
    private void recherche(KeyEvent event) {
        ProduitCrud sp = new ProduitCrud();

         List events=sp.afficherAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table.setItems(et);
        table.setItems((ObservableList<Produit>) et);
        FilteredList<Produit> filteredData = new FilteredList<>(et, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
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




   
}













    
