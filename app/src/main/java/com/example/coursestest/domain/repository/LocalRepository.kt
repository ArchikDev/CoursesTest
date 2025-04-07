package com.example.coursestest.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}