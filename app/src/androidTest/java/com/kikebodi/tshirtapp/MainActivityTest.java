package com.kikebodi.tshirtapp;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Kike Bodi on 13/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class MainActivityTest {

    @Rule public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void isAbleToShowMainScreen(){
        onView(withText("hello")).check(ViewAssertions.matches(isDisplayed()));
    }
}
