package com.example.indroydlab.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Defines all possible navigation destinations in the app.
 * Extending [NavKey] allows these objects to be used as keys in the Navigation 3 backstack.
 */

@Serializable
sealed interface Routes: NavKey {
    @Serializable
    data object Auth: Routes, NavKey {
        @Serializable data object Login: Routes, NavKey
        @Serializable data object Register: Routes, NavKey
    }

    @Serializable data object Home: Routes, NavKey
    @Serializable data object Catalog: Routes, NavKey

    @Serializable data class ProductDetail(val productId: String) : Routes

}