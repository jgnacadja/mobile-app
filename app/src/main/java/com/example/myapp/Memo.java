package com.example.myapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memo {
    private static final Map<Integer, Memo> liste = new HashMap<>();

    private int idMemo;
    private String titre;
    private String contenu;
    private final Date dateCreation;
    private Date dateModif;
    private int idUser;
    private int idDossier;

    public Memo(int idMemo, String titre, String contenu, int idUser, int idDossier) {
        this.idMemo = idMemo;
        this.titre = titre;
        this.contenu = contenu;
        this.idUser = idUser;
        this.idDossier = idDossier;
        this.dateCreation = new Date();
        this.dateModif = new Date();
    }

    private void touch() { this.dateModif = new Date(); }

  
    public int getIdMemo() { return idMemo; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; touch(); }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; touch(); }

    public Date getDateCreation() { return new Date(dateCreation.getTime()); }
    public Date getDateModif() { return new Date(dateModif.getTime()); }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; touch(); }

    public int getIdDossier() { return idDossier; }
    public void setIdDossier(int idDossier) { this.idDossier = idDossier; touch(); }

    public static boolean create(Memo o) {
        if (o == null || liste.containsKey(o.idMemo)) return false;
        liste.put(o.idMemo, o);
        return true;
    }

    public static boolean delete(int id) {
        return liste.remove(id) != null;
    }

    public static boolean update(Memo o) {
        Memo x = liste.get(o.idMemo);
        if (x == null) return false;
        x.setTitre(o.titre);
        x.setContenu(o.contenu);
        x.setIdUser(o.idUser);
        x.setIdDossier(o.idDossier);
        return true;
    }

    public static Memo findById(int id) {
        return liste.get(id);
    }

    public static List<Memo> findAll() {
        return new ArrayList<>(liste.values());
    }

    @Override
    public String toString() {
        return "Memo{id=" + idMemo + ", titre=" + titre + ", contenu=" + contenu
            + ", dateCreation=" + dateCreation + ", dateModif=" + dateModif
            + ", idUser=" + idUser + ", idDossier=" + idDossier + "}";
    }
}