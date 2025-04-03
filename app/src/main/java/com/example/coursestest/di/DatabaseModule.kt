package com.example.coursestest.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        application: Application
//    ): AppDatabase {
//        return Room
//            .databaseBuilder(
//                application,
//                AppDatabase::class.java,
//                "worktime.db"
//            )
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideProjectDao(database: AppDatabase): ProjectDao {
//        return database.projectDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideTaskDao(database: AppDatabase): TaskDao {
//        return database.taskDao()
//    }
//}