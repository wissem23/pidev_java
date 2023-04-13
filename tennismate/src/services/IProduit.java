/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface IProduit {
    
   
    public void ajoutProduit(Produit p,int idCategorie)throws SQLException;
    public List<Produit> afficherListeP() throws SQLException;
     public List<Produit> getProductsByCategorie(int idCategorie) throws SQLException;
     public void supprimerProduit(int id) throws SQLException;
     public void modifierProduit(Produit p,int id)throws SQLException;
}