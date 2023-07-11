package com.example.network.di

import android.content.Context
import androidx.room.Room
import com.example.network.MoviesDatabase
import com.example.network.MoviesDbApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor { chain ->
                val request = chain.request()

                val newUrl = request.newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NzNiZjg1MGRlZTZjMWI1MWI4MTVlN2FkOTYwZGQ2MyIsInN1YiI6IjY0OWQ5ZmI4NWFiYTMyMDBhYzE4YjBmMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ytCO16Uqaqm_pRGi23UELGoUlmZvCWPoHMD7_55WTgQ")
                    .build()

                chain.proceed(newUrl)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesService(
        moshi: Moshi,
        client: OkHttpClient
    ): MoviesDbApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(MoviesDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesDB(
        @ApplicationContext context: Context
    ) : MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java, "Movies-DB"
        ).fallbackToDestructiveMigration().build()
    }
}