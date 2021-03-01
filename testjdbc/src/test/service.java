/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxapplication1.connexion;

/**
 *
 * @author FK Info
 */
public class service {
    Connection c = connexion.getinstance().getConn();
    
     public void methode()
     {
         PreparedStatement req;
        try {
            req = c.prepareStatement("insert into test(id,nom)values(?,?)");
         req.setInt(1, 3);
         req.setString(2,"khanfour");
     req.execute();
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
}