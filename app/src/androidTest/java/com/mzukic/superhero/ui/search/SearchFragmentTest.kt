package com.mzukic.superhero.ui.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.mzukic.superhero.MockServerDispatcher
import com.mzukic.superhero.R
import com.mzukic.superhero.ui.SuperHeroActivity
import com.mzukic.superhero.util.RecyclerViewItemCountAssertion.Companion.withItemCount
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SearchFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    lateinit var mockWebServer: MockWebServer

    @Inject lateinit var okHttp: OkHttpClient

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttp))
    }

    @After
    fun clear() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSearchSuccess(): Unit = runBlocking {
        val scenario = ActivityScenario.launch(SuperHeroActivity::class.java)
        onView(withId(R.id.container_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.edit_text_search)).perform(typeText("batman"))
        onView(withId(R.id.button_submit)).perform(click())
        delay(1000)
        onView(withId(R.id.container_error)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.container_no_result)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.button_clear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recycler_view)).check(withItemCount(3))
    }

    @Test
    fun testSearchError(): Unit = runBlocking {
        val scenario = ActivityScenario.launch(SuperHeroActivity::class.java)
        onView(withId(R.id.container_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.edit_text_search)).perform(typeText("error"))
        onView(withId(R.id.button_submit)).perform(click())
        delay(1000)
        onView(withId(R.id.container_error)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.recycler_view)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.container_no_result)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.button_clear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testSearchNoResults(): Unit = runBlocking {
        val scenario = ActivityScenario.launch(SuperHeroActivity::class.java)
        onView(withId(R.id.container_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.edit_text_search)).perform(typeText("noresults"))
        onView(withId(R.id.button_submit)).perform(click())
        delay(1000)
        onView(withId(R.id.container_error)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.container_empty)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.recycler_view)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.container_no_result)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.button_clear)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
