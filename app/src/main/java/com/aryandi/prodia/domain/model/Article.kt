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
) {

    object Mocks {
        val articlesMocks = listOf(
            Article(
                id = 1,
                featured = true,
                publishedAt = "2024-10-01",
                imageUrl = "https://example.com/image1.jpg",
                newsSite = "Space News",
                title = "Elon Musk launch new satellite",
                url = "https://example.com/article1",
                launches = emptyList(),
                updatedAt = "2024-10-01",
                publishDateOffset = "5h ago",
            ),
            Article(
                id = 2,
                featured = false,
                publishedAt = "2024-10-01",
                imageUrl = "https://example.com/image2.jpg",
                newsSite = "Automotive",
                title = "Tesla release new model",
                url = "https://example.com/article2",
                launches = emptyList(),
                updatedAt = "2024-10-01",
                publishDateOffset = "1d ago",
            ),
            Article(
                id = 3,
                featured = true,
                publishedAt = "2024-10-02",
                imageUrl = "https://example.com/image3.jpg",
                newsSite = "Tech News",
                title = "Why Jetpack Compose is the Future of UI Development",
                url = "https://example.com/article3",
                launches = emptyList(),
                updatedAt = "2024-10-02",
                publishDateOffset = "2d ago",
            ),
            Article(
                id = 4,
                featured = false,
                publishedAt = "2024-10-03",
                imageUrl = "https://example.com/image4.jpg",
                newsSite = "Politic News",
                title = "Fufufafa's account got hacked",
                url = "https://example.com/article4",
                launches = emptyList(),
                updatedAt = "2024-10-03",
                publishDateOffset = "3d ago",
            ),
            Article(
                id = 5,
                featured = true,
                publishedAt = "2024-10-04",
                imageUrl = "https://example.com/image5.jpg",
                newsSite = "Kotlin News",
                title = "Migrating from Java to Kotlin: Best Practices",
                url = "https://example.com/article5",
                launches = emptyList(),
                updatedAt = "2024-10-04",
                publishDateOffset = "4d ago",
            ),
        )
    }
}