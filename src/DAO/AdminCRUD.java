/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.Singleton1;
import Model.Admin;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author mega
 */
public class AdminCRUD implements AdminInterface {
      Connection con = Singleton1.getInstance();
    @Override
    public Admin isLoginValidAdmin(String nom, String password) {
         Admin u = new Admin();
        String sql = "select * from admin where nom='" +nom+ "' and pwd='"+password+"'";
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
    public ArrayList<Admin> showAdmins() {
        String sql = "select * from admin";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Admin>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
       
               String nom = rs.getString("nom");
               String pwd = rs.getString("pwd");
           
              
               list.add(new Admin(nom,pwd));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
        
        
        
        
    }


    
}
