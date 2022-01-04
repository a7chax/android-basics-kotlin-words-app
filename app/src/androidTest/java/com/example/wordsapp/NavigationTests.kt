package com.example.wordsapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

  private lateinit var navController: TestNavHostController

  @Before
  fun setup(){
    navController = TestNavHostController(
      ApplicationProvider.getApplicationContext()
    )


    val letterListScenario = launchFragmentInContainer<LetterListFragment>(
      themeResId = R.style.Theme_Words
    )

    letterListScenario.onFragment { fragment ->
      navController.setGraph(R.navigation.nav_graph)
      Navigation.setViewNavController(fragment.requireView(), navController)
    }

  }



  @Test
  fun navigate_to_word(){

    onView(withId(R.id.recycler_view)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
    )

    assertEquals(
      navController.currentDestination?.id, R.id.wordListFragment
    )

  }


}