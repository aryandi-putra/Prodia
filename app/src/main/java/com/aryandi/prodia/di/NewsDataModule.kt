package com.aryandi.prodia.di

import com.aryandi.prodia.data.api.NewsService
import com.aryandi.prodia.data.api.mapper.ArticleMapper
import com.aryandi.prodia.data.api.mapper.ArticleMapperImpl
import com.aryandi.prodia.data.repository.NewsRepositoryImpl
import com.aryandi.prodia.domain.repository.NewsRepository
import com.aryandi.prodia.util.DateParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    fun provideNewsRepository(
        newsService: NewsService,
        articleMapper: ArticleMapper,
    ): NewsRepository = NewsRepositoryImpl(newsService = newsService, articleMapper = articleMapper)

    @Provides
    fun providesArticleMapper(dateParser: DateParser): ArticleMapper =
        ArticleMapperImpl(dateParser = dateParser)
}