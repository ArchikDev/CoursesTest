package com.example.coursestest.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coursestest.R
import com.example.coursestest.presentation.components.ButtonSimple

@Composable
fun OnBoardingScreen() {
    val tagCourseList = listOf(
        TagCourse(name = "1С Администрирование"),
        TagCourse(name = "RabbitMQ", rotate = "left"),
        TagCourse(name = "Трафик"),
        TagCourse(name = "Контент маркетинг"),
        TagCourse(name = "B2B маркетинг"),
        TagCourse(name = "Google Аналитика"),
        TagCourse(name = "UX исследователь"),
        TagCourse(name = "Веб-аналитика"),
        TagCourse(name = "Big Data", rotate = "right"),
        TagCourse(name = "Геймдизайн"),
        TagCourse(name = "Веб-дизайн"),
        TagCourse(name = "Cinema 4D"),
        TagCourse(name = "Промпт инженеринг"),
        TagCourse(name = "Webflow"),
        TagCourse(name = "Three.js", rotate = "left"),
        TagCourse(name = "Парсинг"),
        TagCourse(name = "Python-разработка")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Тысячи курсов\nв одном месте",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            Box() {

            }
        }

        ButtonSimple(
            text = stringResource(R.string.nextTxt),
            onClick = {}
        )

    }
}

data class TagCourse(
    val name: String,
    val rotate: String? = null
)