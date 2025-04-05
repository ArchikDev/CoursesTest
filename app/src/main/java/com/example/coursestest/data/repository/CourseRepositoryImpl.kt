package com.example.coursestest.data.repository

import com.example.coursestest.data.local.CourseDao
import com.example.coursestest.data.models.Course
import com.example.coursestest.data.remote.CourseApi
import com.example.coursestest.data.remote.CoursesResponse
import com.example.coursestest.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CourseRepositoryImpl(
    private val courseApi: CourseApi,
    private val courseDao: CourseDao
): CourseRepository {
    override suspend fun getCourses(): Response<CoursesResponse> {
        return courseApi.getCourses()
    }

    override suspend fun upsertCourse(course: Course) {
        courseDao.upsertCourse(course)
    }

    override suspend fun insertAll(courses: List<Course>) {
        courseDao.insertAll(courses)
    }

//    override suspend fun clearAll() {
//        courseDao.clearAll()
//    }

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return courseDao.getFavoriteCourses()
    }

    override fun getCoursesBD(): Flow<List<Course>> {
        return courseDao.getCourses()
    }
}