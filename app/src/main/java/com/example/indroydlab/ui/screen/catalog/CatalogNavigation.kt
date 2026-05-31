package com.example.indroydlab.ui.screen.catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.navigation.Routes
import com.example.indroydlab.ui.navigation.Routes.Catalog.ProductDetail
import com.example.indroydlab.ui.screen.SettingScreen
import com.example.indroydlab.ui.screen.auth.authNavigation.AuthNavigation
import com.example.indroydlab.ui.screen.home.BottomBarScreen
import com.example.indroydlab.ui.screen.home.HomeNavigationBar

@Composable
fun CatalogNavigation() {

    val backStack = rememberNavBackStack(Routes.Catalog.Setting)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Routes.Catalog.Setting> {
                SettingScreen()
            }
        }
    )
}