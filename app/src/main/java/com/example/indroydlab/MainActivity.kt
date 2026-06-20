package com.example.indroydlab

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.indroydlab.data.storage.LANGUAGE_KEY
import com.example.indroydlab.data.storage.languageDataStore
import com.example.indroydlab.ui.navigation.NavigationRoot
import com.example.indroydlab.ui.theme.IndroydLabTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale

class MainActivity : ComponentActivity() {

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
        setContent {
            IndroydLabTheme {
                // Pass the intent data to the NavigationRoot to handle potential deep links
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