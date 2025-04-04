package com.example.coursestest.data.remote

import com.example.coursestest.data.models.Course
import retrofit2.Response
import retrofit2.http.GET

interface CourseApi {
    @GET("courses")
    suspend fun getCourses(): Response<CoursesResponse>
}