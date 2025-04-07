package com.example.coursestest.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coursestest.utils.CONTENT_DP

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .padding(
                start = CONTENT_DP,
                end = CONTENT_DP,
                top = CONTENT_DP
            )
            .fillMaxSize()
    ) {
        Text(
            "Профиль",
            style = MaterialTheme.typography.titleLarge
        )
    }
}