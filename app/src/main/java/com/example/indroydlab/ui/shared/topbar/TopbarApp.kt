package com.example.indroydlab.ui.shared.topbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.indroydlab.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarApp(
    title: String,
    onClickBack: () -> Unit = { } ,
    windowInsets: WindowInsets = WindowInsets.statusBars
) {
    TopAppBar(
        windowInsets = windowInsets,
        colors = topAppBarColors(
            containerColor = colorScheme.primary,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = colorScheme.onPrimary,
            actionIconContentColor = Color.Unspecified
        ),
        navigationIcon = {
            IconButton(onClick = onClickBack ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = White
                )
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}