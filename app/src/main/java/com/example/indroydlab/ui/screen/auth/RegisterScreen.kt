package com.example.indroydlab.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.ui.screen.auth.authNavigation.ShareAuthViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    shareAuthViewModel: ShareAuthViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val localCounter by viewModel.counter.collectAsStateWithLifecycle()
    val sharedCounter by shareAuthViewModel.counter.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = shareAuthViewModel::bump
        ) {
            Text(
                text = "Shared counter: $sharedCounter"
            )
        }
        Button(
            onClick = viewModel::bump
        ) {
            Text(
                text = "Local counter: $sharedCounter"
            )
        }
    }
}
