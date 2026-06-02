package com.example.indroydlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.indroydlab.ui.navigation.NavigationRoot
import com.example.indroydlab.ui.theme.IndroydLabTheme

class MainActivity : ComponentActivity() {
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
