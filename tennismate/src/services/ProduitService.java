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
public class ProduitService implements IProduit{

    public Connection conx;
    public Statement stm;
    CategorieService categorieService ;
    public ProduitService() {
        conx = MyDB.getInstance().getConx();
       
    }
    

    @Override
    public void ajoutProduit(Produit p,int idCategorie) throws SQLException{
        String req="INSERT INTO produit(nom, prix,description,qtestock,image,categorie_id) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2,p.getPrix());
        ps.setString(3, p.getDescription());
        ps.setString(4,p.getQstock());
        ps.setString(5,p.getImage());
        ps.setInt(6,idCategorie);
        ps.executeUpdate();
        ps.close();
        System.out.println("produit ajoutée");
        

    }
    
    @Override
    public List<Produit> afficherListeP() throws SQLException{
        String req= "SELECT * FROM produit";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Produit> produits = new ArrayList<>();
        categorieService = new CategorieService();
        while(rs.next()){
           Produit produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setNom(rs.getString("nom"));
            produit.setPrix(rs.getString("prix"));
            produit.setDescription(rs.getString("description"));
            produit.setImage(rs.getString("image"));
            produit.setQstock(rs.getString("qtestock"));
         int id_cat = rs.getInt("categorie_id");
           Categorie categorie = categorieService.afficherCategorie(id_cat);
           produit.setCategorie(categorie);
            
            System.out.println("AAAAAAAAAAAAAa");
            produits.add(produit);
        }
        
        return  produits;
        
    }

 @Override
    public void supprimerProduit(int id) {
        try {
            
            // Find referencing records in lignedecommande table
PreparedStatement stmt = conx.prepareStatement("SELECT * FROM lignedecommande WHERE produit_id = ?");
stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();

// Delete referencing records from lignedecommande table
while (rs.next()) {
    int ligneId = rs.getInt("id");
    PreparedStatement deleteStmt = conx.prepareStatement("DELETE FROM lignedecommande WHERE id = ?");
    deleteStmt.setInt(1, ligneId);
    deleteStmt.executeUpdate();
    deleteStmt.close();
}
//delete product
             PreparedStatement ps = conx.prepareStatement("DELETE FROM produit WHERE id = ?");
             ps.setInt(1, id);
          ps.executeUpdate();
            System.out.println("product deleted !");
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      @Override
    public void modifierProduit(Produit produit,int id) {
        try {
            String req = "UPDATE produit SET nom = ?, prix = ? ,description = ?,qtestock = ?,image=? where id=?";
            PreparedStatement ps = conx.prepareStatement(req);
          //  ps.setTimestamp(1, Timestamp.valueOf(seance.getDate()));
            ps.setString(1,produit.getNom());
            ps.setString(2,produit.getPrix());
            ps.setString(3, produit.getDescription());
             ps.setString(4, produit.getQstock()); 
                          ps.setString(5, produit.getImage()); 
              ps.setInt(6,id);
           
         
            ps.executeUpdate();
            System.out.println("produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 

    @Override
    public List<Produit> getProductsByCategorie(int idCategorie) throws SQLException {
 String req= "SELECT * FROM produit where categorie_id =  "+ idCategorie;
         PreparedStatement ps = conx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
      // ps.setInt(1, idCategorie);
          List<Produit> produits = new ArrayList<>();
        while(rs.next()){
           Produit produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setNom(rs.getString("nom"));
            produit.setPrix(rs.getString("prix"));
            produit.setDescription(rs.getString("description"));
            produit.setImage(rs.getString("image"));
            produit.setQstock(rs.getString("qtestock"));
          //  int id_cat = rs.getInt("categorie_id");
           // Categorie categorie = categorieService.afficherCategorie(id_cat);
          //  produit.setCategorie(categorie);
            
         
            produits.add(produit);
        }
        
return produits;
    }

   
}
