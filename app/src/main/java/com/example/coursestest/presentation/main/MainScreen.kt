package com.example.coursestest.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coursestest.R
import com.example.coursestest.presentation.components.CourseCard
import com.example.coursestest.presentation.components.SearchFilterPanel
import com.example.coursestest.presentation.theme.Green
import com.example.coursestest.utils.Loader
import com.example.coursestest.utils.toDateLong

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    var currentSortOrder by remember { mutableStateOf<SortOrder>(SortOrder.Ask) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchFilterPanel()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    currentSortOrder = if (currentSortOrder == SortOrder.Ask) {
                        SortOrder.Desc
                    } else {
                        SortOrder.Ask
                    }
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
                Loader()
            }
            MainViewModel.MainEvent.Nothing -> {}
            is MainViewModel.MainEvent.Success -> {
                val courses = (uiState.value as MainViewModel.MainEvent.Success).courses
                val coursesSorted = when(currentSortOrder) {
                    SortOrder.Ask -> courses.sortedBy { it.publishDate.toDateLong() }
                    SortOrder.Desc -> courses.sortedBy { it.publishDate.toDateLong() }.reversed()
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (courses.isNotEmpty()) {
                        items(coursesSorted) { course ->
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

sealed class SortOrder() {
    data object Desc: SortOrder()
    data object Ask: SortOrder()
}

