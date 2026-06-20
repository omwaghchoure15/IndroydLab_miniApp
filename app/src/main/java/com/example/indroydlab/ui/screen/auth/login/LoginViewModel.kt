package com.example.indroydlab.ui.screen.auth.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    init {
        println("LoginViewModel: init")
    }
    fun bump(){
        _counter.value++
    }

    @SuppressLint("EmptySuperCall")
    override fun onCleared() {
        super.onCleared()
        println("LoginViewModel: Cleared")
    }
}