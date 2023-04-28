/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.entities;



/**
 *
 * @author LENOVO
 */
public class Utilisateur {
     public int id;
     private String role;
     private String email;
     private String password;

    public Utilisateur(int utilisateur_id, String role, String email, String password) {
        this.id = utilisateur_id;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String role, String email, String password) {
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public Utilisateur() {
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "utilisateur_id=" + id + ", role=" + role + ", email=" + email + ", password=" + password + '}';
    }
     
}

