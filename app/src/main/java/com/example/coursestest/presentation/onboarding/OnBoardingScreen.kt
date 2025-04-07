package com.example.coursestest.presentation.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coursestest.R
import com.example.coursestest.presentation.components.ButtonSimple
import com.example.coursestest.presentation.theme.Glass90
import com.example.coursestest.presentation.theme.Green
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
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
        TagCourse(name = "Three.js", rotate = "right"),
        TagCourse(name = "Парсинг"),
        TagCourse(name = "Python-разработка")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.onBoardTitleTxt),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            BoxSwipe(tagCourseList)
        }

        ButtonSimple(
            text = stringResource(R.string.nextTxt),
            onClick = {
                viewModel.saveAppEntry()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

    }
}


@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BoxSwipe(tagCourseList: List<TagCourse>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val posterWidthDp = screenWidth * 1.6f
    val scrollX = with(LocalDensity.current) {
        (posterWidthDp - screenWidth).toPx() / 2
    }

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    scope.launch {
        scrollState.scrollBy(scrollX)
    }

    FlowRow(
        modifier = Modifier
            .horizontalScroll(
                state = scrollState
            )
            .width(posterWidthDp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Bottom
    ) {
        tagCourseList.forEach { tag ->
            val rotateBtn = when(tag.rotate) {
                "left" -> {
                    Modifier
                        .offset {
                            IntOffset(0, 40)
                        }
                        .zIndex(0f)
                        .graphicsLayer {
                            rotationZ = -20f
                        }
                }
                "right" -> {
                    Modifier
                        .offset {
                            IntOffset(0, -40)
                        }
                        .zIndex(0f)
                        .graphicsLayer {
                            rotationZ = 20f
                        }
                }
                else -> Modifier.zIndex(1f)
            }

            ButtonSimple(
                text = tag.name,
                bgColor = if (tag.rotate?.isNotEmpty() == true) Green else Glass90,
                modifier = Modifier
                    .padding(
                        bottom = 8.dp
                    )
                    then(rotateBtn),
                height = 60.dp
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

data class TagCourse(
    val name: String,
    val rotate: String? = null
)