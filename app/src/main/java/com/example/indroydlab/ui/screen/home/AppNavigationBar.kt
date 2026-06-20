package com.example.indroydlab.ui.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indroydlab.R
import com.example.indroydlab.ui.theme.*

data class NavItem(
    val key: Home,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun AppNavigationBar(
    currentKey: Home?,
    onNavigate: (Home) -> Unit
) {
    val navItems = listOf(
        NavItem(Home.Dashboard, stringResource(R.string.nav_home),    Icons.Filled.Home,       Icons.Outlined.Home),
        NavItem(Home.Catalog,   stringResource(R.string.nav_catalog), Icons.Filled.Inventory2, Icons.Outlined.Inventory2),
        NavItem(Home.Setting,   stringResource(R.string.nav_setting), Icons.Filled.Settings,   Icons.Outlined.Settings)
    )

    Surface(
        modifier = Modifier.fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 20.dp),
                ambientColor = Color.Black.copy(alpha = 0.08f),
                spotColor = Color.Black.copy(alpha = 0.08f)
            )
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                NavBarItem(
                    item = item,
                    isSelected = currentKey == item.key,
                    onSelect = { onNavigate(item.key) }
                )
            }
        }
    }
}

@Suppress("DEPRECATION")
@Composable
private fun NavBarItem(
    item: NavItem,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val view = LocalView.current
    val interactionSource = remember { MutableInteractionSource() }

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) LofazBlue else UnselectedColor,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "iconColor"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) LofazBlue else UnselectedColor,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "textColor"
    )

    Column(
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp).clickable { onSelect() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.label,
            tint = iconColor
        )
        Text(
            text = item.label,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}