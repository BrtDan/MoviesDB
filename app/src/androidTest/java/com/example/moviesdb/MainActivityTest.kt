package com.example.moviesdb

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.doubleClick
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {
    @get:Rule var activityScenario = activityScenarioRule<MainActivity>()

    @Test
    fun shouldShowSearchTvWhenClickedSearchButton() {

        onView(withId(R.id.imageMovies1))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.moviesName1))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageMovies2))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.moviesName2))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageMovies3))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.moviesName3))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageTv1))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.imageTv1))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageTv2))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.imageTv2))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageTv3))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
        onView(withId(R.id.imageTv3))
            .check(matches(isDisplayed()))

        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(typeText("1917"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.searchItem))
            .check(matches(isDisplayed()))
            .perform(swipeUp())
            .perform(swipeDown())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(clearText())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.body))
            .check(matches(isDisplayed()))

        onView(withId(R.id.horizontalScrollView))
            .check(matches(isDisplayed()))
            .perform(swipeLeft())
            .perform(swipeRight())

        onView(withId(R.id.body))
            .check(matches(isDisplayed()))

        onView(withId(R.id.horizontalScrollView_2))
            .check(matches(isDisplayed()))

        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText("catch me if you can"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.searchItem))
            .check(matches(isDisplayed()))
            .perform(swipeUp())
            .perform(swipeDown())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(clearText())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())
            .perform(typeText("The wolf of wall street"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.horizontalScrollView_2))
            .check(matches(isDisplayed()))
/*
        onView(withId(R.id.imageTv1))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())*/


        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(closeSoftKeyboard())
            .perform(typeText("Interstellar"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        /*onView(withId(R.id.imageTrending2))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())
            .perform(doubleClick())
            .perform(longClick())*/

        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText("Inception"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.horizontalScrollView_2))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())

        onView(withId(R.id.leftIconLayout))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText("Once upon a time in america"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.radio_actors))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText("Leonardo Di Caprio"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.radio_tv_series))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())

        onView(withId(R.id.searchBarText2))
            .check(matches(isDisplayed()))
            .perform(click())
            .perform(clearText())
            .perform(typeText("The Sopranos"))
            .perform(pressImeActionButton())
            .perform(closeSoftKeyboard())

        onView(withId(R.id.toolbarbutton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.body))
            .check(matches(isDisplayed()))
            .perform(swipeUp())

    }
}