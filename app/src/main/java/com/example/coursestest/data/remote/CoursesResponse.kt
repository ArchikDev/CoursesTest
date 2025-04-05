package com.example.coursestest.data.remote

import com.example.coursestest.data.models.Course
import kotlinx.coroutines.flow.Flow

data class CoursesResponse(
    val courses: List<Course>
)
