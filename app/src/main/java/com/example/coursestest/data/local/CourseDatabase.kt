package com.example.coursestest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coursestest.data.models.Course

@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}