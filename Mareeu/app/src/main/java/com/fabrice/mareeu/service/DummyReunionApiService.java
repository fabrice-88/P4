package com.fabrice.mareeu.service;

import com.fabrice.mareeu.model.Reunion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DummyReunionApiService implements ReunionApiService {
    private final List<Reunion> reunions = DummyReunionGenerator.generateReunion();

    public List<Reunion> getReunion() {
        return this.reunions;
    }

    public void createReunion(Reunion reunion) {
        this.reunions.add(reunion);
    }

    public void deleteReunion(Reunion reunion) {
        this.reunions.remove(reunion);
    }

    public List<Reunion> getFiltreSalle(String salle) {
        List<Reunion> filtreSalle = new ArrayList();
        Iterator var3 = this.reunions.iterator();

        while(var3.hasNext()) {
            Reunion reunion = (Reunion)var3.next();
            if (reunion.getSalle().toLowerCase().contains(salle.toLowerCase())) {
                filtreSalle.add(reunion);
            }
        }
        return filtreSalle;
    }

    public List<Reunion> getFiltreDate(String date) {
        List<Reunion> filtreDate = new ArrayList();
        Iterator var3 = this.reunions.iterator();

        while(var3.hasNext()) {
            Reunion reunion = (Reunion)var3.next();
            if (reunion.getDate().contains(date)) {
                filtreDate.add(reunion);
            }
        }
        return filtreDate;
    }
}
