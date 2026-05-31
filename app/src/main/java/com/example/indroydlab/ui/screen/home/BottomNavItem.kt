package com.example.indroydlab.ui.screen.home

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.example.indroydlab.R
import kotlinx.serialization.Serializable

val bottomBarItems = listOf<BottomBarScreen>(
    BottomBarScreen.Product,
    BottomBarScreen.Cart,
    BottomBarScreen.Profile,
)

@Serializable
sealed class BottomBarScreen(
    val icon: Int,
    val title: String,
): NavKey {
    @Serializable
    data object Product : BottomBarScreen(
        icon = R.drawable.home,
        title = "Home"
    )

    @Serializable
    data object Cart : BottomBarScreen(
        icon = R.drawable.search,
        title = "Search"
    )

    @Serializable
    data object Profile : BottomBarScreen(
        icon = R.drawable.person,
        title = "Profile"
    )
}

val BottomBarScreenSaver = Saver<BottomBarScreen, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            BottomBarScreen.Product::class.simpleName -> BottomBarScreen.Product
            BottomBarScreen.Cart::class.simpleName -> BottomBarScreen.Cart
            BottomBarScreen.Profile::class.simpleName -> BottomBarScreen.Profile
            else -> BottomBarScreen.Product
        }
    }
)