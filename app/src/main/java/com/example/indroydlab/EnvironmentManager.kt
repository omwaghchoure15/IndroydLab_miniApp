package com.example.indroydlab

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.BuildConfig
import com.google.firebase.remoteconfig.remoteConfig

object EnvironmentManager {
    private const val PREFS_NAME = "dev_settings_prefs"
    private const val KEY_URL_OVERRIDE = "api_url_override"

    // Resolves which URL to use based on build type and local overrides
    fun getBaseUrl(context: Context): String {
        // 1. In DEBUG mode, check for a local SharedPreferences override first
        if (BuildConfig.DEBUG) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val overrideUrl = prefs.getString(KEY_URL_OVERRIDE, null)
            if (!overrideUrl.isNullOrEmpty()) {
                return overrideUrl
            }
        }

        // 2. Fallback to the production default fetched from Firebase Remote Config
        // Assumes "api_base_url" is your Remote Config parameter key
        return Firebase.remoteConfig.getString("api_base_url")
    }

    // Call this ONLY from your developer UI to save the selected URL
    fun setUrlOverride(context: Context, url: String?) {
        if (BuildConfig.DEBUG) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_URL_OVERRIDE, url).apply()
        }
    }
}
