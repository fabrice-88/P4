package com.fabrice.mareeu;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;

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
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t2deleteReunion() {
        Espresso.onView(ViewMatchers.withId(R.id.delete)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }

    @Test
    public void t3createReunion() {
        Espresso.onView(ViewMatchers.withId(R.id.add)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.salle1)).perform(replaceText("salle1"));
        Espresso.onView(ViewMatchers.withId(R.id.sujet1)).perform(replaceText("réunion1"));
        Espresso.onView(ViewMatchers.withId(R.id.mail1)).perform(replaceText("toto@gmail.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.date)).perform(click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.heure)).perform(click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.submitButton)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t4filtrerDate() {
        Espresso.onView(ViewMatchers.withId(R.id.filtre)).perform(click());
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.title), ViewMatchers.withText("Filtrer par date"), ViewMatchers.isDisplayed())).perform(click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(1)));
    }

    @Test
    public void t5filtrerSalle() {
        Espresso.onView(ViewMatchers.withId(R.id.filtre)).perform(click());
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.title), ViewMatchers.withText("Filtrer par salle"), ViewMatchers.isDisplayed())).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(typeText("toto"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }
}