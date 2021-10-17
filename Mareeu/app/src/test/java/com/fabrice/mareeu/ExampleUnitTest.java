package com.fabrice.mareeu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.fabrice.mareeu.di.Di;
import com.fabrice.mareeu.model.Reunion;
import com.fabrice.mareeu.service.ReunionApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private ReunionApiService mService;

    @Before
    public void setup() {
        mService = Di.getNewInstanceApiService();
    }

    @Test
    public void getReunionWithSuccess() {
        List<Reunion> reunions = mService.getReunion();
        assertEquals(reunions.size(), 1);
    }

    @Test
    public void createReunionWithSuccess() {
        Reunion reunion = mService.getReunion().get(0);
        mService.createReunion(reunion);
        assertTrue(mService.getReunion().contains(reunion));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunion = mService.getReunion().get(0);
        mService.deleteReunion(reunion);
        assertFalse(mService.getReunion().contains(reunion));
    }

    @Test
    public void filtrerDateWithSuccess() {
        Reunion reunion = mService.getReunion().get(0);
        assertTrue(mService.getFiltreDate(reunion.getDate()).contains(reunion));
    }

    @Test
    public void filtrerSalleWithSuccess() {
        Reunion reunion = mService.getReunion().get(0);
        assertTrue(mService.getFiltreSalle(reunion.getSalle()).contains(reunion));
    }
}