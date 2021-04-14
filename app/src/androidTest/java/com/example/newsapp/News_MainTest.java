package com.example.newsapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class News_MainTest extends TestCase {
    private static final int ITEMFIND=5;
    @Rule
    public ActivityTestRule<News_Main> activityTestRule = new ActivityTestRule<>(News_Main.class);

    @Test
    public void test_isRecyclerViewVisible(){
        onView(withId(R.id.news_RecyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isHeadlineVisible(){
        onView(withId(R.id.headline)).check(matches(isDisplayed()));
    }
    @Test
    public void test_recycler(){
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.news_RecyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.news_RecyclerView)).perform(RecyclerViewActions.scrollToPosition(itemCount-1));
    }

}