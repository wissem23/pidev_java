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
public class Participation {

    public static void setEvennement(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int id;
    private String codeticket;
    private User user;
    private Evennements evenements;

    public Participation(int id, String codeticket, User user, Evennements evenements) {
        this.id = id;
        this.codeticket = codeticket;
        this.user = user;
        this.evenements = evenements;
    }
    

    public Participation(String codeticket, User user, Evennements evenements) {
        this.codeticket = codeticket;
        this.user = user;
        this.evenements = evenements;
    }

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeticket() {
        return codeticket;
    }

    public void setCodeticket(String codeticket) {
        this.codeticket = codeticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evennements getEvenements() {
        return evenements;
    }

    public void setEvenements(Evennements evenements) {
        this.evenements = evenements;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Participation other = (Participation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.codeticket, other.codeticket)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.evenements, other.evenements)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""  + codeticket + "          " + evenements.getType() + "          " + user.getFirst_name()+ ' ';
    }

    
    
    
}

    
    

