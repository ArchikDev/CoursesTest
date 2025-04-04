package com.example.coursestest.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coursestest.R
import com.example.coursestest.presentation.components.SearchFilterPanel
import com.example.coursestest.presentation.theme.Green

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
                            Text(course.title)
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