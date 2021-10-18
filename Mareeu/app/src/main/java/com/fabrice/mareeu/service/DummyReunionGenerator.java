package com.fabrice.mareeu.service;

import com.fabrice.mareeu.model.Reunion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyReunionGenerator {
    public static List<Reunion> DUMMY_REUNION = Arrays.asList(new Reunion("Réunion1", "Salle1", "toto@gmail.com, coco@gmail.com", "30/9/2021", "14h20"));

    public DummyReunionGenerator() {
    }

    static List<Reunion> generateReunion() {
        return new ArrayList(DUMMY_REUNION);
    }
}
