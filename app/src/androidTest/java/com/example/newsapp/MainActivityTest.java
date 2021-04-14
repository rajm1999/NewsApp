package com.example.newsapp;
import androidx.test.core.app.ActivityScenario;
import junit.framework.TestCase;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;


public class MainActivityTest extends TestCase {
    @Test
    public void test_isActivityInView(){
    ActivityScenario actvityscenario = ActivityScenario.launch(MainActivity.class);
    onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void test_Visibility_title_homebtn() {

        ActivityScenario actvityscenario = ActivityScenario.launch(MainActivity.class);

        //To check visibility of home btn
        onView(withId(R.id.main_button)).check(matches(isDisplayed()));
    }

    @Test
    public void test_nav_news_main_activity() {
        ActivityScenario actvityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.main_button)).perform(click());
        onView(withId(R.id.news_main)).check(matches(isDisplayed()));
    }

    @Test
    public void test_backPress_toMainActivity() {
        ActivityScenario actvityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.main_button)).perform(click());
        onView(withId(R.id.news_main)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}