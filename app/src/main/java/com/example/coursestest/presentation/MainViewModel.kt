package com.example.coursestest.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursestest.domain.repository.LocalRepository
import com.example.coursestest.presentation.navigation.OnBoardRoute
import com.example.coursestest.presentation.navigation.SignInRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LocalRepository
): ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set


    var startDestination: Any by mutableStateOf(SignInRoute)
        private set

    init {
        repository.readAppEntry().onEach {
            startDestination = if (it) {
                SignInRoute
            } else {
                OnBoardRoute
            }

            delay(300)

            splashCondition = false
        }.launchIn(viewModelScope)
    }
}