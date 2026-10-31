package com.example.myapp;

import java.util.ArrayList;

public class Tag {

    public static ArrayList<Tag> liste = new ArrayList<>();

    private String label;
    private String couleur;

    public Tag(String label, String couleur) {
        this.label = label;
        this.couleur = couleur;
    }

    public String getLabel() { return label; }
    public String getCouleur() { return couleur; }
}