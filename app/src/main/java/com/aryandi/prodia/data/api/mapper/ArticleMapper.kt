package com.aryandi.prodia.data.api.mapper

import com.aryandi.prodia.data.api.model.ArticleRemote
import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.domain.model.Launch
import com.aryandi.prodia.util.DateParser

interface ArticleMapper {
    fun mapToDomain(articleRemote: ArticleRemote): Article
}

class ArticleMapperImpl(
    private val dateParser: DateParser,
) : ArticleMapper {
    override fun mapToDomain(articleRemote: ArticleRemote) = Article(
        id = articleRemote.id,
        featured = articleRemote.featured,
        publishedAt = articleRemote.publishedAt,
        publishDateOffset = dateParser.formatToTimeAgo(articleRemote.publishedAt),
        imageUrl = articleRemote.imageUrl,
        newsSite = articleRemote.newsSite,
        title = articleRemote.title,
        url = articleRemote.url,
        launches = articleRemote.launches.map { Launch((it.launchId)) },
        updatedAt = articleRemote.updatedAt,
    )
}