package com.example.indroydlab.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.screen.DashboardScreen
import com.example.indroydlab.ui.screen.SettingScreen
import com.example.indroydlab.ui.screen.catalog.CatalogScreen

@Composable
fun HomeScreen(
    selectedTab: String? = null,
    onProductClick: (String) -> Unit
){
    val initialTab = when(selectedTab?.lowercase()) {
        "catalog" -> Home.Catalog
        "setting" -> Home.Setting
        else -> Home.Dashboard
    }
    val backStack = rememberNavBackStack(initialTab)

    Scaffold(
        bottomBar = {
            AppNavigationBar(
                currentKey = backStack.lastOrNull() as Home?,
                onNavigate = { destination ->
                    if (backStack.lastOrNull() != destination ){
                        // Reset the stack to the new tab root when switching
                        backStack.clear()
                        backStack.add(destination)
                    }
                }
            )
        }
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Home.Dashboard> {
                    DashboardScreen()
                }
                entry<Home.Catalog> {
                    CatalogScreen(onProductClick = onProductClick)
                }
                entry<Home.Setting> {
                    SettingScreen()
                }
            }
        )
    }
}