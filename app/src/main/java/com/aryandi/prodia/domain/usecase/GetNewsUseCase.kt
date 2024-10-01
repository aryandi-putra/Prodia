package com.aryandi.prodia.domain.usecase

import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {
    operator fun invoke(): Flow<List<Article>>
}

class GetNewsUseCaseImpl(
    private val newsRepository: NewsRepository,
) : GetNewsUseCase {
    override fun invoke(): Flow<List<Article>> = newsRepository.getNews()
}