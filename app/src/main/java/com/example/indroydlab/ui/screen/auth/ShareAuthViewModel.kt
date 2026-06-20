package com.example.indroydlab.ui.screen.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.indroydlab.data.storage.getLanguageFlow
import com.example.indroydlab.data.storage.languageDataStore
import com.example.indroydlab.data.storage.setLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShareAuthViewModel(private val app: Application) : AndroidViewModel(app) {

    // Existing counter
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()
    fun bump() { _counter.value++ }

    // ── Language
    private val dataStore = app.languageDataStore

    val currentLanguage: StateFlow<String> = dataStore
        .getLanguageFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "en"
        )

    fun setLanguage(code: String, activity: Activity) {
        viewModelScope.launch {
            dataStore.setLanguage(code)
            activity.recreate()
        }
    }

    @SuppressLint("EmptySuperCall")
    override fun onCleared() {
        super.onCleared()
        println("ShareAuthViewModel: Cleared")
    }
}