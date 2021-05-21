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
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import static com.sun.javafx.fxml.expression.Expression.add;
import entities.Users;
import service.ServiceUser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AdduserForm extends BaseForm {

    public AdduserForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
          TextField tfn = new TextField("","username");
       
        TextField tfp = new TextField("","password");
       
        TextField tfns = new TextField("","email");
         TextField tfnas = new TextField("","type");
         tfnas.setText("client");
         
        

       
        
        tfn.setSingleLineTextArea(false);
        tfp.setSingleLineTextArea(false);
        tfns.setSingleLineTextArea(false);
       tfnas.setSingleLineTextArea(false);
        
        Button next = new Button("Valider");
        Button signIn = new Button("SignIn");
        Button btnValider = new Button("Add Account");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
         next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfn.getText().length()==0)||(tfp.getText().length()==0)||(tfns.getText().length()==0)||(tfnas.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields",new Command("OK"));
                }
                else {
                    try {
                        Users t = new Users(tfn.getText(),tfp.getText(),tfns.getText(),tfnas.getText());
                         ServiceUser sr = new ServiceUser();
                         sr.addCompte(t);
                            new LoginForm(res).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be string", new Command("OK"));
                    }
                }
            }

           

           
        });
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tfn),
                createLineSeparator(),
                new FloatingHint(tfp),
                createLineSeparator(),
                new FloatingHint(tfns),
                createLineSeparator(),
                new FloatingHint(tfnas),
                createLineSeparator()

               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
       
    }
    
}
