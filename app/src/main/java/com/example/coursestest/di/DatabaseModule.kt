package com.example.coursestest.di

import android.app.Application
import androidx.room.Room
import com.example.coursestest.data.local.CourseDao
import com.example.coursestest.data.local.CourseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): CourseDatabase {
        return Room
            .databaseBuilder(
                application,
                CourseDatabase::class.java,
                "courses.db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCourseDao(database: CourseDatabase): CourseDao {
        return database.courseDao()
    }

}