package com.example.coursestest.domain.repository

import com.example.coursestest.data.models.Course
import com.example.coursestest.data.remote.CoursesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CourseRepository {

    suspend fun getCourses(): Response<CoursesResponse>

    suspend fun upsertCourse(course: Course)

    suspend fun insertAll(courses: List<Course>)

//    suspend fun clearAll()

    fun getFavoriteCourses(): Flow<List<Course>>

    fun getCoursesBD(): Flow<List<Course>>

}