/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Visiteur;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public interface VisiteurInterface {
        
    public void addv(Visiteur v);
    public void updatev(Visiteur v);
    public ArrayList<Visiteur> showv();
    public void deletev(int id);
    public ArrayList searchv(int id);
    public ArrayList searchv_n(String ch); //test
    public void deletev();
    
    
}
