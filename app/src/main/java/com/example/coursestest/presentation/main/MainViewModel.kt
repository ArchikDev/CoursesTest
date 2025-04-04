package com.example.coursestest.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursestest.data.models.Course
import com.example.coursestest.data.remote.ApiResponse
import com.example.coursestest.data.remote.CourseApi
import com.example.coursestest.data.remote.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val courseApi: CourseApi,
): ViewModel() {
    private val _uiState = MutableStateFlow<MainEvent>(MainEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    init {
        getCourses()
    }


    private fun getCourses() {
        viewModelScope.launch {
            _uiState.value = MainEvent.Loading

            try {
                val response = safeApiCall {
                    courseApi.getCourses()
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


    sealed class MainEvent {
        data object Nothing: MainEvent()
        data class Success(val courses: List<Course>): MainEvent()
        data object Error: MainEvent()
        data object Loading: MainEvent()
    }
}