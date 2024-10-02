package com.aryandi.prodia.data.api

import com.aryandi.prodia.data.api.model.ArticleRemote
import com.aryandi.prodia.data.api.model.PaginatedResultRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("articles")
    suspend fun getArticles(@Query("limit") resultLimit: String = "10"): PaginatedResultRemote<List<ArticleRemote>>

    @GET("articles")
    suspend fun filterArticles(
        @Query("title_contains") title: String,
        @Query("limit") resultLimit: String = "10",
    ): PaginatedResultRemote<List<ArticleRemote>>
}