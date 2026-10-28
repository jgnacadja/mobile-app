package com.example.myapp;

import java.util.List;

public class Memo {
    private int idMemo;
    private String titre;
    private String contenu;
    private String dateCreation;
    private String dateModif;
    private int idUser;
    private int idDossier;

    private List<Memo> liste; 

    
    public Memo(List<Memo> liste) {
        this.liste = liste;
    }

    
    public Memo(int idMemo, String titre, String contenu, String dateCreation,
                String dateModif, int idUser, int idDossier) {
        this.idMemo = idMemo;
        this.titre = titre;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.dateModif = dateModif;
        this.idUser = idUser;
        this.idDossier = idDossier;
    }

    
    public int getIdMemo() { return idMemo; }
    public void setIdMemo(int idMemo) { this.idMemo = idMemo; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }

    public String getDateModif() { return dateModif; }
    public void setDateModif(String dateModif) { this.dateModif = dateModif; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public int getIdDossier() { return idDossier; }
    public void setIdDossier(int idDossier) { this.idDossier = idDossier; }

    
    public boolean create(Memo o) {
        return liste.add(o);
    }

    public boolean delete(Memo o) {
        return liste.remove(o);
    }

    public boolean update(Memo o) {
        for (Memo x : liste) {
            if (x.getIdMemo() == o.getIdMemo()) {
                x.setTitre(o.getTitre());
                x.setContenu(o.getContenu());
                x.setDateModif(o.getDateModif());
                x.setIdUser(o.getIdUser());
                x.setIdDossier(o.getIdDossier());
                return true;
            }
        }
        return false;
    }

    public Memo findById(int id) {
        for (Memo x : liste) {
            if (x.getIdMemo() == id) {
                return x;
            }
        }
        return null;
    }

    public List<Memo> findAll() {
        return liste;
    }

 
    public String toString() {
        return "Memo{id=" + idMemo
            + ", titre=" + titre
            + ", contenu=" + contenu
            + ", dateCreation=" + dateCreation
            + ", dateModif=" + dateModif
            + ", idUser=" + idUser
            + ", idDossier=" + idDossier + "}";
    }
}