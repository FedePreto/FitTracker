package com.example.fittracker.home

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import com.example.fittracker.R
import com.example.fittracker.home.HomeActivity
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
internal class HomeActivityTest{
    private lateinit var scenario: ActivityScenario<HomeActivity>

    @Before
    fun setUp(){
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun waterTest(){
        onView(withId(R.id.glass_8)).perform(click())
        onView(withText("2.0L")).check(matches(isDisplayed()))
    }


}