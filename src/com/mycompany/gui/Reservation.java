/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author FK Info
 */
public class Reservation extends Form {
    public Reservation(Form previous) {
        setTitle("espace Reservation");
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
}
