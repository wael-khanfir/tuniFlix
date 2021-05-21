/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ISSAM
 */
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class HomeForm extends Form {
   public HomeForm(Resources theme)
   {
Button ProfilButton = new Button("profile");
        Button logoutBtn = new Button("logout");
        
         ProfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfilForm(theme).show();
             
               
                   
            }
        });
         add(ProfilButton);
         
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new LoginForm(theme).show();
                
            }
        });
           
        add(logoutBtn);
   
   }  
}
