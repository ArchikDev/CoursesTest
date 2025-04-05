package com.example.coursestest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursestest.data.models.Course
import com.example.coursestest.data.remote.ApiResponse
import com.example.coursestest.data.remote.safeApiCall
import com.example.coursestest.domain.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CourseRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<MainEvent>(MainEvent.Nothing)
    val uiState = combine(
        _uiState,
        repository.getCoursesBD(),
    ) { state, coursesBd  ->
        if (state is MainEvent.Success) {
            var coursesCurrent = state.courses.map { course ->
                coursesBd.find { courseBd ->
                    courseBd.id == course.id
                } ?: course
            }

            if (coursesBd.isEmpty()) {
                repository.insertAll(coursesCurrent)
            }

            if (coursesCurrent.isEmpty() && coursesBd.isNotEmpty()) {
                coursesCurrent = coursesBd
            }

            MainEvent.Success(coursesCurrent)
        } else state
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = MainEvent.Nothing
    )

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            _uiState.value = MainEvent.Loading

            try {

                val response = safeApiCall {
                    repository.getCourses()
                }

                when (response) {
                    is ApiResponse.Success -> {
                        _uiState.value = MainEvent.Success(response.data.courses)
                    }
                    else -> {
                        _uiState.value = MainEvent.Error
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MainEvent.Error
            }
        }
    }

    fun setFavoriteCourse(course: Course) {
        viewModelScope.launch {
            repository.upsertCourse(course.copy(hasLike = !course.hasLike))
        }
    }


    sealed class MainEvent {
        data object Nothing: MainEvent()
        data class Success(val courses: List<Course>): MainEvent()
        data object Error: MainEvent()
        data object Loading: MainEvent()
    }
}