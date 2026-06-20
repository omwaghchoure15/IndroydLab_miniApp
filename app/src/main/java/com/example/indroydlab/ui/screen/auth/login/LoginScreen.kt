package com.example.indroydlab.ui.screen.auth.login

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.BorderStroke
import com.example.indroydlab.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.ui.screen.auth.ShareAuthViewModel

data class LanguageOption(
    val code   : String,
    val name   : String,   // native name
    val flag   : String    // emoji flag
)

val languages = listOf(
    LanguageOption("en", "English",  "🇬🇧"),
    LanguageOption("hi", "हिंदी",    "🇮🇳"),
    LanguageOption("mr", "मराठी",    "🇮🇳"),
    LanguageOption("gu", "ગુજરાતી", "🇮🇳"),
    LanguageOption("ta", "தமிழ்",   "🇮🇳"),
    LanguageOption("te", "తెలుగు",  "🇮🇳")
)

@SuppressLint("ContextCastToActivity")
@Composable
fun LoginScreen(
    viewModel : LoginViewModel = viewModel(),
    shareAuthViewModel : ShareAuthViewModel = viewModel(),
    onLogin : () -> Unit,
    onRegister : () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val localCounter by viewModel.counter.collectAsStateWithLifecycle()
    val sharedCounter by shareAuthViewModel.counter.collectAsStateWithLifecycle()
    val currentLang by shareAuthViewModel.currentLanguage.collectAsStateWithLifecycle()
    val activity = LocalContext.current as Activity

    Box(modifier = modifier.fillMaxSize()) {

        // ── Language dropdown — top right
        LanguageDropdown(
            current    = currentLang,
            languages  = languages,
            onSelected = { code -> shareAuthViewModel.setLanguage(code, activity) },
            modifier   = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        // ── Existing content — centred
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = shareAuthViewModel::bump) {
                Text("Shared counter: $sharedCounter")
            }
            Button(onClick = viewModel::bump) {
                Text("Local counter: $localCounter")
            }
            Button(onClick = onLogin) {
                Text(stringResource(R.string.login))
            }
            Button(onClick = onRegister) {
                Text(stringResource(R.string.register))
            }
        }
    }
}

@Composable
fun LanguageDropdown(
    current    : String,
    languages  : List<LanguageOption>,
    onSelected : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val selected = languages.find { it.code == current } ?: languages.first()

    Box(modifier) {
        // Trigger button
        Surface(
            onClick = { expanded = true },
            shape  = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            color  = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = selected.flag, fontSize = 18.sp)
                Text(text = selected.name, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { lang ->
                val isSelected = lang.code == current
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = lang.flag, fontSize = 18.sp)
                            Text(
                                text = lang.name,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    trailingIcon = {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },
                    onClick = {
                        onSelected(lang.code)
                        expanded = false
                    }
                )
            }
        }
    }
}