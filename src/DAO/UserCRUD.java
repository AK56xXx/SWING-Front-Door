/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.Singleton1;
import Model.User;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public class UserCRUD implements UserInterface {
    Connection con = Singleton1.getInstance();

    @Override
    public User isLoginValidUser(String nom, String password) {
        User u = new User();
        String sql = "select * from user where nom='" +nom+ "' and pwd='"+password+"'";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           if(rs.next())
           {
               
               u.setNom(rs.getString("nom"));
               u.setPwd(rs.getString("pwd"));
                     
               return u;
                        
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return u;
    }

    @Override
    public void addU(User u) {
         String sql = "INSERT INTO user (nom, pwd)  values(?,?);";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1,u.getNom());
           ps.setString(2,u.getPwd());
           ps.executeUpdate();
           
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("User added");
    }

    @Override
    public void updateU(String nom,String pwd) {
         String sql = "update user set pwd= ? where nom = ?";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1, pwd);
           ps.setString(2, nom);
           ps.executeUpdate();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    public ArrayList<User> showUsers() {
        String sql = "select * from user";
       PreparedStatement ps;
       ArrayList list =new ArrayList<User>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
       
               String nom = rs.getString("nom");
               String pwd = rs.getString("pwd");
           
              
               list.add(new User(nom,pwd));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    }

    @Override
    public void deleteU(String nom) {
        String sql = "DELETE FROM user WHERE nom= ? ;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1, nom);       
           ps.executeUpdate();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    public ArrayList searchU(String nom) {
        String sql = "select * from user where nom like '%"+nom+"%'";
       PreparedStatement ps;
       ArrayList<User> l =new ArrayList();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               String nomu =rs.getString("nom");
               String pwd= rs.getString("pwd");
           
              
               l.add(new User(nomu,pwd));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return l;
    }
    
}
