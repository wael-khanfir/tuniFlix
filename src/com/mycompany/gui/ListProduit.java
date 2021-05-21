/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Produit;
import com.mycompany.services.ProduitService;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.mycompany.myapp.entities.Projection;
import com.mycompany.services.serviceProjection;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class ListProduit extends Form {
Form current;
int nbre=2;
     int nbrs=3;
   public ListProduit(Form previous) {
       double[] values = new double[2];
    		values[0]=nbre;
    		values[1]=nbrs;
        getStyle().setBgColor(0x2D2D2D);
        setTitle("List des projections");
        
        
            //search tbadel 3onwen tool bar
//RECHERCHE
TextField searchField;
searchField = new TextField("", "Chercher produit");
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
getToolbar().setTitleComponent(searchField);
//if field content changed
searchField.addDataChangedListener((i1, i2) -> {
String t = searchField.getText();
if(t.length() < 1) {
for(Component cmp : getContentPane()) {
cmp.setHidden(false);
cmp.setVisible(true);
}
} else {
t = t.toLowerCase();
for(Component cmp: getContentPane()) {
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->label )
String val = ((Label) ((Container) cmp).getComponentAt(0) ).getText();
System.out.println(   (  (Label) ((Container) cmp).getComponentAt(0) ).getText() );
boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});
          ArrayList<Produit>liste=ProduitService.getInstance().getList2();
        for(Produit art : liste)
        {
            addButton(art);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
   
   
   
   public ListProduit(Resources res) {
       super("Produits: ", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      
        getContentPane().setScrollVisible(false);
        
        addSideMenu(res);
        
        Tabs swipe = new Tabs();
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
      
        
       
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("   ", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("    ", barGroup);
        featured.setUIID("SelectBar");
        RadioButton Cinema = RadioButton.createToggle("    ", barGroup);
        Cinema.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("   ", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("    ", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(5, all, featured,Cinema, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Cinema.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Cinema, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(Cinema, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });      
       
       
       
   }
   
   
   
   

    private void addButton(Produit a) {
     
        
        Label ta1 = new Label("Nom :" + a.getNom(),"NewsTopLine");
        Label ta2 = new Label("genre :" + a.getDescription(),"NewsTopLine");
        Label ta3 = new Label("age :" + a.getPrix(),"NewsTopLine");
        //Label ta4 = new Label("dure :" + a.getDuree(),"NewsTopLine");
       
      String url="file:/xampp/htdocs/webintegration/public/uploads/images";
        Image placeholder=Image.createImage(1150,420);
        EncodedImage enc=EncodedImage.createFromImage(placeholder, false);
        URLImage urlim=URLImage.createToStorage(enc,a.getImage(), url+"/"+a.getImage());
        ImageViewer imgv=new ImageViewer();
        imgv.setImage(urlim);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        
       
       
        ta1.getStyle().setFgColor(0xfffcf3);
        ta1.getStyle().setFont(smallPlainSystemFont);
        ta2.getStyle().setFgColor(0xfffcf3);
        ta2.getStyle().setFont(smallPlainSystemFont);
         
       current=this;
      
        createLineSeparator();
        add(BoxLayout.encloseY(ta1,ta2,imgv));
        }

  
      public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
   
      
      
      
      //el 3 li ta7t l affichage side menu
    
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
    //SIDE MENU
    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("wiem.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Nos Cinemas", FontImage.MATERIAL_SCREEN_SHARE, e -> new HomeForm(res).show());
        tb.addMaterialCommandToSideMenu("Laisser Un Feedback", FontImage.MATERIAL_MESSENGER , e -> new AjoutFeedbackForm(res).show());
        
        //WAEL
        tb.addMaterialCommandToSideMenu("Films et Articles", FontImage.MATERIAL_ARTICLE, e -> new Home(res).show());
        //WIEM
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_FASTFOOD, e -> new HomeForm_1(res).show());
        //ISSAM
        tb.addMaterialCommandToSideMenu("Log Out", FontImage.MATERIAL_LOGOUT, e -> new LoginForm(res).show());
                
                
                
    }
}
    
           
    