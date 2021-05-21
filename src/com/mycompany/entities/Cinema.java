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
public class Cinema {
    private int id;
    private String nom;
    private String date_creation;
    private String adresse;
    private String email;

    public Cinema() {
    }

    
    public Cinema(int id, String nom, String date_creation, String adresse, String email) {
        this.id = id;
        this.nom = nom;
        this.date_creation = date_creation;
        this.adresse = adresse;
        this.email = email;
    }

    public Cinema(String nom, String date_creation, String adresse, String email) {
        this.nom = nom;
        this.date_creation = date_creation;
        this.adresse = adresse;
        this.email = email;
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

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return  "\n"+ "cinema{"  + ", nom= " + nom+ "\n"+
                ", date de creation= " + date_creation + "\n"+
                ",adresse= " + adresse +  "\n"+
                ", email= "+ email +  "\n"+
                '}';
    }
      
     
    
    
    
}
