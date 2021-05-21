/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Feedback;
import com.mycompany.services.ServiceFeedback;
import java.util.Date;


/**
 *
 * @author kenza ben debba
 */
public class AjoutFeedbackForm extends BaseForm{
    
    Form current;
    
    public AjoutFeedbackForm(Resources res){
        
        
         super("Newsfeed",BoxLayout.y()); //heritage men NewsFeed w l formlire verticale
        
        
        Toolbar tb= new Toolbar(true);
        current= this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Feedback");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e-> {
            
        });
        
        
        addSideMenu(res);
        Tabs swipe= new Tabs();
        Label s1= new Label();
        Label s2= new Label();
        
        addTab(swipe ,s1, res.getImage("pathe.jpg"),"","",res);
        
        //SOME DESIGN CODE
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(("    "), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
          
          
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        //
        
        TextField nom= new TextField("","entrer nom  :");
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom",nom);
        
        TextField email= new TextField("","entrer email :");
        email.setUIID("TextFieldBlack");
        addStringValue("Email",email);
        
        TextField message= new TextField("","entrer message :");
        message.setUIID("TextFieldBlack");
        addStringValue("Message",message);
        
        
        
        
        
     
        Button btnAjouter = new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        //onclick Button event
        btnAjouter.addActionListener((e) -> {
            
            try{
                if(nom.getText().equals("") || email.getText().equals("") || message.getText().equals("") ){
                    Dialog.show("Veuillez verifier les donnees","","Annuler","Ok");
                }
                else{
                    InfiniteProgress ip= new InfiniteProgress();//loading adter insert date
                    
                    final Dialog iDialog= ip.showInfiniteBlocking();
                    
                   
                    Feedback f= new Feedback(
                            String.valueOf(nom.getText()).toString(),
                            String.valueOf(email.getText()).toString(),
                            String.valueOf(message.getText()).toString(),
                          
                    0);
                    System.out.println("data feedback == "+f);
                    
                    //appele methodeajjoutreclamation
                    ServiceFeedback.getInstance().ajoutFeedback(f);
                    iDialog.dispose(); //na7iw oading ba3d ma3malna ajout
                    
                    //lena na3mlou affichage liste feedback
                    new ListFeedbackForm(res).show();
                    
                    
                    
                    refreshTheme();
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        });
         //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }       
        
        

    private void addStringValue(String s,Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
        .add(BorderLayout.CENTER, v));
      
        add(createLineSeparator(0xeeeeee));
        
        }

    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
    //    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {

        
        int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        if(image.getHeight() < size){ 
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
        image = image.scaledHeight(Display.getInstance().getDisplayWidth() / 2);
        }
        
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("","ImageOverlay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                overLay,
                BorderLayout.south(
                BoxLayout.encloseY(
                new SpanLabel(text,"LargeWhiteText"),
               
                spacer
                
                )
            )
        );
        swipe.addTab("",res.getImage("pathe.jpg"),page1);
    }
    
    public void bindButtonSelection(Button btn , Label l){
        
        btn.addActionListener(e -> { 
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
        } 
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);   
        l.getParent().repaint(); 
    }
    
    //SIDE MENU
    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("kenza.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Nos Cinemas", FontImage.MATERIAL_SCREEN_SHARE, e -> new HomeForm(res).show());
        tb.addMaterialCommandToSideMenu("Laisser Un Feedback", FontImage.MATERIAL_MESSENGER , e -> new AjoutFeedbackForm(res).show());

        //WIEM
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_FASTFOOD, e -> new HomeForm_1(res).show());
        //ISSAM
        tb.addMaterialCommandToSideMenu("Log Out", FontImage.MATERIAL_LOGOUT, e -> new LoginForm(res).show());
    }
    
    
    
    
    
    }
    

