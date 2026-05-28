package com.example.indroydlab.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.indroydlab.ui.shared.topbar.TopbarApp

@Composable
fun RegisterScreen() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }
    val firstNameTextState: TextFieldState = rememberTextFieldState("")
    val lastNameTextState: TextFieldState = rememberTextFieldState("")
    val companyNameTextState: TextFieldState = rememberTextFieldState("")
    val slugTextState: TextFieldState = rememberTextFieldState("")

    Scaffold(
        topBar = {
            TopbarApp(
                title = "Register",
                onClickBack = { })
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                state = firstNameTextState,
                label = { Text("First Name") },
                placeholder = { Text("Ram") },
            )
            OutlinedTextField(
                state = lastNameTextState,
                label = { Text("Last Name") },
                placeholder = { Text("Shah") },
            )
            OutlinedTextField(
                state = companyNameTextState,
                label = { Text("Company Name") },
                placeholder = { Text("Shopper") },
            )
            OutlinedTextField(
                state = slugTextState,
                label = { Text("slug") },
                placeholder = { Text("shopper") },
            )
            Button(
                onClick = {
//                    val body = PostRegisterBody(
//                        firstName = firstNameTextState.text.toString(),
//                        lastName = lastNameTextState.text.toString(),
//                        companyName = companyNameTextState.text.toString(),
//                        slug = slugTextState.text.toString(),
//                    )
//
//                    conroutineScope.launch {
//                        submitBtn(body, viewModel , context, imageInfo.value)
//                    }

                }
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}