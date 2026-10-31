package com.example.myapp;

import java.util.ArrayList;

public class Note {

    public static ArrayList<Note> liste = new ArrayList<>();

    private String titre;
    private String description;
    private Tag tag;

    public Note(String titre, String description, Tag tag) {
        this.titre = titre;
        this.description = description;
        this.tag = tag;
    }

    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public Tag getTag() { return tag; }
}