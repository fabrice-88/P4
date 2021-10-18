package com.fabrice.mareeu.service;

import com.fabrice.mareeu.model.Reunion;
import java.util.List;

public interface ReunionApiService {
    List<Reunion> getReunion();

    void createReunion(Reunion var1);

    void deleteReunion(Reunion var1);

    List<Reunion> getFiltreSalle(String var1);

    List<Reunion> getFiltreDate(String var1);
}
