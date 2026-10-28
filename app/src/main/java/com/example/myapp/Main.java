package com.example.myapp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Memo> liste = new ArrayList<>();
        Memo gestion = new Memo(liste);

        gestion.create(new Memo(1, "Courses", "Acheter du pain", "2026-10-28", "2026-10-28", 1, 1));
        gestion.create(new Memo(2, "TP Java", "Finir le TP", "2026-10-28", "2026-10-28", 1, 2));

        System.out.println("Après create :");
        System.out.println(gestion.findAll());

        gestion.update(new Memo(1, "Courses", "Acheter du lait", "2026-10-28", "2026-10-29", 1, 1));
        System.out.println("Après update du mémo 1 :");
        System.out.println(gestion.findById(1));

        gestion.delete(gestion.findById(2));
        System.out.println("Après delete du mémo 2 :");
        System.out.println(liste);
    }
}