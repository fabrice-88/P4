package com.fabrice.mareeu;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.fabrice.mareeu.ui.MainActivity;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule(MainActivity.class);

    public ExampleInstrumentedTest() {
    }

    @Before
    public void setUp() {
        ActivityScenario<MainActivity> mActivity = this.rule.getScenario();
        Assert.assertThat(mActivity, IsNull.notNullValue());
    }

    @Test
    public void t1reunion_shouldBeEmpty() {
        Espresso.onView(ViewMatchers.withId(2131231058)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t2deleteReunion() {
        Espresso.onView(ViewMatchers.withId(2131230943)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231058)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }

    @Test
    public void t3createReunion() {
        Espresso.onView(ViewMatchers.withId(2131231119)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231067)).perform(new ViewAction[]{ViewActions.replaceText("salle1")});
        Espresso.onView(ViewMatchers.withId(2131231131)).perform(new ViewAction[]{ViewActions.replaceText("réunion1")});
        Espresso.onView(ViewMatchers.withId(2131230960)).perform(new ViewAction[]{ViewActions.replaceText("toto@gmail.com"), ViewActions.closeSoftKeyboard()});
        Espresso.onView(ViewMatchers.withId(2131230857)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withText("OK")).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131230925)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withText("OK")).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231128)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231058)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t4filtrerDate() {
        Espresso.onView(ViewMatchers.withId(2131230983)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(2131231168), ViewMatchers.withText("Filtrer par date"), ViewMatchers.isDisplayed())).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withText("OK")).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231058)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t5filtrerSalle() {
        Espresso.onView(ViewMatchers.withId(2131230983)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(2131231168), ViewMatchers.withText("Filtrer par salle"), ViewMatchers.isDisplayed())).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231086)).perform(new ViewAction[]{ViewActions.click()});
        Espresso.onView(ViewMatchers.withId(2131231086)).perform(new ViewAction[]{ViewActions.replaceText("toto"), ViewActions.closeSoftKeyboard()});
        Espresso.onView(ViewMatchers.withId(2131231058)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }
}
