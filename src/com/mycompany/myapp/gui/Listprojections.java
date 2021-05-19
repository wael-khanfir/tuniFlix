/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Projection;
import com.mycompany.myapp.service.serviceArticle;
import com.mycompany.myapp.service.serviceProjection;
import java.util.ArrayList;

/**
 *
 * @author FK Info
 */
public class Listprojections extends Form {

   public Listprojections(Form previous) {
        setTitle("List des projections");
            //search tbadel 3onwen tool bar
//prepare field
TextField searchField;
searchField = new TextField("", "Chercher un film");
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
          ArrayList<Projection>liste=serviceProjection.getInstance().getAllProjections();
        for(Projection art : liste)
        {
            addButton(art);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    private void addButton(Projection a) {
     
        
        Label ta1 = new Label("Nom :" + a.getNom(),"NewsTopLine");
        Label ta2 = new Label("genre :" + a.getGenre(),"NewsTopLine");
        Label ta3 = new Label("age :" + a.getAge_recommande(),"NewsTopLine");
        Label ta4 = new Label("dure :" + a.getDuree(),"NewsTopLine");
       
      String url="file:/Users/FK Info/Desktop/piidev/Pidev/public/uploads/image";
        Image placeholder=Image.createImage(1150,420);
        EncodedImage enc=EncodedImage.createFromImage(placeholder, false);
        URLImage urlim=URLImage.createToStorage(enc,a.getImage(), url+"/"+a.getImage());
        ImageViewer imgv=new ImageViewer();
        imgv.setImage(urlim);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        
       
       
        ta1.getStyle().setFgColor(0x1e3642);
        ta1.getStyle().setFont(smallPlainSystemFont);
        ta2.getStyle().setFgColor(0x1e3642);
        ta2.getStyle().setFont(smallPlainSystemFont);
         
     
        
        createLineSeparator();
        add(BoxLayout.encloseY(ta1,ta2,imgv));
        }

  
      public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
   
    }

  



    
    

