/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author ISSAM
 */


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Encapsulation;

/**
 *
 * @author Wael Belhadj
 */
public class ProfilForm extends Form{

    ProfilForm(Resources res) {
        
         Button backButton = new Button("back");
        
        
       Label label1= new Label(Integer.toString(Encapsulation.getUserId()));
       Label label12 =new Label(Encapsulation.getUsername());
       Label label2= new Label(Encapsulation.getPassword());
       Label label3=   new Label(Encapsulation.getEmail());
       Label label4=  new Label(Encapsulation.getType());
      
       
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).show();
             
               
                   
            }
        });
       
        Container by = BoxLayout.encloseY(
                
                
               label1,label12,label3,label4,backButton
               
                
                ) ;    
        add( by);
       
    }
    
}
