package com.example.coursestest.presentation.favorite

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coursestest.R
import com.example.coursestest.presentation.components.CourseCard
import com.example.coursestest.presentation.main.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val courseFavorite = viewModel.coursesFavorite.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            stringResource(R.string.favoriteTxt),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (courseFavorite.value.isNotEmpty()) {
                items(courseFavorite.value) { course ->
                    CourseCard(
                        course = course,
                        onClickFavorite = {
                            viewModel.setFavoriteCourse(course)
                        }
                    )
                }
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.notFavoriteTxt),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }
}