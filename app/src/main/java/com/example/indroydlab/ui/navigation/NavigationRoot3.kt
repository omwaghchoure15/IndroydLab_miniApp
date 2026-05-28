package com.example.indroydlab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.screen.CartScreen
import com.example.indroydlab.ui.screen.HomeScreen
import com.example.indroydlab.ui.screen.ProductDetailScreen
import com.example.indroydlab.ui.screen.ProductScreen
import com.example.indroydlab.ui.viewmodel.CartViewModel

@Composable
fun NavigationRoot() {

    val backStack = rememberNavBackStack(Routes.ProductKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
//        entryDecorators = listOf(
//            rememberSaveableStateHolderNavEntryDecorator(),
//            rememberViewModelStoreNavEntryDecorator()
//        ),
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen()
            }
            entry <Routes.ProductKey>{
                ProductScreen(
                    onback = { backStack.removeLastOrNull() },
                    // Correctly pass the productId from the screen to the route
                    onProductClick = { id ->
                        backStack.add(Routes.ProductDetail(productId = id.toString()))
                    }
                )
            }
            entry<Routes.ProductDetail> { key ->
                ProductDetailScreen(
                    onBack = {backStack.add(Routes.CartKey)},
                    productId = key.productId
                )
            }

            entry<Routes.CartKey> {
                val viewModel: CartViewModel = viewModel()
                CartScreen(viewModel = viewModel)
            }
        }
    )
}