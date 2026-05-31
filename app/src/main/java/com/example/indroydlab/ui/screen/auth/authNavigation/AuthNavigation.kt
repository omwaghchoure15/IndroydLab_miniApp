package com.example.indroydlab.ui.screen.auth.authNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.indroydlab.ui.navigation.Routes
import com.example.indroydlab.ui.screen.auth.LoginScreen
import com.example.indroydlab.ui.screen.auth.LoginViewModel
import com.example.indroydlab.ui.screen.auth.RegisterScreen
import com.example.indroydlab.ui.screen.auth.RegisterViewModel

@Composable
fun AuthNavigation(
    onLogin: ()-> Unit,
    modifier: Modifier = Modifier
) {

    val authBackStack = rememberNavBackStack(Routes.Auth.Login)

    val shareAuthViewModel = viewModel { ShareAuthViewModel() }
    NavDisplay(
        backStack = authBackStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Routes.Auth.Login> {
                LoginScreen(
                    viewModel = viewModel { LoginViewModel()} ,
                    shareAuthViewModel = shareAuthViewModel  ,
                    onLogin = onLogin,
                    onRegister = { authBackStack.add(Routes.Auth.Register) }
                )
            }
            entry<Routes.Auth.Register> {
                RegisterScreen(
                    viewModel = viewModel { RegisterViewModel() },
                    shareAuthViewModel = shareAuthViewModel,
                )
            }
        }
    )
}