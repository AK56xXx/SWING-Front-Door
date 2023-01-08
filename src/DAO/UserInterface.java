/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public interface UserInterface {
    
    public User isLoginValidUser(String nom, String password);
    public void addU(User u);
    public void updateU(String nom , String pwd);
    public ArrayList<User> showUsers();
    public void deleteU(String nom);
    public ArrayList searchU(String nom);
    
}
