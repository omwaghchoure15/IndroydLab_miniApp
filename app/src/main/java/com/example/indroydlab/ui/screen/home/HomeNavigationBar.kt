package com.example.indroydlab.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.R
import com.example.indroydlab.ui.navigation.Routes
import com.example.indroydlab.ui.screen.cart.CartScreen
import com.example.indroydlab.ui.screen.catalog.ProductDetailScreen
import com.example.indroydlab.ui.screen.product.ProductScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationBar( navigateToSettings: () -> Unit
) {
    val backStack = rememberNavBackStack(BottomBarScreen.Product)

    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(BottomBarScreen.Product) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nested Graph") },
                actions = {
                    IconButton(onClick = navigateToSettings) {
                        Icon(
                            painter = painterResource(R.drawable.settings),
                            contentDescription = "Settings icon"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                        icon = { Icon(painterResource(destination.icon), "$destination icon") },
                        label = { Text(text = destination.title) },
                        onClick = {
                            if (backStack.lastOrNull() != destination) {
                                if (backStack.lastOrNull() in bottomBarItems) {
                                    backStack.removeAt(backStack.lastIndex)
                                }
                                backStack.add(destination)
                                currentBottomBarScreen = destination
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<BottomBarScreen.Product> {
                    ProductScreen(
                        onBack = { },
                        onProductClick = { backStack.add(Routes.Catalog.ProductDetail(it)) }
                    )
                }
                entry<BottomBarScreen.Cart> {
                    CartScreen()
                }
                entry<BottomBarScreen.Profile> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Profile",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                }
                entry<Routes.Catalog.ProductDetail> { key ->
                    ProductDetailScreen(
                        onBack = { backStack.removeLastOrNull() },
                        productId = key.productId
                    )
                }
            }
        )
    }
}