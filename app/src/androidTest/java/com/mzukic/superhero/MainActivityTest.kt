package com.mzukic.superhero

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mzukic.superhero.ui.SuperHeroActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<SuperHeroActivity> = ActivityTestRule(
        SuperHeroActivity::class.java
    )
}
