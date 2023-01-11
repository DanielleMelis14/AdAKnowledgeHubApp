package com.danielle.myapplicationnogeenkeer.models;

public class Resource {
    private int bron_id;
    private String titel;
    private String schrijver;
    private String samenvatting;
    private String keywords;
    private String taal;
    private int jaar;
    private int druk;
    private String link;

    public Resource(int bron_id, String titel, String schrijver, String samenvatting, String keywords, String taal, int jaar, int druk, String link) {
        this.bron_id = bron_id;
        this.titel = titel;
        this.schrijver = schrijver;
        this.samenvatting = samenvatting;
        this.keywords = keywords;
        this.taal = taal;
        this.jaar = jaar;
        this.druk = druk;
        this.link = link;
    }

    public Resource(int bron_id, String titel, String samenvatting, String link) {
        this.bron_id = bron_id;
        this.titel = titel;
        this.samenvatting = samenvatting;
        this.link = link;
    }

    public int getBron_id() {
        return bron_id;
    }

    public void setBron_id(int bron_id) {
        this.bron_id = bron_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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

    public int getDruk() {
        return druk;
    }

    public void setDruk(int druk) {
        this.druk = druk;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
