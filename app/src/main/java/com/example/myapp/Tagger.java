package com.example.myapp;

import java.util.List;

public class Tagger {
    // Propriétés
    private int idMemo;
    private int idTag;

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

    // Méthodes CRUD
    public boolean create(Tagger o) {
        return false; 
    }

    public boolean delete(Tagger o) {
        return false;
    }

    public boolean update(Tagger o) {
        return false;
    }

    public Tagger findById(int id) {
        return null;
    }

    public List<Tagger> findAll() {
        return null;
    }
}