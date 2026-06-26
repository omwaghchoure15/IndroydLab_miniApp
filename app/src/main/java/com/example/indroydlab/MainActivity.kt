package com.example.indroydlab

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.indroydlab.data.storage.LANGUAGE_KEY
import com.example.indroydlab.data.storage.languageDataStore
import com.example.indroydlab.ui.navigation.NavigationRoot
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var remoteConfig: FirebaseRemoteConfig


    override fun attachBaseContext(newBase: Context) {
        val prefs = runBlocking {
            newBase.languageDataStore.data.first()
        }
        val langCode = prefs[LANGUAGE_KEY] ?: "en"
        super.attachBaseContext(newBase.applyLocale(langCode))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        // 1. Initialize
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0 // 0 for testing, change to 3600 for production
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(ConfigManager.defaults)

        // 2. Fetch
        Log.d("RemoteConfig", "Starting fetch...")
        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            val TAG = "RemoteConfig"
            if (task.isSuccessful) {
                // task.result is true if the config was updated with fetched values
                val updated = task.result
                Log.d(TAG, "Fetch Successful! Hits Firebase: Yes")
                Log.d(TAG, "New values activated from server: $updated")
                Log.d(TAG, "Final URL in use: ${ConfigManager.apiBaseUrl}")

                if (updated) {
                    Toast.makeText(this, "Remote Config Updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                val exception = task.exception
                Log.e(TAG, "Fetch Failed: ${exception?.message}")
                Toast.makeText(this, "Fetch Failed", Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            IndroydLabTheme {
                NavigationRoot(intentData = intent?.data)
            }
        }
    }
}

@Suppress("DEPRECATION")
fun Context.applyLocale(langCode: String): Context {
    val locale = Locale(langCode)
    Locale.setDefault(locale)
    val config = resources.configuration.apply {
        setLocale(locale)
        setLayoutDirection(locale)
    }
    return createConfigurationContext(config)
}