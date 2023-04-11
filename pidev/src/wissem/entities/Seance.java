/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.entities;

import java.sql.*;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author wisse
 */
public class Seance {



    private int id;
    private Date date;
    private double duration;
    private String level;
    private String adresse;
    private String coach_name;
    private int people_nbre;
    private boolean is_available;
    private String description;
    private double price;
    
    public Seance(){
        
    }
    public Seance(int id, Date date, double duration, String level, String adresse, String coach_name, int people_nbre, boolean is_available, String description, double price) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.level = level;
        this.adresse = adresse;
        this.coach_name = coach_name;
        this.people_nbre = people_nbre;
        this.is_available = is_available;
        this.description = description;
        this.price = price;
    }
     public Seance(Date date, double duration, String level, String adresse, String coach_name, int people_nbre, boolean is_available, String description, double price) {
       
        this.date = date;
        this.duration = duration;
        this.level = level;
        this.adresse = adresse;
        this.coach_name = coach_name;
        this.people_nbre = people_nbre;
        this.is_available = is_available;
        this.description = description;
        this.price = price;
    }

    public Seance(String _1530) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCoachName() {
        return coach_name;
    }

    public void setCoachName(String coach_name) {
        this.coach_name = coach_name;
    }

    public int getPeopleNbre() {
        return people_nbre;
    }

    public void setPeopleNbre(int people_nbre) {
        this.people_nbre = people_nbre;
    }

    public boolean isIsAvailable() {
        return is_available;
    }

    public void setIsAvailable(boolean is_available) {
        this.is_available = is_available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", date=" + date +
                ", duration=" + duration +
                ", level='" + level + '\'' +
                ", adresse='" + adresse + '\'' +
                ", coach_name='" + coach_name + '\'' +
                ", people_nbre=" + people_nbre +
                ", is_available=" + is_available +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
    
    /* @Override
    public int hashCode() {
        return Objects.hash(id, date, duration, level, adresse, coach_name, people_nbre, is_available, description, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return id == seance.id && Double.compare(seance.duration, duration) == 0 && people_nbre == seance.people_nbre && is_available == seance.is_available && Double.compare(seance.price, price) == 0 && Objects.equals(date, seance.date) && Objects.equals(level, seance.level) && Objects.equals(adresse, seance.adresse) && Objects.equals(coach_name, seance.coach_name) && Objects.equals(description, seance.description);
    }
*/

 /*   @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.duration) ^ (Double.doubleToLongBits(this.duration) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.level);
        hash = 59 * hash + Objects.hashCode(this.adresse);
        hash = 59 * hash + Objects.hashCode(this.coach_name);
        hash = 59 * hash + this.people_nbre;
        hash = 59 * hash + (this.is_available ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final Seance other = (Seance) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.duration) != Double.doubleToLongBits(other.duration)) {
            return false;
        }
        if (this.people_nbre != other.people_nbre) {
            return false;
        }
        if (this.is_available != other.is_available) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.coach_name, other.coach_name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    */
    
}
