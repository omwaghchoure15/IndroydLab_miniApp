package com.example.indroydlab.ui.screen.home

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Home: NavKey {
    @Serializable
    data object Dashboard: Home()
    @Serializable
    data object Catalog: Home()
    @Serializable
    data object Setting: Home()
}