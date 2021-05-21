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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Feedback;
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
public class ServiceFeedback {
    
       //singleton
    public static ServiceFeedback instance=null;
    public static boolean  resultOK = true;
    
    //initialisatioin conenction request
    private ConnectionRequest req;
    
    public static ServiceFeedback getInstance() {
        if(instance == null)
            instance = new ServiceFeedback();
        return instance;
    }
    
    public ServiceFeedback(){
    
    req= new ConnectionRequest();
    
}
    //ajout
    public void ajoutFeedback(Feedback feedback) {
        
        String url=Statics.BASE_URL+"/addFeedbackJSON/new?nom="+feedback.getNom()+"&email="+feedback.getEmail()+"&message="+feedback.getMessage();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());//response json
            System.out.println("data == " +str);

//
        });
        NetworkManager.getInstance().addToQueueAndWait(req); //execution mta3 l requet sinon yet3ada chay dima nal9awha
    
    }
    
   //affichage
    public ArrayList <Feedback> affichageFeedback()
    {
        ArrayList <Feedback> result = new ArrayList <>();
         
        String url = Statics.BASE_URL+"/AllFeedbacks";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener <NetworkEvent>() {
        
        @Override
        public void actionPerformed(NetworkEvent evt){
            JSONParser jsonp;
            jsonp = new JSONParser();
            
            try {
                Map<String, Object> mapFeedbacks = jsonp.parseJSON(new CharArrayReader( new String(req.getResponseData()).toCharArray()));
                
                List <Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapFeedbacks.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    Feedback re = new Feedback();
                    
                    //dima id fi codenameone1 float
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String nom= obj.get("nom").toString();
                    String email= obj.get("email").toString();
                    String message= obj.get("message").toString();
                    float etat = Float.parseFloat(obj.get("etat").toString());
                    
                    
                    re.setId((int)id);
                    re.setNom(nom);
                    re.setEmail(email);
                    re.setMessage(message);
                    
                    re.setEtat((int)etat);
                    
                   


            //insert data into ArrayList result
            result.add(re);

                }
                       
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    });
                NetworkManager.getInstance().addToQueueAndWait(req); //execution mta3 l requet sinon yet3ada chay dima nal9awha
                return result;   
                
                
    }
    
    //Detail Feedback
    public Feedback DetailFeedback(int id, Feedback feedback){
       String url=Statics.BASE_URL +feedback.getId()+"/FeedbackId";
        req.setUrl(url);
        
        
        String str = new String(req.getResponseData());        
       req.addResponseListener(((evt) -> {
           
           JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader( new String(str).toCharArray()));
                
              feedback.setNom(obj.get("nom").toString());
                    feedback.setEmail(obj.get("email").toString());
                    feedback.setMessage(obj.get("message").toString());
                    feedback.setEtat(Integer.parseInt(obj.get("etat").toString()));

        
    }catch(IOException ex) {
        System.out.println("error related to sql :( "+ ex.getMessage());
    }
            
            System.out.println("data ===" +str);
    
}));
               NetworkManager.getInstance().addToQueueAndWait(req); //execution mta3 l requet sinon yet3ada chay dima nal9awha
               return feedback;
               
}
    //delete
    public boolean deleteFeedback(int id){
               String url=Statics.BASE_URL +"/deleteFeedbackJson/"+id;
               req.setUrl(url);
               req.addResponseListener(new ActionListener<NetworkEvent>() { 
       
        @Override
        public void actionPerformed(NetworkEvent evt) {
             req.removeResponseListener(this);
        }
    
    
    
    }
    
    );
    
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
               
               

    }
    
    
   //update
    public boolean modifierFeedback(Feedback feedback){
     
       String url=Statics.BASE_URL+"/updateFeedbackJson/"+feedback.getId() +"?nom="+feedback.getNom()+"&email="+feedback.getEmail()+"&message="+feedback.getMessage()+"&etat="+feedback.getEtat();
       req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() { 
        @Override
        public void actionPerformed(NetworkEvent evt) {
         resultOK=req.getResponseCode() == 200;//code response http 200 ok
         req.removeResponseListener(this);
        }
    
    
    });
     NetworkManager.getInstance().addToQueueAndWait(req);//execution mta3 l requete
    return resultOK; 
       
    }
    
    
}
