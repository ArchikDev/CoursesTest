package com.example.coursestest.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coursestest.R
import com.example.coursestest.data.models.Course
import com.example.coursestest.presentation.components.SearchFilterPanel
import com.example.coursestest.presentation.theme.DarkGray
import com.example.coursestest.presentation.theme.Glass
import com.example.coursestest.presentation.theme.Green
import com.example.coursestest.presentation.theme.LightGray
import com.example.coursestest.presentation.theme.White50

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchFilterPanel()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sortDateTxt),
                style = MaterialTheme.typography.labelLarge,
                color = Green
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.icon_sort),
                contentDescription = null,
                tint = Green
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        when(uiState.value) {
            MainViewModel.MainEvent.Error -> {
                Text("Error")
            }
            MainViewModel.MainEvent.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(40.dp)
                    )
                }
            }
            MainViewModel.MainEvent.Nothing -> {}
            is MainViewModel.MainEvent.Success -> {
                val courses = (uiState.value as MainViewModel.MainEvent.Success).courses

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (courses.isNotEmpty()) {
                        items(courses) { course ->
                            CourseCard(course)
                        }
                    } else {
                        item {
                            Text(
                                text = stringResource(R.string.notCourseTxt)
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    val isFav = remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.padding(bottom = 16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Yellow)
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {
                        isFav.value = !isFav.value
                    },
                    modifier = Modifier.size(28.dp).align(Alignment.TopEnd),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Glass,
                    ),
                ) {
                    if (isFav.value) {
                        Icon(
                            painter = painterResource(R.drawable.icon_fav_active),
                            contentDescription = null,
                            tint = Green
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.icon_fav),
                            contentDescription = null
                        )
                    }

                }
                Row(
                    modifier = Modifier.align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .height(22.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Glass)
                            .padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_star),
                            contentDescription = null,
                            tint = Green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = course.rate,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Row(
                        modifier = Modifier
                            .height(22.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Glass)
                            .padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = course.startDate,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = course.text,
                    style = MaterialTheme.typography.bodySmall,
                    color = White50,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${course.price} ₽",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        modifier = Modifier.clickable {},
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.MoreTxt),
                            style = MaterialTheme.typography.labelMedium,
                            color = Green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_arrow_right),
                            contentDescription = null,
                            tint = Green
                        )
                    }
                }
            }

        }
    }
}
