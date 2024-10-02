package com.aryandi.prodia.presentation.main

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aryandi.prodia.R
import com.aryandi.prodia.domain.model.Article
import com.aryandi.prodia.ui.theme.ProdiaTheme

@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel(), onArticleClick: (url: String) -> Unit) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        NewsScreen(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = innerPadding.calculateTopPadding()),
            uiState = uiState.value,
            onRefresh = { viewModel.getNews(isRefresh = true) },
            onTryAgainClicked = viewModel::getNews,
            onArticleClick = onArticleClick,
        )
    }
}

@Composable
fun NewsScreen(
    modifier: Modifier,
    uiState: NewsUiState,
    onRefresh: () -> Unit,
    onArticleClick: (url: String) -> Unit,
    onTryAgainClicked: () -> Unit,
) {
    when {
        uiState.isLoading -> {
            CircularProgressIndicator(
                modifier
                    .fillMaxSize()
                    .wrapContentSize(),
            )
        }

        uiState.articles.isNotEmpty() -> {
            ArticleList(
                modifier = modifier,
                articles = uiState.articles,
                isRefreshing = uiState.isRefresh,
                onArticleClick = onArticleClick,
                onRefresh = onRefresh,
            )
        }

        uiState.error -> {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleList(
    modifier: Modifier,
    articles: List<Article>,
    isRefreshing: Boolean,
    onArticleClick: (url: String) -> Unit,
    onRefresh: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(
                text = "Artikel",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "See More",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
        PullToRefreshBox(
            modifier = modifier,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            LazyRow (contentPadding = PaddingValues(16.dp)) {
                items(articles) { article ->
                    ArticleSmall(
                        articleInfo = "${article.newsSite} • ${article.publishDateOffset}",
                        title = article.title,
                        articleImageUrl = article.imageUrl,
                        articleUrl = article.url,
                        onArticleClick = onArticleClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleSmall(
    title: String,
    articleInfo: String,
    articleImageUrl: String,
    articleUrl: String,
    onArticleClick: (url: String) -> Unit,
) {
    val placeholderDrawable =
        AppCompatResources.getDrawable(
            LocalContext.current,
            R.drawable.image_placeholder,
        )
    placeholderDrawable?.setTint(MaterialTheme.colorScheme.inverseOnSurface.toArgb())

    Column(
        modifier = Modifier
            .clickable { onArticleClick.invoke(articleUrl) }
            .fillMaxWidth()
            .padding(end = 16.dp),
    ) {
        AsyncImage(
            model =
            ImageRequest.Builder(LocalContext.current)
                .data(articleImageUrl)
                .placeholder(placeholderDrawable)
                .error(placeholderDrawable)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(100.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() {
    ProdiaTheme {
        NewsScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = NewsUiState(articles = Article.Mocks.articlesMocks),
            onRefresh = {},
            onArticleClick = {},
            onTryAgainClicked = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleSmallPreview() {
    ProdiaTheme {
        ArticleSmall(
            articleInfo = "SpaceNews • 2 days ago",
            title = "Roscosmos to launch uncrewed Soyuz to replace damaged spacecraft at ISS",
            articleImageUrl = "",
            articleUrl = "",
            onArticleClick = {},
        )
    }
}