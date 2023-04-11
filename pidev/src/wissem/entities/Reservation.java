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
public class Reservation {
    private int id; 
    private int seance_id;
    private int user_id ;
    private boolean is_status;
    private Date create_at;
    
    public Reservation(){
        
    }

    public Reservation(int id, int seance_id, int user_id, boolean is_status, Date create_at) {
        this.id = id;
        this.seance_id = seance_id;
        this.user_id = user_id;
        this.is_status = is_status;
        this.create_at = create_at;
    }

    public Reservation(int seance_id, int user_id, boolean is_status, Date create_at) {
        this.seance_id = seance_id;
        this.user_id = user_id;
        this.is_status = is_status;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isIs_status() {
        return is_status;
    }

    public void setIs_status(boolean is_status) {
        this.is_status = is_status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", seance_id=" + seance_id + ", user_id=" + user_id + ", is_status=" + is_status + ", create_at=" + create_at + '}';
    }
/*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + this.seance_id;
        hash = 19 * hash + this.user_id;
        hash = 19 * hash + (this.is_status ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.create_at);
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.seance_id != other.seance_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.is_status != other.is_status) {
            return false;
        }
        if (!Objects.equals(this.create_at, other.create_at)) {
            return false;
        }
        return true;
    }
    */
    
}
