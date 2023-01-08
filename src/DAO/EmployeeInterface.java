/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Employee;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public interface EmployeeInterface {

    public void addE(Employee e);
    public void updateE(Employee e);
    public ArrayList<Employee> showE();
    public void deleteE(int id);
    public ArrayList searchE(String nom);
    
}
