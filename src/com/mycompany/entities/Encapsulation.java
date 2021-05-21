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
public class Encapsulation {
    protected static int userId;
    protected static String username;
    protected static String password;
    protected static String email;
    protected static String type ;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int UserId) {
        Encapsulation.userId = UserId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Encapsulation.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Encapsulation.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Encapsulation.email = email;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Encapsulation.type = type;
    }

    public Encapsulation() {
    }

       public Encapsulation(int userId,String username, String password, String email, String type ) {
        this.userId = userId;
        this.username=username;
        this.password=password;
        this.email = email;
        this.type= type;
            }
        
       public static void setCompte(Users users )
    {
        Encapsulation.userId = users.getUserId();
        Encapsulation.username= users.getUsername();
        Encapsulation.password = users.getPassword();
        Encapsulation.email = users.getEmail();
        Encapsulation.type = users.getType();
      
    }
   
    
}
