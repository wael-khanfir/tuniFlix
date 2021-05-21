/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author kenza ben debba
 */
public class Feedback {
     private int id;
    private String nom;
    private String email;
    private String message;
    private int etat;
    

    public Feedback(int id, String nom, String email, String message, int etat) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.message = message;
        this.etat = etat;
      
    }

    public Feedback() {
    }

    public Feedback(String nom, String email, String message, int etat) {
        this.nom = nom;
        this.email = email;
        this.message = message;
        this.etat = etat;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

  
    
    
    
}
