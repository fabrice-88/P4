package com.fabrice.mareeu;

import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExampleUnitTest {
    private ReunionApiService mService;

    public ExampleUnitTest() {
    }

    @Before
    public void setup() {
        this.mService = Di.getNewInstanceApiService();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = this.mService.getReunion();
        Assert.assertEquals((long)reunions.size(), 1L);
    }

    @Test
    public void createReunionWithSuccess() {
        Reunion reunion = (Reunion)this.mService.getReunion().get(0);
        this.mService.createReunion(reunion);
        Assert.assertTrue(this.mService.getReunion().contains(reunion));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunion = (Reunion)this.mService.getReunion().get(0);
        this.mService.deleteReunion(reunion);
        Assert.assertFalse(this.mService.getReunion().contains(reunion));
    }

    @Test
    public void filtrerDateWithSuccess() {
        Reunion reunion = (Reunion)this.mService.getReunion().get(0);
        Assert.assertTrue(this.mService.getFiltreDate(reunion.getDate()).contains(reunion));
    }

    @Test
    public void filtrerSalleWithSuccess() {
        Reunion reunion = (Reunion)this.mService.getReunion().get(0);
        Assert.assertTrue(this.mService.getFiltreSalle(reunion.getSalle()).contains(reunion));
    }
}
