package com.fabrice.mareeu.reunion_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.fabrice.mareeu.R;
import com.fabrice.mareeu.ui.MainActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        ActivityScenario<MainActivity> mActivity = rule.getScenario();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void  t1reunion_shouldBeEmpty() {
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasChildCount(1)));
    }

    @Test
    public void t2deleteReunion() {
        onView(withId(R.id.item_list_delete_button)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasChildCount(0)));
    }

    @Test
    public void t3createReunion() {
        onView(ViewMatchers.withId(R.id.startAddActivity)).perform(click());
        onView(withId(R.id.salle1)).perform(replaceText("salle1"));
        onView(withId(R.id.sujet1)).perform(replaceText("réunion1"));
        onView(withId(R.id.mail1)).perform(replaceText("toto@gmail.com"),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.date)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.heure)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasChildCount(1)));
    }

    @Test
    public void t4filtrerDate() {
        onView(withId(R.id.menu_filtre)).perform(click());
        onView(allOf(withId(R.id.title), withText("Filtrer par date"),isDisplayed())).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasChildCount(1)));
    }

    @Test
    public void t5filtrerSalle() {
        onView(withId(R.id.menu_filtre)).perform(click());
        onView(allOf(withId(R.id.title), withText("Filtrer par salle"),isDisplayed())).perform(click());
        onView(withId(R.id.search_src_text)).perform(click());
        onView(withId(R.id.search_src_text)).perform(replaceText("toto"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasChildCount(0)));
    }
}