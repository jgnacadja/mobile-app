package com.example.myapp;

import java.util.ArrayList;
import java.util.List;

public class Tagger {
    // Propriétés
    private int idMemo;
    private int idTag;

    // Simulation d'une base de données en mémoire (comme l'a fait l'équipe)
    private static List<Tagger> taggersDB = new ArrayList<>();

    // Constructeurs
    public Tagger(int idMemo, int idTag) {
        this.idMemo = idMemo;
        this.idTag = idTag;
    }

    public Tagger() {}

    // Getters et Setters
    public int getIdMemo() { 
        return idMemo; 
    }
    
    public void setIdMemo(int idMemo) { 
        this.idMemo = idMemo; 
    }

    public int getIdTag() { 
        return idTag; 
    }
    
    public void setIdTag(int idTag) { 
        this.idTag = idTag; 
    }

    // Méthodes CRUD avec véritable algorithmique en mémoire
    public boolean create(Tagger o) {
        if (o != null) {
            taggersDB.add(o); // Ajoute le tagger à la fausse base de données
            return true; 
        }
        return false;
    }

    public boolean delete(Tagger o) {
        // Cherche l'objet dans la liste et le supprime
        for (int i = 0; i < taggersDB.size(); i++) {
            if (taggersDB.get(i).getIdMemo() == o.getIdMemo() && taggersDB.get(i).getIdTag() == o.getIdTag()) {
                taggersDB.remove(i);
                return true; // Suppression réussie
            }
        }
        return false;
    }

    public boolean update(Tagger o) {
        // Cherche l'objet et le met à jour
        for (int i = 0; i < taggersDB.size(); i++) {
            if (taggersDB.get(i).getIdMemo() == o.getIdMemo() && taggersDB.get(i).getIdTag() == o.getIdTag()) {
                taggersDB.set(i, o);
                return true; // Mise à jour réussie
            }
        }
        return false;
    }

    public Tagger findById(int id) {
        for (Tagger t : taggersDB) {
            if (t.getIdMemo() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Tagger> findAll() {
        return new ArrayList<>(taggersDB); // Retourne la liste complète
    }
}