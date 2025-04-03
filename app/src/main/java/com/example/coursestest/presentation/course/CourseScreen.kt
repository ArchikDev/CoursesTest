package com.example.coursestest.presentation.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CourseScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Экран курса")
    }
}