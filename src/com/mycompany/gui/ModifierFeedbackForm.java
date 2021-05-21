/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceFeedback;

/**
 *
 * @author kenza ben debba
 */
public class ModifierFeedbackForm extends BaseForm{
    
    Form current;
    public ModifierFeedbackForm(Resources res, Feedback f){
        
        super("Newsfeed",BoxLayout.y()); //heritage men NewsFeed w l formlire verticale
        
        Toolbar tb= new Toolbar(true);
        current= this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modification Feedback");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        TextField nom= new TextField(f.getNom(), "Nom" , 20, TextField.ANY);
        TextField email= new TextField(f.getEmail(), "Email" , 20, TextField.ANY);
        TextField message= new TextField(f.getMessage(), "Message" , 20, TextField.ANY);
        TextField etat= new TextField(String.valueOf(f.getEtat()), "etat" , 20, TextField.ANY);

        //etat bech na3Mlu combobox bech ynajem ladmin yapprouvi walle
        ComboBox etatCombo= new ComboBox();
        etatCombo.addItem("Nom Traité");
        etatCombo.addItem("Traité");
        
        if(f.getEtat() == 0) {
            etatCombo.setSelectedIndex(0);
        }
        else
            etatCombo.setSelectedIndex(1);
        

        nom.setUIID("NewsTopLine");
        email.setUIID("NewsTopLine");
        message.setUIID("NewsTopLine");
        etat.setUIID("NewsTopLine");
        
        nom.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        message.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
        
        //Event onclick Modifier
        btnModifier.addPointerPressedListener(l -> {
            f.setNom(nom.getText());
            f.setEmail(email.getText());
            f.setMessage(message.getText());
            
            if(etatCombo.getSelectedIndex() == 0){
                f.setEtat(0);
              
            }else 
                f.setEtat(1);
            
            
            
        
        //appel focntion modifier feedback men service
        
        if(ServiceFeedback.getInstance().modifierFeedback(f)){
            //if true
            new ListFeedbackForm(res).show();
        }
        });
        Button btnAnnuler= new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListFeedbackForm(res).show();
        });
        
        Label l2= new Label("");
        Label l3= new Label("");
        Label l4= new Label("");
        Label l5= new Label("");
        Label l1= new Label();
        
        Container content= BoxLayout.encloseY(
        l1,l2,
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(message),
                createLineSeparator(),
                etatCombo,
                createLineSeparator(),// ligne de seperation
                //l4,l5,
                btnModifier,
                btnAnnuler
                
                
                
                );
        add(content);
        show();
        
        
        
        
    }
}
