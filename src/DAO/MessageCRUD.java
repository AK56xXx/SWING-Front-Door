/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.Singleton1;
import Model.Message;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public class MessageCRUD implements MessageInterface {
        Connection con = Singleton1.getInstance();
    @Override
    public void addMsg(Message m) {
        
        String sql = "INSERT INTO message (nom_user, nom_admin ,titre, msg , date_m , token)  values(?,?,?,?,?,?);";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1,m.getNom_u());
           ps.setString(2,m.getNom_a());
           ps.setString(3,m.getTitre());
           ps.setString(4,m.getMsg());
           ps.setString(5,m.getDate_m());
           ps.setString(6,m.getToken());
           ps.executeUpdate();
          
 
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("Message added");
        
        
        
        
        
        
        
        
        
    }

    @Override
    public ArrayList<Message> showMsg() {
        String sql = "select * from message";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Message>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int id =rs.getInt("idm");
               String user = rs.getString("nom_user");
               String admin = rs.getString("nom_admin");
               String titre = rs.getString("titre");
               String msg = rs.getString("msg");
               String date_m = rs.getString("date_m");
               String token = rs.getString("token");
              
               list.add(new Message(id,user,admin,titre,msg,date_m,token));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    }

    @Override
    public ArrayList<Message> showMsgUser(String ch) {
        String sql = "select * from message where nom_user like '%"+ch+"%' and token like 'user_send' ";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Message>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int id =rs.getInt("idm");
               String user = rs.getString("nom_user");
               String admin = rs.getString("nom_admin");
               String titre = rs.getString("titre");
               String msg = rs.getString("msg");
               String date_m = rs.getString("date_m");
               String token = rs.getString("token");
              
               list.add(new Message(id,user,admin,titre,msg,date_m,token));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    }

    @Override
    public ArrayList<Message> showMsgAdmin(String ch) {
        String sql = "select * from message where nom_admin like '%"+ch+"%' and token like 'admin_send'";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Message>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int id =rs.getInt("idm");
               String user = rs.getString("nom_user");
               String admin = rs.getString("nom_admin");
               String titre = rs.getString("titre");
               String msg = rs.getString("msg");
               String date_m = rs.getString("date_m");
               String token = rs.getString("token");
              
               list.add(new Message(id,user,admin,titre,msg,date_m,token));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    }

    @Override
    public void deleteM(int id) {
         String sql = "DELETE FROM message WHERE idm= ? ;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setInt(1, id);       
           ps.executeUpdate();
               con.close();
                  
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    @Override
    public void deleteM() {
        String sql = "DELETE FROM message;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);             
           ps.executeUpdate();
                        
       }catch (SQLException e){
           e.printStackTrace();
       }
        
    }
    
}
