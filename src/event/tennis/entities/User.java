/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.entities;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
  public class User {
    private int id,cin,telephone;
    private String email , password, first_name,last_name,adress;
    private int is_verified;
    private String roles;

    public User(int id, int cin, int telephone, String email, String password, String first_name, String last_name, String adress, int is_verified, String roles) {
        this.id = id;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.adress = adress;
        this.is_verified = is_verified;
        this.roles = roles;
    }

    public User(int cin, int telephone, String email, String password, String first_name, String last_name, String adress, int is_verified, String roles) {
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.adress = adress;
        this.is_verified = is_verified;
        this.roles = roles;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (this.telephone != other.telephone) {
            return false;
        }
        if (this.is_verified != other.is_verified) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.first_name, other.first_name)) {
            return false;
        }
        if (!Objects.equals(this.last_name, other.last_name)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", telephone=" + telephone + ", email=" + email + ", password=" + password + ", first_name=" + first_name + ", last_name=" + last_name + ", adress=" + adress + ", is_verified=" + is_verified + ", roles=" + roles + '}';
    }

    public User(int id, String first_name) {
        this.id = id;
        this.first_name = first_name;
    }
   
    

    
    
    
    
}
