/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author mega
 */
public class Visiteur {
    private int cin;
    private String nom;
    private String prenom;
    private int tel;
    private String lieu;

    public Visiteur(int cin, String nom, String prenom , int tel , String lieu) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.lieu = lieu;
    }

    public Visiteur(String nom, String prenom , int tel , String lieu) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.lieu = lieu;
    }

    public Visiteur() {
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Visiteur{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", lieu=" + lieu + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.cin;
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.prenom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Visiteur other = (Visiteur) obj;
        if (this.cin != other.cin) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
}
