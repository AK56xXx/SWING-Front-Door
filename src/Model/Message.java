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
public class Message {
    
    private int idm;
    private String nom_u;
    private String nom_a;
    private String titre;
    private String msg;
    private String date_m;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Message(int idm, String nom_u, String nom_a, String titre, String msg, String date_m , String token) {
        this.idm = idm;
        this.nom_u = nom_u;
        this.nom_a = nom_a;
        this.titre = titre;
        this.msg = msg;
        this.date_m = date_m;
        this.token = token;
    }

    public Message(String nom_u, String nom_a, String titre, String msg, String date_m , String token) {
        this.nom_u = nom_u;
        this.nom_a = nom_a;
        this.titre = titre;
        this.msg = msg;
        this.date_m = date_m;
        this.token = token;
    }

    public Message() {
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getNom_u() {
        return nom_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public String getNom_a() {
        return nom_a;
    }

    public void setNom_a(String nom_a) {
        this.nom_a = nom_a;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate_m() {
        return date_m;
    }

    public void setDate_m(String date_m) {
        this.date_m = date_m;
    }

    @Override
    public String toString() {
        return "Message{" + "idm=" + idm + ", nom_u=" + nom_u + ", nom_a=" + nom_a + ", titre=" + titre + ", msg=" + msg + ", date_m=" + date_m + '}';
    }
    
    
    
    
}
