package com.example.indroydlab.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val LANGUAGE_KEY = stringPreferencesKey("language")

val Context.languageDataStore: DataStore<Preferences> by preferencesDataStore("language_prefs")

suspend fun DataStore<Preferences>.setLanguage(code: String) {
    edit { it[LANGUAGE_KEY] = code }
}

suspend fun DataStore<Preferences>.getLanguage(): String {
    return data.map { it[LANGUAGE_KEY] ?: "en" }.first()
}

fun DataStore<Preferences>.getLanguageFlow(): Flow<String> {
    return data.map { it[LANGUAGE_KEY] ?: "en" }
}