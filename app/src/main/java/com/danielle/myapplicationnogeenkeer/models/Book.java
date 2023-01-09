package com.danielle.myapplicationnogeenkeer.models;

public class Book {

    private int id;
    private String titel;
    private String isbn;
    private String schrijver;
    private String samenvatting;
    private String keywords;
    private String taal;
    private int jaar;
    private String foto;

    public Book(int id, String titel, String isbn, String schrijver, String samenvatting, String keywords, String taal, int jaar, String foto) {
        this.id = id;
        this.titel = titel;
        this.isbn = isbn;
        this.schrijver = schrijver;
        this.samenvatting = samenvatting;
        this.keywords = keywords;
        this.taal = taal;
        this.jaar = jaar;
        this.foto = foto;
    }

    public Book(int id, String titel, String foto, String samenvatting) {
        this.id = id;
        this.titel = titel;
        this.foto = foto;
        this.samenvatting = samenvatting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSchrijver() {
        return schrijver;
    }

    public void setSchrijver(String schrijver) {
        this.schrijver = schrijver;
    }

    public String getSamenvatting() {
        return samenvatting;
    }

    public void setSamenvatting(String samenvatting) {
        this.samenvatting = samenvatting;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
