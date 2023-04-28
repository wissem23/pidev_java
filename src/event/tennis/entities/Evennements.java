/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.entities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author LENOVO
 */

    public class Evennements {

    public static Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String type;
    private String description;
    private String image;
    private LocalDate date_debut;
    private LocalDate date_fin; 
    private int liked ;
    private int totalelike;

    public Evennements() {
    }

    public Evennements(int id, String type, String description, String image, LocalDate date_debut, LocalDate date_fin,int liked, int totalelike) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        
        this.liked = liked;
        this.totalelike = totalelike;
    }

    public Evennements(String type, String description, String image, LocalDate date_debut, LocalDate date_fin, int liked, int totalelike) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.liked = liked;
        this.totalelike = totalelike;
    }

    public Evennements(String text, LocalDate value, LocalDate value0, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evennements(String text, LocalDate value, LocalDate value0, String text0, String text1, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evennements(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    
    public int getliked() {
        return liked;
    }

    public void setliked(int liked) {
        this.liked = liked;
    }
    public int gettotalelike() {
        return totalelike;
    }

    public void settotalelike(int totalelike) {
        this.totalelike = totalelike;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
       
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
        final Evennements other = (Evennements) obj;
        if (this.id != other.id) {
            return false;
        }
        
        if (this.liked != other.liked) {
            return false;
        }
        if (this.totalelike != other.totalelike) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + type + "           " + description + "           " + image + "        " + date_debut + "       " + date_fin + "       " + liked + "       " + totalelike + ' ';
    }

    public Object toLowerCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}




    
    

