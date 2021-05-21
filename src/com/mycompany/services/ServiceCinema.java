/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Cinema;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kenza ben debba
 */
public class ServiceCinema {
   // private Form current;
    public ArrayList<Cinema> cinemas;
    
    public static ServiceCinema instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCinema() {
         req = new ConnectionRequest();
    }

    public static ServiceCinema getInstance() {
        if (instance == null) {
            instance = new ServiceCinema();
        }
        return instance;
    }

//Picker datePicker= new Picker();

    public ArrayList<Cinema> parseCinemas(String jsonText){
        try {
            cinemas=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> cinemasListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)cinemasListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Cinema c = new Cinema();
                Picker date = new Picker();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
               // c.setDateCreation(obj.get("dateCreation"));
c.setDate_creation(obj.get("dateCreation").toString());
                c.setAdresse(obj.get("adresse").toString());
                c.setEmail(obj.get("email").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                cinemas.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return cinemas;
    }
    
    public ArrayList<Cinema> getAllCinemas(){
        String url = Statics.BASE_URL+"/AllCinemas/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                cinemas = parseCinemas(new String(req.getResponseData()));
                               
                
//Picker date = new Picker();
//Form f2= new Form(BoxLayout.y());
//Label lDate=new Label("Date: "+ date.getDate().toString());
//f2.add(lDate); 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cinemas;
    }
    
    
    
    
/*    //ajout
    public Cinema DetailCinema(int id, Cinema cinema){
       String url=Statics.BASE_URL +cinema.getId()+"/CinemaId";
        req.setUrl(url);
        
        
String str = new String(req.getResponseData());        
       req.addResponseListener(((evt) -> {
           
           JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader( new String(str).toCharArray()));
                
              cinema.setNom(obj.get("nom").toString());
                    cinema.setAdresse(obj.get("adresse").toString());
                    cinema.setEmail(obj.get("email").toString());
                    
        
    }catch(IOException ex) {
        System.out.println("error related to sql :( "+ ex.getMessage());
    }
            
            System.out.println("data ===" +str);
    
}));
               NetworkManager.getInstance().addToQueueAndWait(req); //execution mta3 l requet sinon yet3ada chay dima nal9awha
               return cinema;
               
}
*/







}