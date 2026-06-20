package com.example.indroydlab.ui.screen.catalog

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.PrimaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.indroydlab.ui.screen.catalog.cart.CartScreen
import com.example.indroydlab.ui.screen.catalog.product.ProductScreen
import com.example.indroydlab.ui.theme.LofazBlue
import com.example.indroydlab.ui.theme.PageBg
import com.example.indroydlab.ui.theme.UnselectedColor
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
sealed interface Catalog: NavKey {
    @Serializable data object Product: Catalog
    @Serializable data object Cart: Catalog
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen( onProductClick: (String) -> Unit ) {

    val backStack = rememberNavBackStack(Catalog.Product)

    val scope = rememberCoroutineScope()
    val tabs = remember { listOf(Catalog.Product, Catalog.Cart) }
    val pagerState = rememberPagerState(pageCount = {tabs.size})
    val selected = pagerState.currentPage

    Scaffold(
        contentWindowInsets = WindowInsets(0,0,0,0),
        topBar = {
            PrimaryTabRow(
                selectedTabIndex = selected,
                modifier = Modifier.fillMaxWidth(),
                containerColor = PageBg,
                indicator = {
                    PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selected),
                        color = LofazBlue,
                        width = 64.dp
                    ) },
                divider = {}
            ) {
                tabs.forEachIndexed { index, key ->
                    Tab(
                        selected = selected == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = when (key) {
                                    Catalog.Product -> "Products"
                                    Catalog.Cart -> "Cart"
                                },
                                fontWeight = if (selected == index) FontWeight.Bold else FontWeight.Medium
                            )
                        },
                        selectedContentColor = LofazBlue,
                        unselectedContentColor = UnselectedColor
                    )
                }
            }
        },
        containerColor = PageBg
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            beyondViewportPageCount = 1,
        ){ page ->
            when(tabs[page]) {
                Catalog.Product  -> ProductScreen(
                    onBack = {backStack.removeLastOrNull() },
                    onProductClick = onProductClick
                )
                Catalog.Cart -> CartScreen()
            }
        }
    }
}