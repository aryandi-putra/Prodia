package com.aryandi.prodia.domain.repository

import com.aryandi.prodia.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<Article>>
    fun filterNews(filterQuery: String): Flow<List<Article>>
}