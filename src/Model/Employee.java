/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mega
 */
public class Employee {
    private int id;
    private String nom;
    private String prenom;
    private String dep;
    private String ocu;
    private int tel;
    private String mail;

    public Employee(int id, String nom, String prenom, String dep, String ocu, int tel, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dep = dep;
        this.ocu = ocu;
        this.tel = tel;
        this.mail = mail;
    }

    public Employee(String nom, String prenom, String dep, String ocu, int tel, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.dep = dep;
        this.ocu = ocu;
        this.tel = tel;
        this.mail = mail;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getOcu() {
        return ocu;
    }

    public void setOcu(String ocu) {
        this.ocu = ocu;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dep=" + dep + ", ocu=" + ocu + ", tel=" + tel + ", mail=" + mail + '}';
    }
    
    
    
    
    
    
    
    
}
