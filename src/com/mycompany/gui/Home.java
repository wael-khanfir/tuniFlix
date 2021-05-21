/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author FK Info
 */
public class Home extends Form {

    public Home(Form current) {

        this.current = current;
        
    }
    
    
Form current;
int nbre=2;
     int nbrs=3;
    public Home(Resources res) {
  super("  ", BoxLayout.y());
  
  double[] values = new double[2];
    		values[0]=nbre;
    		values[1]=nbrs;
        getStyle().setBgColor(0x2D2D2D);
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Interface Films Et Articless");
        getContentPane().setScrollVisible(false);
        
        addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
      
        
       
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("   ", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("    ", barGroup);
        featured.setUIID("SelectBar");
        RadioButton Cinema = RadioButton.createToggle("    ", barGroup);
        Cinema.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("   ", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("    ", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(5, all, featured,Cinema, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Cinema.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(Cinema, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(Cinema, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });      






        current=this;
        setTitle("Interface Films Et Articles");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnListProjections=new Button("List des films");
         Button btnListArticles=new Button("List des Articles");
        btnListProjections.addActionListener(e->new Listprojections(current).show());
         btnListArticles.addActionListener(e->new Listarticle(current).show());
        addAll(btnListProjections,btnListArticles);
    }
    
    
    //el 3 li ta7t l affichage side menu
    
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
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
                        new Label(res.getImage("wael.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Nos Cinemas", FontImage.MATERIAL_SCREEN_SHARE, e -> new HomeForm(res).show());
        tb.addMaterialCommandToSideMenu("Laisser Un Feedback", FontImage.MATERIAL_MESSENGER , e -> new AjoutFeedbackForm(res).show());
        
        //WAEL
        tb.addMaterialCommandToSideMenu("Films et Articles", FontImage.MATERIAL_ARTICLE, e -> new Home(res).show());
        
        //WIEM
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_FASTFOOD, e -> new HomeForm_1(res).show());
        //ISSAM
        tb.addMaterialCommandToSideMenu("Log Out", FontImage.MATERIAL_LOGOUT, e -> new LoginForm(res).show());  
                
                
    }
    
    
    
}
