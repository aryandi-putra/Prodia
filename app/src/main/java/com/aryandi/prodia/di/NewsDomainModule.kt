package com.aryandi.prodia.di

import com.aryandi.prodia.domain.repository.NewsRepository
import com.aryandi.prodia.domain.usecase.FilterNewsUseCase
import com.aryandi.prodia.domain.usecase.FilterNewsUseCaseImpl
import com.aryandi.prodia.domain.usecase.GetNewsUseCase
import com.aryandi.prodia.domain.usecase.GetNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsDomainModule {

    @Provides
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase =
        GetNewsUseCaseImpl(newsRepository = newsRepository)

    @Provides
    fun provideFilterNewsUseCase(newsRepository: NewsRepository): FilterNewsUseCase =
        FilterNewsUseCaseImpl(newsRepository = newsRepository)
}