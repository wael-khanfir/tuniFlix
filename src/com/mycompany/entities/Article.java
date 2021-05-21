/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author FK Info
 */
public class Article {
    int id;
    String titre;
    String description;
    String img;

    public Article(int id, String titre, String description, String img) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.img = img;
    }
    
public Article() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
 @Override
    public String toString() {
        return "article{" + "id=" + id + ", titre=" + titre + ", name=" + description + ", img=" +img + '}';
    }
    

}
