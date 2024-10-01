package com.aryandi.prodia.domain.model

data class Article(
    val id: Int,
    val featured: Boolean,
    val publishedAt: String?,
    val imageUrl: String,
    val newsSite: String,
    val title: String,
    val url: String,
    val launches: List<Launch>,
    val updatedAt: String,
    val publishDateOffset: String?,
)