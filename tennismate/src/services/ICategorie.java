/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface ICategorie {
    
   
    public void ajoutCategorie(Categorie c)throws SQLException;
    public List<Categorie> afficherListeCategorie() throws SQLException;
    public void supprimerCategorie(int id)throws SQLException;
     public void modifierCategorie(Categorie c,int id)throws SQLException;
       public Categorie afficherCategorie(int id) throws SQLException;
}