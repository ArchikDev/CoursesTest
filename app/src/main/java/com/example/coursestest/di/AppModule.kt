package com.example.coursestest.di

import android.content.Context
import com.example.coursestest.data.local.CourseDatabase
import com.example.coursestest.data.remote.CourseApi
import com.example.coursestest.data.remote.LocalJsonInterceptor
import com.example.coursestest.data.repository.CourseRepositoryImpl
import com.example.coursestest.domain.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LocalJsonInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mock.api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(
        courseApi: CourseApi,
        database: CourseDatabase
    ): CourseRepository {
        return CourseRepositoryImpl(
            courseApi,
            database.courseDao()
        )
    }
}