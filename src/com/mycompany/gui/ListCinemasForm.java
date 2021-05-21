/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Cinema;
import com.mycompany.services.ServiceCinema;
import java.util.ArrayList;

/**
 *
 * @author kkk
 */
public class ListCinemasForm extends BaseForm{
int nbre=2;
     int nbrs=3;
    public ListCinemasForm(Form previous) {
        //AK7EL 
        double[] values = new double[2];
        values[0]=nbre;
    		values[1]=nbrs;
        getStyle().setBgColor(0x2D2D2D);
        
        setTitle("Liste Cinemas");
         ArrayList<Cinema>liste=ServiceCinema.getInstance().getAllCinemas();
        for(Cinema art : liste)
        {
            addButton(art);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    private void addButton(Cinema a) {
     
        
        Label ta1 = new Label("Nom :   " + a.getNom(),"NewsTopLine");
        Label ta2 = new Label("Date creation :  " + a.getDate_creation(),"NewsTopLine");
        Label ta3 = new Label("Adresse :   " + a.getAdresse(),"NewsTopLine");
        Label ta4 = new Label("Email :   " + a.getEmail(),"NewsTopLine");
      add(createLineSeparator(0xeeeeee)); 
      
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        
       
       
        ta1.getStyle().setFgColor(0xfc3468);
        ta1.getStyle().setFont(smallPlainSystemFont);
        ta2.getStyle().setFgColor(0xfffcf3);
        ta2.getStyle().setFont(smallPlainSystemFont);
        ta3.getStyle().setFgColor(0xfffcf3);
        ta3.getStyle().setFont(smallPlainSystemFont);
        ta4.getStyle().setFgColor(0xfffcf3);
        ta4.getStyle().setFont(smallPlainSystemFont);
         
     
        
        add(createLineSeparator(0xeeeeee));
        add(BoxLayout.encloseY(ta1,ta2,ta3,ta4));
        }
    
     public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    
}
