/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.Singleton1;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public class EmployeeCRUD implements EmployeeInterface {
    Connection con = Singleton1.getInstance();

    @Override
    public void addE(Employee e) {
        String sql = "INSERT INTO employees (nom,prenom,dep,ocup,tel,mail)  values(?,?,?,?,?,?);";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1,e.getNom());
           ps.setString(2,e.getPrenom());
           ps.setString(3,e.getDep());
           ps.setString(4,e.getOcu());
           ps.setInt(5,e.getTel());
           ps.setString(6,e.getMail());
           ps.executeUpdate();
          
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("employee added");
    }

    @Override
    public void updateE(Employee e) {
        String sql = "UPDATE employees SET nom= ? ,prenom= ?,dep= ?,ocup= ?,tel= ? ,mail= ? WHERE idemp = ?;";
       PreparedStatement ps;
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1,e.getNom());
           ps.setString(2,e.getPrenom());
           ps.setString(3,e.getDep());
           ps.setString(4,e.getOcu());
           ps.setInt(5,e.getTel());
           ps.setString(6,e.getMail());
           ps.setInt(7,e.getId());
           ps.executeUpdate();
          
       }catch (SQLException x){
           x.printStackTrace();
       }
       System.out.println("employee updated");
    }

    @Override
    public ArrayList<Employee> showE() {
        String sql = "select * from employees";
       PreparedStatement ps;
       ArrayList list =new ArrayList<Employee>();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int id =rs.getInt("idemp");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               String dep = rs.getString("dep");
               String ocu = rs.getString("ocup");
               int tel= rs.getInt("tel");
               String mail = rs.getString("mail");
              
               list.add(new Employee(id,nom,prenom,dep,ocu,tel,mail));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return list;
    }

    @Override
    public void deleteE(int id) {
         String sql = "DELETE FROM employees WHERE idemp= ? ;";
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
    public ArrayList searchE(String ch) {
       String sql = "select * from employees where nom like '%"+ch+"%'"+"or prenom like '%"+ch+"%'";
       PreparedStatement ps;
       ArrayList<Employee> l =new ArrayList();
       try{
           ps = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {
               int id =rs.getInt("idemp");
               String nom = rs.getString("nom");
               String prenom = rs.getString("prenom");
               String dep = rs.getString("dep");
               String ocu = rs.getString("ocup");
               int tel= rs.getInt("tel");
               String mail = rs.getString("mail");
              
               l.add(new Employee(id,nom,prenom,dep,ocu,tel,mail));
                         
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return l;
    }
    
   
    
}
