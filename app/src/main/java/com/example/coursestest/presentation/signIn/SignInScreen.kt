package com.example.coursestest.presentation.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coursestest.R
import com.example.coursestest.presentation.components.ButtonSimple
import com.example.coursestest.presentation.components.FieldInput
import com.example.coursestest.presentation.theme.Blue
import com.example.coursestest.presentation.theme.Green
import com.example.coursestest.presentation.theme.OrangeGradient
import com.example.coursestest.presentation.theme.Stroke

@Composable
fun SignInScreen() {
    val emailInput = remember { mutableStateOf("") }
    val passwordInput = remember { mutableStateOf("") }

    val urlHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.signinTxt), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(34.dp))
        FieldInput(
            value = emailInput.value,
            onValueChanged = {
                emailInput.value = it
            },
            label = stringResource(R.string.emailTxt),
            placeholder = stringResource(R.string.emailPlaceholderTxt)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FieldInput(
            type = "password",
            value = passwordInput.value,
            onValueChanged = {
                passwordInput.value = it
            },
            label = stringResource(R.string.passwordLabelTxt),
            placeholder = stringResource(R.string.passwordPlaceholderTxt)
        )
        Spacer(modifier = Modifier.height(18.dp))
        ButtonSimple(
            isEnabled = true,
            text = stringResource(R.string.signinTxt),
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = stringResource(R.string.notAccountTxt),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = stringResource(R.string.regTxt),
                    color = Green,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.passForgotTxt),
                color = Green,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Stroke
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row {
                ButtonSimple(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_vk,
                    bgColor = Blue,
                    onClick = {
                        urlHandler.openUri("https://vk.com/")
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                ButtonSimple(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_ok,
                    bgBrush = OrangeGradient,
                    onClick = {
                        urlHandler.openUri("https://ok.ru/")
                    }
                )
            }
        }

    }
}