package com.example.indroydlab.ui.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.screen.SettingScreen
import com.example.indroydlab.ui.screen.auth.authNavigation.AuthNavigation
import com.example.indroydlab.ui.screen.catalog.CatalogNavigation
import com.example.indroydlab.ui.screen.home.HomeNavigationBar

@Composable
fun NavigationRoot() {

    val rootBackStack = rememberNavBackStack(Routes.Auth)

    NavDisplay(
        backStack = rootBackStack,
        onBack = { rootBackStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Routes.Auth> {
                AuthNavigation(
                    onLogin = {
                        rootBackStack.remove(Routes.Auth)
                        rootBackStack.add(Routes.HomeNavigationBar)
                    }
                )
            }
            entry<Routes.HomeNavigationBar> {
                HomeNavigationBar(
                    navigateToSettings = {
                        rootBackStack.add(Routes.Catalog)
                    }
                )
            }
            entry<Routes.Catalog> {
                CatalogNavigation()
            }
        }
    )

}