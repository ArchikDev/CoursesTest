package com.example.coursestest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindProjectRepository(
//        impl: ProjectRepositoryImpl
//    ): ProjectRepository
//
//    @Singleton
//    @Binds
//    abstract fun bindTaskRepository(
//        impl: TaskRepositoryImpl
//    ): TaskRepository
//
//}