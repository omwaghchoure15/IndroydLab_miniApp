package com.example.indroydlab.ui.screen.auth.authNavigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShareAuthViewModel: ViewModel() {

    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    init {
        println("ShareAuthViewModel: init")
    }
    fun bump(){
        _counter.value++
    }

    override fun onCleared() {
        super.onCleared()
        println("ShareAuthViewModel: Cleared")
    }

}