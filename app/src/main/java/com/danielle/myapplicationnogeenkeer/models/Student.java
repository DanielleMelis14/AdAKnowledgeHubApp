package com.danielle.myapplicationnogeenkeer.models;

public class Student {
    private int id;
    private String naam, email;

    public Student(int id, String naam, String email) {
        this.id = id;
        this.naam = naam;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }
}
