package com.fabrice.mareeu.di;

import com.fabrice.mareeu.service.DummyReunionApiService;
import com.fabrice.mareeu.service.ReunionApiService;

public class Di {
    private static ReunionApiService service = new DummyReunionApiService();

    public static ReunionApiService getReunionApiService() {
        return service;
    }

    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}