package com.mycompany.gui;

import com.codename1.ui.util.Resources;
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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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
import java.util.ArrayList;
import java.util.Date;


public class ListFeedbackForm extends BaseForm{
    
    Form current;
    
    public ListFeedbackForm(Resources res) {
        
        
     super("Newsfeed",BoxLayout.y()); //heritage men NewsFeed w l formlire verticale
     
     
        
        Toolbar tb= new Toolbar(true);
        current= this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Feedback");
        getContentPane().setScrollVisible(false);
        
        //SIDE MENU
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
        //appel methode affichage
        ArrayList<Feedback>list= ServiceFeedback.getInstance().affichageFeedback();
        for(Feedback rec: list) {
            String urlImage="pathe.jpg"; 
            
            Image placeHolder= Image.createImage(120, 90);
            EncodedImage enc= EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            
            
            addButton(urlim,rec,res);
            ScaleImageLabel image= new ScaleImageLabel(urlim);
            Container containerImg= new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
        }
        
        
        
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

    private void addButton(Image img,Feedback rec, Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image= new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        
        /*TextArea ta= new TextArea(nom);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        
        TextArea ti= new TextArea(email);
        ti.setUIID("NewsTopLine");
        ti.setEditable(false);
        
        TextArea tu= new TextArea(message);
        tu.setUIID("NewsTopLine");
        tu.setEditable(false);
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta,ti,tu));*/
        
         Label nomTxt= new Label("nom : "+rec.getNom(),"NewsTopLine2");
        Label emailTxt= new Label("email : "+rec.getEmail(),"NewsTopLine2");
        Label messageTxt= new Label("message : "+rec.getMessage(),"NewsTopLine2");
        Label etatTxt= new Label("etat :" +rec.getEtat(), "NewsTopLine2");
        
        nomTxt.getStyle().setFgColor(0xfc3468);
        
        
        createLineSeparator();
        
        if(rec.getEtat() ==0) {
            etatTxt.setText("non Traitée");
        }
        else
            etatTxt.setText("Traitée");
         
        
        
        //mena bde lmochkol
        //supprimer
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        
        // click delete icon
        lSupprimer.addPointerPressedListener(l ->{
    Dialog dig = new Dialog("Suppression");
    if(dig.show("Suppression","Vous voulez supprimer ce Feedback ?","Annuler","Oui")){
    dig.dispose();
    }
    else{
    dig.dispose();
    //n3aytou l supprimer men service Facture
    if(ServiceFeedback.getInstance().deleteFeedback(rec.getId()))
    { new ListFeedbackForm(res).show();
    }
    }
    
    });
        
        //Update icon
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        
        modifierStyle.setFgColor(0xf7ad02);
        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(modifierImage);
        lModifier.setTextPosition(LEFT);
        
        
       lModifier.addPointerPressedListener(l ->{
       //System.out.println("hello update");
       new ModifierFeedbackForm(res,rec).show();
         
    
    });
        
        
        
        
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(nomTxt),
                BoxLayout.encloseX(emailTxt),
                BoxLayout.encloseX(messageTxt),
                
                BoxLayout.encloseX(etatTxt,lModifier,lSupprimer)));
        
        
        
       
        
        add(cnt);
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
        tb.addMaterialCommandToSideMenu("Nos Cinemas", FontImage.MATERIAL_EXIT_TO_APP, e -> new HomeForm(res).show());
        tb.addMaterialCommandToSideMenu("Laisser Un Feedback", FontImage.MATERIAL_MESSENGER , e -> new AjoutFeedbackForm(res).show());
        
        //WAEL
        tb.addMaterialCommandToSideMenu("Films et Articles", FontImage.MATERIAL_ARTICLE, e -> new Home(res).show());
        //WIEM
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_FASTFOOD, e -> new HomeForm_1(res).show());
        //ISSAM
        tb.addMaterialCommandToSideMenu("Log Out", FontImage.MATERIAL_LOGOUT, e -> new LoginForm(res).show());
        
        
    }
    
    
    
}