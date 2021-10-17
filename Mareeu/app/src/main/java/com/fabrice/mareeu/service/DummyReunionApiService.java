package com.fabrice.mareeu.service;

import com.fabrice.mareeu.model.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DummyReunionApiService implements ReunionApiService{

    private final List<Reunion> reunions = DummyReunionGenerator.generateReunion();

    public List<Reunion> getReunion(){
        return reunions;
    }

    public void createReunion(Reunion reunion){
        reunions.add(reunion);
    }

    public void deleteReunion(Reunion reunion){
        reunions.remove(reunion);
    }

    public List<Reunion> getFiltreSalle(String salle){
        List<Reunion> filtreSalle = new ArrayList<>();
        for (Reunion reunion : reunions) {
            if (reunion.getSalle().toLowerCase().contains(salle.toLowerCase())){
                filtreSalle.add(reunion);
            }
        }
        return filtreSalle;
    }

    public List<Reunion> getFiltreDate(String date){
        List<Reunion> filtreDate = new ArrayList<>();
        for (Reunion reunion : reunions) {
            if (reunion.getDate().contains(date)){
                filtreDate.add(reunion);
            }
        }
        return filtreDate;
    }
}
