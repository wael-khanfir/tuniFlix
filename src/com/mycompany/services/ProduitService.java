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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.lang.NullPointerException;


/**
 *
 * @author ASUS
 */
public class ProduitService {
   /* public static ProduitService instance = null;
    public static boolean  resultOK = true;
    
    private ConnectionRequest req;
    
    public static ProduitService getInstance()
    { if(instance == null)
        instance = new ProduitService();
    return instance;
    }
    
    public ProduitService()
    { req = new ConnectionRequest();
    }
         
   //affichage
    public ArrayList<Produit> affichageProduit()
    {
      ArrayList<Produit> result = new ArrayList<>();
      String url= Statics.BASE_URL+"/AllProduits";
      req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>()
      {
          @Override
          public void actionPerformed(NetworkEvent evt) {
             JSONParser jsonp ;
             jsonp = new JSONParser();
             
             try {
             Map<String,Object> mapProduit = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
             List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapProduit.get("root");
             for(Map<String, Object> obj : listOfMaps)
                 {
                 Produit fact = new Produit();
                 
                 
                 
                 float id = Float.parseFloat(obj.get("id").toString());
                 String nom = (String) obj.get("nom");
                 String description = (String) obj.get("description");
                  float prix = Float.parseFloat(obj.get("prix").toString());
                
                 
                
                 
                 
               /*  String DateConverter = obj.get("date_paiement").toString().substring(obj.get("date_paiement").toString().indexOf("timestamp") + 10 , obj.get("date_paiement").toString().lastIndexOf(")"));
                 Date currenTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                 
                 
                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                 String dateString = formatter.format(currenTime);
                 //fact.setDate_paiement(dateString);
                 
       
                 
          
                 
              
              
                fact.setId((int) id);
                fact.setNom(nom);
                fact.setDescription(description);
               fact.setPrix((int) prix);
                
                //insert data into ArrayList result
                result.add(fact);
                 }
             }catch(Exception ex){
             
             ex.printStackTrace();
             
             
             }
             
             
          }
      });
      
      
      NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    }  
}*/

private ConnectionRequest connectionRequest;

  
      int a = 0 ;
      
    private static int id = 13; //Session.getId();
    public static ProduitService instance = null;
  public static ProduitService getInstance()
    { if(instance == null)
        instance = new ProduitService();
    return instance;
    }
 public ArrayList<Produit> getProduit(String json) {
        
        ArrayList<Produit> listEvent = new ArrayList<>();
        
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
            
            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
                
                

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                Picker date = new Picker();
//           String datedeb = formater.format(e.getDatefin());           
        
         
              
                  // e.setDatefin((Date) obj.get(datedeb));

                
               // e.setDatefin(obj.get("datefin").toString());
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                e.setImage(obj.get("image").toString());
//                 e.setImg(obj.get("img").toString());
                e.setPrix((int) prix);
              
                //e.setDatefin(obj.get("datefin").toString());
                
                
                listEvent.add(e);
                
            }
        } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;
        
    }
    ArrayList<Produit> listProduit = new ArrayList<>();
    
    public ArrayList<Produit> getList2() {
       
      String url= Statics.BASE_URL+"/AllProduits";
     
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                listProduit = ser.getProduit(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }

   
    
    public Produit getProduit2(String json) {
        Produit e = new Produit();        
        
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                 Picker date = new Picker();
         
             
                   //e.setDatefin((Date) obj.get(datedeb));
                //e.setDatefin(obj.get("datefin").toString());
             //   e.setDatefin(obj.get("datefin").toString());
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                e.setImage(obj.get("image").toString());
                e.setPrix((int) prix);
             e.setImg(obj.get("img").toString());
            
        } catch (IOException ex) {
        }
        return e;
        
    }
    public int getNbparticip2(String json) {
                
         int  e = 0 ;
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            float id = Float.parseFloat(obj.get("nb").toString());
              e = (int)id;
            
        } catch (IOException ex) {
        }
        return e;
        
    }
     public int getPparticipent(int id, int iduser){
      
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/GestionBuvette/web/app_dev.php/findparticip/"+ id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 ProduitService ser = new ProduitService();
                 a = ser.getNbparticip2(new String(con.getResponseData()));
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
     return a ;
    }
   
}
  