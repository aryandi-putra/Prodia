package com.aryandi.prodia.di

import com.aryandi.prodia.BuildConfig
import com.aryandi.prodia.data.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {
    private fun getNewsBaseUrl() =
        buildString {
            append(BuildConfig.SPACEFLIGHT_NEWS_BASE_URL).append("/")
            append(BuildConfig.SPACEFLIGHT_NEWS_API_VERSION).append("/")
        }

    @Singleton
    @Provides
    fun provideNewsService(): NewsService =
        Retrofit.Builder()
            .baseUrl(getNewsBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
            .create(NewsService::class.java)

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY,
        )

    private fun provideOkHttp(): OkHttpClient {
        val okHttpClient =
            OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(provideLoggingInterceptor())
        }

        return okHttpClient.build()
    }
}