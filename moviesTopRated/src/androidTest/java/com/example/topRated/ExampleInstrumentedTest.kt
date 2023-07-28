package com.example.topRated

import android.content.ComponentName
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.topRated.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenario = activityScenarioRule<movieTopRatedActivity>(
        Intent.makeMainActivity(ComponentName("com.example.topRated.test", "com.example.topRated.test.movieTopRatedActivity"))

    )

    /*@get:Rule
    val activityRule = object : ActivityTestRule<movieTopRatedActivity>(movieTopRatedActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(InstrumentationRegistry.getInstrumentation().targetContext, movieTopRatedActivity::class.java)
                .apply {
                    putExtra("id", "238")
                    putExtra("language", "en-US")
                    putExtra("type", "movie")
                }
        }
    }*/

    @Before
    fun setUp() {
        activityScenario.scenario.onActivity {
            it.intent.apply{
                putExtra("id", "238")
                putExtra("language", "en-US")
                putExtra("type", "movie")
            }
        }
    }
    @Test
    fun shouldShowSearchTvWhenClickedOnTopRatedMovies() {
        onView(withId(R.id.imgMovie))
            .check(matches(isDisplayed()))

        onView(withId(R.id.title_origLang))
            .check(matches(isDisplayed()))

        onView(withId(R.id.goBack))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .perform(click())
    }

}