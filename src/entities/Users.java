/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ISSAM
 */
public class Users {
     private int userId;
    private String username;
    private String password;
    private String email;
    private String type;

    

    public Users() {
    }

    public Users(int userId, String username, String password, String email,String type) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }
     public Users( String username, String password, String email,String type) {
     
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
         this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", type=" + type + '}';
    }

    public Users(int userId) {
        this.userId = userId;
    }

    public Users(String password, String email ) {
        this.password = password;
        this.email = email;
      
    }

    
   
    
}
