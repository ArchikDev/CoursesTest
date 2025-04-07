package com.example.coursestest.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursestest.data.repository.LocalRepositoryImpl
import com.example.coursestest.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: LocalRepository
): ViewModel() {

    fun saveAppEntry() {
        viewModelScope.launch {
            repository.saveAppEntry()
        }
    }

}