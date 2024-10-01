package com.aryandi.prodia.data.repository

import com.aryandi.prodia.data.api.NewsService
import com.aryandi.prodia.data.api.mapper.ArticleMapper
import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl(
    private val newsService: NewsService,
    private val articleMapper: ArticleMapper,
) : NewsRepository {
    override fun getNews(): Flow<List<Article>> = flow {
        val result = newsService.getArticles().results.map(articleMapper::mapToDomain)
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun filterNews(filterQuery: String): Flow<List<Article>> = flow {
        val result = newsService.filterArticles(filterQuery).results.map(articleMapper::mapToDomain)
        emit(result)
    }.flowOn(Dispatchers.IO)
}