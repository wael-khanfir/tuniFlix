/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.service.serviceArticle;
import java.util.ArrayList;
import java.util.Map;
import com.codename1.io.JSONParser;

/**
 *
 * @author bhk
 */
public class Listarticle extends Form{

    public Listarticle(Form previous) {
        String accountSID = "AC7b5220f269ca1dd931a9710e63a919f3";
String authToken = "02149a0f7e576f5ed0269ec7156cdf54";
String fromPhone = "+12542942650";
String destinationPhone="+21699194519";
        setTitle("List article");
     Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", destinationPhone).
        queryParam("From", fromPhone).
        queryParam("Body", "Bienvenue a tuniflix").
        basicAuth(accountSID, authToken).getAsJsonMap();
      if(result.getResponseData() != null) {
    String error = (String)result.getResponseData().get("error_message");
    if(error != null) {
        ToastBar.showErrorMessage(error);
    }
} else {
    ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
}
       
        //search tbadel 3onwen tool bar
//prepare field
TextField searchField;
searchField = new TextField("", "Chercher un article");
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
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
String val = ((Label) ((Container) cmp).getComponentAt(0) ).getText();
System.out.println(   (  (Label) ((Container) cmp).getComponentAt(0) ).getText() );
boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
cmp.setHidden(!show);
cmp.setVisible(show);
}
}
getContentPane().animateLayout(250);
});
        SpanLabel sp = new SpanLabel();

        ArrayList<Article>liste=(serviceArticle.getInstance().getAllArticles());
        for(Article art : liste)
        {
            addButton(art);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    private void addButton(Article a) {
     
        
        Label ta1 = new Label("Titre :" + a.getTitre(),"NewsTopLine");
        Label ta2 = new Label("Description :" + a.getDescription(),"NewsTopLine");
       
        String url="file:/Users/FK Info/Desktop/piidev/Pidev/public/uploads/image";
        Image placeholder=Image.createImage(1150,420);
        EncodedImage enc=EncodedImage.createFromImage(placeholder, false);
        URLImage urlim=URLImage.createToStorage(enc,a.getImg(), url+"/"+a.getImg());
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
    
    

