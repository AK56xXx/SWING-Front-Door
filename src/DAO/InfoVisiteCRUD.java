/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DB.Singleton1;
import java.sql.*;
/**
 *
 * @author mega
 */
public class InfoVisiteCRUD implements InfoVisitInterface {
    Connection con = Singleton1.getInstance();

    @Override
    public void deletev(int cin) {
       String sql = "DELETE FROM info_visit WHERE cin_v = ? ;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setInt(1, cin);       
           ps.executeUpdate();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    public void deletev() {
         String sql = "DELETE FROM info_visit ";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);     
           ps.executeUpdate();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    
}
