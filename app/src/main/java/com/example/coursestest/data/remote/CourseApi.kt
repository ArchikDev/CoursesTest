package com.example.coursestest.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface CourseApi {
    @GET("courses")
    suspend fun getCourses(): Response<CoursesResponse>
}