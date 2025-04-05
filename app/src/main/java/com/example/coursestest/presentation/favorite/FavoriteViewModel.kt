package com.example.coursestest.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursestest.data.models.Course
import com.example.coursestest.domain.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CourseRepository
): ViewModel() {
    val coursesFavorite = repository.getFavoriteCourses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun setFavoriteCourse(course: Course) {
        viewModelScope.launch {
            repository.upsertCourse(course.copy(hasLike = !course.hasLike))
        }
    }
}