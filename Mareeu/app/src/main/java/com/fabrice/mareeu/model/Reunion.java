package com.fabrice.mareeu.model;


import java.util.Date;

public class Reunion{

    private String mSujet;

    private String mSalle;

    private String mMail;

    private String mHeure;

    private String mDate;

    public Reunion(String sujet, String salle, String mail, String date, String heure){
        this.mMail = mail;
        this.mSalle = salle;
        this.mSujet = sujet;
        this.mHeure = heure;
        this.mDate = date;
    }

    public String getSalle(){
        return mSalle;
    }

    public void setSalle(String salle){
        mSalle = salle;
    }

    public String getSujet(){
        return mSujet;
    }

    public void setSujet(String sujet){
        mSujet = sujet;
    }

    public String getMail(){
        return mMail;
    }

    public void setMail(String mail){
        mMail = mail;
    }

    public String getHeure(){
        return mHeure;
    }

    public void setHeure(String heure){
        mHeure = heure;
    }

    public String getDate(){
        return mDate;
    }

    public void setDate(String date){
        mDate = date;
    }
}
