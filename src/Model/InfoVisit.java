/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author mega
 */
public class InfoVisit {

    private int id;
    private int cin;
    private String reason;
    private String enterTime;
    private String exitTime;
    private String datej;

    private String nom;
    private String prenom;

    public InfoVisit(int id, int cin, String reason, String enterTime, String exitTime, String datej, String nom, String prenom) {
        this.id = id;
        this.cin = cin;
        this.reason = reason;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.datej = datej;
        this.nom = nom;
        this.prenom = prenom;
    }

    public InfoVisit(int cin, String reason, String enterTime, String exitTime, String datej, String nom, String prenom) {
        this.cin = cin;
        this.reason = reason;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.datej = datej;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getDatej() {
        return datej;
    }

    public void setDatej(String datej) {
        this.datej = datej;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    
    
    
    

    

    public InfoVisit() {
    }

    @Override
    public String toString() {
        return "InfoVisit{" + "id=" + id + ", cin=" + cin + ", reason=" + reason + ", enterTime=" + enterTime + ", exitTime=" + exitTime + ", datej=" + datej + '}';
    }

}
