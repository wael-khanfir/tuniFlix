/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author kenza ben debba
 */
public class AjoutCinemaForm extends BaseForm{
    
    Form current;
    int nbre=2;
     int nbrs=3;
    public AjoutCinemaForm(Resources res) {
        super("Newsfeed",BoxLayout.x()); //heritage men NewsFeed w l formlire verticale
        
        double[] values = new double[2];
        values[0]=nbre;
    		values[1]=nbrs;
        getStyle().setBgColor(0x2D2D2D);
        
        Toolbar tb= new Toolbar(true);
        current= this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclaation");
        getContentPane().setScrollVisible(false);
        
        
        TextField nom= new TextField("","entrer nom cinema :");
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom",nom);
        
        TextField adresse= new TextField("","entrer adresse :");
        adresse.setUIID("TextFieldBlack");
        addStringValue("Adresse",adresse);
        
        TextField email= new TextField("","entrer email :");
        adresse.setUIID("TextFieldBlack");
        addStringValue("Email",email);
          
        
        
    }

    private void addStringValue(String s,Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER, v));
      
        add(createLineSeparator(0xeeeeee));
        
        }
}
