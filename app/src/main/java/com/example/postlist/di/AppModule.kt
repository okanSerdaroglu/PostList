package com.example.postlist.di

import com.example.postlist.BuildConfig
import com.example.postlist.data.remote.PostApi
import com.example.postlist.data.repository.PostListRepositoryImpl
import com.example.postlist.domain.repository.PostListRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePostApi(): PostApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMarketRepository(
        postApi: PostApi
    ): PostListRepository {
        return PostListRepositoryImpl(
            postApi = postApi,
        )
    }

}