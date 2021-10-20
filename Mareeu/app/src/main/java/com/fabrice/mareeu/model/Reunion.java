package com.fabrice.mareeu.model;

public class Reunion {
    private String mSujet;
    private String mSalle;
    private String mMail;
    private String mHeure;
    private String mDate;

    public Reunion(String sujet, String salle, String mail, String date, String heure) {
        this.mMail = mail;
        this.mSalle = salle;
        this.mSujet = sujet;
        this.mHeure = heure;
        this.mDate = date;
    }

    public String getSalle() {
        return this.mSalle;
    }

    public void setSalle(String salle) {
        this.mSalle = salle;
    }

    public String getSujet() {
        return this.mSujet;
    }

    public void setSujet(String sujet) {
        this.mSujet = sujet;
    }

    public String getMail() {
        return this.mMail;
    }

    public void setMail(String mail) {
        this.mMail = mail;
    }

    public String getHeure() {
        return this.mHeure;
    }

    public void setHeure(String heure) {
        this.mHeure = heure;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }
}
