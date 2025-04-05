package com.example.coursestest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.coursestest.data.models.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Upsert
    suspend fun upsertCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<Course>)

//    @Query("delete from course")
//    suspend fun clearAll()

    @Query("select * from course WHERE hasLike = 1")
    fun getFavoriteCourses(): Flow<List<Course>>

    @Query("select * from course")
    fun getCourses(): Flow<List<Course>>
}