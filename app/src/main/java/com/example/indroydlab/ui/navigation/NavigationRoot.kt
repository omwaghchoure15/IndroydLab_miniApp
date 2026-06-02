package com.example.indroydlab.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.screen.auth.authNavigation.AuthNavigation
import com.example.indroydlab.ui.screen.catalog.CatalogScreen
import com.example.indroydlab.ui.screen.catalog.ProductDetailScreen
import com.example.indroydlab.ui.screen.home.HomeScreen

/**
 * The top-level navigation graph for the application.
 * 
 * @param intentData The URI from the starting Intent, used for deep link handling.
 */
@Composable
fun NavigationRoot(intentData: Uri? = null) {

    // Determine the initial backstack based on the presence of a deep link
    val initialRoutes = remember(intentData) {
        val deepLinkRoute = parseDeepLink(intentData)
        if (deepLinkRoute != null) {
            // For deep links, we provide a stack that allows the user to go "back" 
            // to the Home screen instead of exiting the app.
            listOf(Routes.Home, deepLinkRoute)
        } else {
            // Default starting destination
            listOf(Routes.Auth)
        }
    }

    // Initialize the backstack with the calculated initial routes
    // Spread the list into varargs as required by rememberNavBackStack
    val rootBackStack = rememberNavBackStack(*initialRoutes.toTypedArray())

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
                        rootBackStack.removeLastOrNull()
                        rootBackStack.add(Routes.Home)
                    }
                )
            }
            entry<Routes.Home> {
                HomeScreen(
                    onProductClick = { productId ->
                        rootBackStack.add(Routes.ProductDetail(productId))
                    }
                )
            }
            entry<Routes.Catalog> {
                CatalogScreen (
                    onProductClick = { productId ->
                        rootBackStack.add(Routes.ProductDetail(productId))
                    }
                )
            }
            entry<Routes.ProductDetail> { key ->
                ProductDetailScreen(
                    onBack = { rootBackStack.removeLastOrNull() },
                    productId = key.productId
                )
            }
        }
    )
}

/**
 * Parses an incoming URI to determine if it matches a known deep link pattern.
 * 
 * Supported hosts: indroydlab.app, indroydlab.com, omwaghchoure15.github.io
 * Pattern: https://{host}/catalog/product/{productId}
 * 
 * @param uri The incoming URI to parse.
 * @return A [Routes] object if the URI is a valid deep link, null otherwise.
 */
private fun parseDeepLink(uri: Uri?): Routes? {
    if (uri == null) return null
    
    // Check for valid host
    val host = uri.host
    val validHosts = listOf("indroydlab.app", "indroydlab.com", "omwaghchoure15.github.io")
    if (host !in validHosts) return null
    
    val pathSegments = uri.pathSegments
    
    // Pattern: /catalog/product/{id}
    // We check if "catalog" and "product" exist in the path to be more flexible 
    // with GitHub Pages sub-directories if necessary.
    val catalogIndex = pathSegments.indexOfFirst { it.equals("catalog", ignoreCase = true) }
    if (catalogIndex != -1 && pathSegments.size > catalogIndex + 2) {
        if (pathSegments[catalogIndex + 1].equals("product", ignoreCase = true)) {
            val productId = pathSegments[catalogIndex + 2]
            if (productId.isNotEmpty()) {
                return Routes.ProductDetail(productId)
            }
        }
    }
    
    return null
}