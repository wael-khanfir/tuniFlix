/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author FK Info
 */
public class Home extends Form {

    public Home(Form current) {
        this.current = current;
    }
Form current;
    public Home() {
        current=this;
        setTitle("Tuniflix");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnListProjections=new Button("List des films");
         Button btnListArticles=new Button("List des Articles");
        btnListProjections.addActionListener(e->new Listprojections(current).show());
         btnListArticles.addActionListener(e->new Listarticle(current).show());
        addAll(btnListProjections,btnListArticles);
    }
    
    
}
