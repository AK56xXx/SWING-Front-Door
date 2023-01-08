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
public class User {

    private String nom;
    private String pwd;

    public User(String nom, String pwd) {
        this.nom = nom;
        this.pwd = pwd;
    }

    public User(String pwd) {
        this.pwd = pwd;
    }

    public User() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.pwd);
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
        final User other = (User) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", pwd=" + pwd + '}';
    }

}
