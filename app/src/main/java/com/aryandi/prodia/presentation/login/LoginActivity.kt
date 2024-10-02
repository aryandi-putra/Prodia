package com.aryandi.prodia.presentation.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.aryandi.prodia.BuildConfig
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials

class LoginActivity : ComponentActivity() {
    private lateinit var account: Auth0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0(
            BuildConfig.COM_AUTH0_CLIENTID,
            BuildConfig.COM_AUTH0_DOMAIN
        )

        loginWithBrowser()
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val accessToken = credentials.accessToken
                }
            })
    }

    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(this, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    // The user has been logged out!
                }

                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!
                }
            })
    }
}