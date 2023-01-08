/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.Singleton1;
import Model.Visiteur;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public class VisiteurCRUD implements VisiteurInterface {
    Connection con = Singleton1.getInstance();

    @Override
    public void addv(Visiteur v) {
       String sql = "INSERT INTO visiteur (cin, nom ,prenom, tel , lieu)  values(?,?,?,?,?);";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setInt(1,v.getCin());
           ps.setString(2, v.getNom());
           ps.setString(3, v.getPrenom());
           ps.setInt(4, v.getTel());
           ps.setString(5, v.getLieu());
           ps.executeUpdate();
          
               con.close();
 
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("visiteur added");
             
    }

    @Override
    public void updatev(Visiteur v) {
        String sql = "UPDATE visiteur SET nom= ? ,prenom= ?,tel= ?,lieu= ? WHERE cin = ?;";
       PreparedStatement ps;
       try{
         ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1, v.getNom());
           ps.setString(2, v.getPrenom());
           ps.setInt(3, v.getTel());
           ps.setString(4, v.getLieu());
           ps.setInt(5, v.getCin());
           ps.executeUpdate();
           
           
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("visteur updated");      
        
    }

    @Override
    public ArrayList<Visiteur> showv() {
       String sql = "select * from visiteur";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Visiteur>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int cin =rs.getInt("cin");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               int tel= rs.getInt("tel");
               String lieu = rs.getString("lieu");
              
               list.add(new Visiteur(cin,nom,prenom,tel,lieu));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    
    }

    @Override
    public void deletev(int id) {
        String sql = "DELETE FROM visiteur WHERE cin= ? ;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setInt(1, id);       
           ps.executeUpdate();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
        
    }

    @Override
    public ArrayList searchv(int id) {
        String sql = "select * from visiteur where cin="+id;
       PreparedStatement ps;
       ArrayList<Visiteur> l =new ArrayList();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int cin =rs.getInt("cin");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               int tel= rs.getInt("tel");
               String lieu = rs.getString("lieu");
              
               l.add(new Visiteur(cin,nom,prenom,tel,lieu));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return l;
        
    }
    
    
    
    @Override //test
    public ArrayList searchv_n(String ch) {
       String sql = "select * from visiteur where nom like '%"+ch+"%'"+"or prenom like '%"+ch+"%'";
       PreparedStatement ps;
       ArrayList<Visiteur> l =new ArrayList();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int cin =rs.getInt("cin");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               int tel= rs.getInt("tel");
               String lieu = rs.getString("lieu");
              
               l.add(new Visiteur(cin,nom,prenom,tel,lieu));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return l;
        
    }
    
    
    

    @Override
    public void deletev() {
          String sql = "DELETE FROM visiteur";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);      
           ps.executeUpdate();

                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    
    
    
    
    
    
    
    
    
    
}
