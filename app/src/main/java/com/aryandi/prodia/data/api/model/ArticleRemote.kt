package com.aryandi.prodia.data.api.model

import com.google.gson.annotations.SerializedName

data class ArticleRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("featured") val featured: Boolean,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("news_site") val newsSite: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("launches") val launches: List<LaunchRemote>,
    @SerializedName("updated_at") val updatedAt: String,
)