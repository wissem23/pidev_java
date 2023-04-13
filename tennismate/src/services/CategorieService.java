/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Ennou
 */
public class CategorieService implements ICategorie{

    public Connection conx;
    public Statement stm;
   private ProduitService produitService =new ProduitService();
    
    public CategorieService() {
        conx = MyDB.getInstance().getConx();
     
    }

   

    @Override
    public void ajoutCategorie(Categorie c) throws SQLException{
        String req="INSERT INTO categorie(nom,description) VALUES (?,?)";
        
        PreparedStatement ps = conx.prepareStatement(req);
       ps.setString(1, c.getNom());
        
        ps.setString(2, c.getDescription());       
        ps.executeUpdate();
        System.out.println("categorie ajoutée");

    }
    
     public void modifierCategorie(Categorie categorie,int id)throws SQLException {
      
            String req = "UPDATE categorie SET nom = ?, description = ? WHERE id` = ?";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1,categorie.getNom());
            ps.setString(2, categorie.getDescription());
            ps.setInt(3,id);
            ps.executeUpdate();
            System.out.println("categorie updated !");
      
    }
    @Override
    public List<Categorie> afficherListeCategorie() throws SQLException{
        String req= "SELECT * FROM categorie";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        //System.out.println("rs: "+rs.toString());
        List<Categorie> categories = new ArrayList<>();
        while(rs.next()){
           Categorie c = new Categorie(rs.getInt("id"), rs.getString(2));
           c.setProduits(produitService.getProductsByCategorie(1));
           categories.add(c);
        }
        return  categories;
    }

    
    
         @Override
    public Categorie afficherCategorie(int id) throws SQLException{
         String req= "SELECT * FROM categorie where id=1";
         PreparedStatement ps = conx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
       
           Categorie categorie = new Categorie();
        if(rs.next()){
        
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
            categorie.setDescription(rs.getString("description"));
            String requet= "SELECT * FROM produit where categorie_id = 1 ";
         PreparedStatement pst = conx.prepareStatement(requet);
        ResultSet rst = pst.executeQuery();
      // ps.setInt(1, idCategorie);
          List<Produit> produits = new ArrayList<>();
        while(rst.next()){
           Produit produit = new Produit();
            produit.setId(rst.getInt("id"));
            produit.setNom(rst.getString("nom"));
            produit.setPrix(rst.getString("prix"));
            produit.setDescription(rst.getString("description"));
            produit.setImage(rst.getString("image"));
            produit.setQstock(rst.getString("qtestock"));

            System.out.println(produit);
         
            produits.add(produit);
        }
            categorie.setProduits(produits);
        }
        return categorie;
  
    }
 
    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM categorie WHERE id =" + id;
           PreparedStatement ps = conx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
   



 
   
}
