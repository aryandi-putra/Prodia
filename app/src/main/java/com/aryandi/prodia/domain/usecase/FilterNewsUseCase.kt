package com.aryandi.prodia.domain.usecase

import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

interface FilterNewsUseCase {
    operator fun invoke(filterQuery: String): Flow<List<Article>>
}

class FilterNewsUseCaseImpl(private val newsRepository: NewsRepository) : FilterNewsUseCase {
    override fun invoke(filterQuery: String): Flow<List<Article>> =
        newsRepository.filterNews(filterQuery)
}