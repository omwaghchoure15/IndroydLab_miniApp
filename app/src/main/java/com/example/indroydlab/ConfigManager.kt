package com.example.indroydlab

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig

object ConfigManager {
    // These keys match your Firebase screenshot EXACTLY
    private const val KEY_URL_DEV = "url_dev"
    private const val KEY_URL_DEV_2 = "url_dev_2"
    private const val KEY_URL_PUBLIC = "url_public"
    private const val KEY_URL_PUBLIC_2 = "url_public_2"

    @SuppressLint("StaticFieldLeak")
    private val remoteConfig = Firebase.remoteConfig

    // This property automatically detects if you are in Debug or Release mode
    private val isDebug: Boolean
        get() = BuildConfig.DEBUG

    val apiBaseUrl: String
        get() {
            val key = if (isDebug) KEY_URL_DEV else KEY_URL_PUBLIC
            return remoteConfig.getString(key)
        }

    val apiBaseUrl2: String
        get() {
            val key = if (isDebug) KEY_URL_DEV_2 else KEY_URL_PUBLIC_2
            return remoteConfig.getString(key)
        }

    val defaults = mapOf(
        KEY_URL_DEV to "https://api.lofaz.in/api/",
        KEY_URL_DEV_2 to "https://api2.lofaz.in/api/",
        KEY_URL_PUBLIC to "https://api.lofaz.com/api/",
        KEY_URL_PUBLIC_2 to "https://api2.lofaz.com/api/"
    )
}

//2026-06-25 19:34:28.920  6092-6092  RemoteConfig            com.example.indroydlab               D  Fetch Successful! Hits Firebase: Yes
//2026-06-25 19:34:28.920  6092-6092  RemoteConfig            com.example.indroydlab               D  New values activated from server: false
//2026-06-25 19:34:28.921  6092-6092  RemoteConfig            com.example.indroydlab               D  Final URL in use: https://api.lofaz.in/api/