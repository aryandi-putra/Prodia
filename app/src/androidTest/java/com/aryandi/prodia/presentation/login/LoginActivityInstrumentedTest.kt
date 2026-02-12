package com.aryandi.prodia.presentation.login

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aryandi.prodia.BuildConfig
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for LoginActivity.
 * These tests run on an Android device or emulator.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        // Initialize Espresso Intents for intent verification
        Intents.init()
    }

    @After
    fun tearDown() {
        // Release Espresso Intents
        Intents.release()
    }

    @Test
    fun activityLaunches() {
        activityRule.scenario.onActivity { activity ->
            assertNotNull("Activity should launch successfully", activity)
        }
    }

    @Test
    fun activityIsNotFinishing() {
        activityRule.scenario.onActivity { activity ->
            assertFalse("Activity should not be finishing on start", activity.isFinishing)
        }
    }

    @Test
    fun activityHasCorrectPackageName() {
        activityRule.scenario.onActivity { activity ->
            assertEquals(
                "Activity package name should match",
                "com.aryandi.prodia",
                activity.packageName
            )
        }
    }

    @Test
    fun activityIsComponentActivity() {
        activityRule.scenario.onActivity { activity ->
            assertTrue(
                "LoginActivity should be an instance of ComponentActivity",
                activity is androidx.activity.ComponentActivity
            )
        }
    }

    @Test
    fun buildConfigContainsAuth0Credentials() {
        // Verify Auth0 configuration is properly set
        assertNotNull("Auth0 client ID should be set", BuildConfig.COM_AUTH0_CLIENTID)
        assertNotNull("Auth0 domain should be set", BuildConfig.COM_AUTH0_DOMAIN)
        assertTrue(
            "Auth0 client ID should not be empty",
            BuildConfig.COM_AUTH0_CLIENTID.isNotEmpty()
        )
        assertTrue(
            "Auth0 domain should not be empty",
            BuildConfig.COM_AUTH0_DOMAIN.isNotEmpty()
        )
    }

    @Test
    fun activityRecreationSucceeds() {
        activityRule.scenario.onActivity { activity ->
            assertNotNull("Initial activity should not be null", activity)
        }

        // Recreate the activity
        activityRule.scenario.recreate()

        activityRule.scenario.onActivity { activity ->
            assertNotNull("Recreated activity should not be null", activity)
            assertFalse("Recreated activity should not be finishing", activity.isFinishing)
        }
    }

    @Test
    fun activityLifecycleTransitions() {
        // Test activity lifecycle state transitions
        activityRule.scenario.onActivity { activity ->
            assertNotNull("Activity in resumed state should not be null", activity)
        }

        // Move to paused state
        activityRule.scenario.moveToState(androidx.lifecycle.Lifecycle.State.STARTED)
        
        activityRule.scenario.onActivity { activity ->
            assertNotNull("Activity in started state should not be null", activity)
        }

        // Move back to resumed
        activityRule.scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)
        
        activityRule.scenario.onActivity { activity ->
            assertNotNull("Activity returned to resumed state should not be null", activity)
        }
    }

    @Test
    fun activityWithCustomIntentLaunches() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)
        val scenario = ActivityScenario.launch<LoginActivity>(intent)
        
        scenario.onActivity { activity ->
            assertNotNull("Activity launched with custom intent should not be null", activity)
            assertFalse("Activity should not be finishing", activity.isFinishing)
        }
        
        scenario.close()
    }
}
