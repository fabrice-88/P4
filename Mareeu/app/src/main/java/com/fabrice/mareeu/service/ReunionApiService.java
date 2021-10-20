package com.fabrice.mareeu.service;

import com.fabrice.mareeu.model.Reunion;
import java.util.List;

public interface ReunionApiService {
    List<Reunion> getReunion();

    void createReunion(Reunion reunion);

    void deleteReunion(Reunion reunion);

    List<Reunion> getFiltreSalle(String salle);

    List<Reunion> getFiltreDate(String date);
}
