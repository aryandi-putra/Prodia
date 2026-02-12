package com.aryandi.prodia.presentation.login

import android.os.Build
import com.aryandi.prodia.BuildConfig
import com.auth0.android.Auth0
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.*

/**
 * Unit tests for LoginActivity using Robolectric.
 * These tests run on the JVM without requiring an Android device or emulator.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class LoginActivityTest {

    private lateinit var activity: LoginActivity

    @Before
    fun setUp() {
        // Create the activity
        activity = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .get()
    }

    @Test
    fun `activity should not be null after creation`() {
        assertNotNull("Activity should be created successfully", activity)
    }

    @Test
    fun `activity should be initialized properly`() {
        assertNotNull("Activity should be initialized", activity)
        assertFalse("Activity should not be destroyed", activity.isDestroyed)
        assertFalse("Activity should not be finishing", activity.isFinishing)
    }

    @Test
    fun `activity should have Auth0 configuration from BuildConfig`() {
        // Verify that BuildConfig contains the required Auth0 configuration
        assertNotNull("Auth0 client ID should be configured", BuildConfig.COM_AUTH0_CLIENTID)
        assertNotNull("Auth0 domain should be configured", BuildConfig.COM_AUTH0_DOMAIN)
        assertFalse("Auth0 client ID should not be empty", BuildConfig.COM_AUTH0_CLIENTID.isEmpty())
        assertFalse("Auth0 domain should not be empty", BuildConfig.COM_AUTH0_DOMAIN.isEmpty())
    }

    @Test
    fun `activity should call onCreate without crashing`() {
        // This test verifies that the activity lifecycle methods execute without exceptions
        val controller = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .start()
            .resume()

        assertNotNull("Activity controller should not be null", controller)
        assertNotNull("Activity should be created", controller.get())
        assertFalse("Activity should not be destroyed", controller.get().isDestroyed)
    }

    @Test
    fun `activity should handle lifecycle transitions`() {
        val controller = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .start()
            .resume()
            .pause()
            .stop()

        assertNotNull("Activity should handle lifecycle transitions", controller.get())
    }

    @Test
    fun `activity should be a ComponentActivity`() {
        assertTrue(
            "LoginActivity should extend ComponentActivity",
            activity is androidx.activity.ComponentActivity
        )
    }
}
