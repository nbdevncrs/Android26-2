@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.model.ArticleVote
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    vote: ArticleVote,
    onVoteChange: (ArticleVote) -> Unit,
    isFirstArticleRead: Boolean,
    onFirstArticleReadChange: (Boolean) -> Unit,
    isSecondArticleRead: Boolean,
    onNavigateBack: () -> Unit,
    onShareArticle: () -> Unit,
    onOpenSecondArticle: () -> Unit
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.article_title),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = stringResource(R.string.navigate_back)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ArticleContent()
                ArticleActions(
                    vote = vote,
                    onVoteChange = onVoteChange,
                    isRead = isFirstArticleRead,
                    onReadChange = onFirstArticleReadChange,
                    onShareArticle = onShareArticle
                )
                RelatedArticleLink(
                    isRead = isSecondArticleRead,
                    onOpen = onOpenSecondArticle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainActivityScreen(
        vote = ArticleVote.NONE,
        onVoteChange = {},
        isFirstArticleRead = false,
        onFirstArticleReadChange = {},
        isSecondArticleRead = false,
        onNavigateBack = {},
        onShareArticle = {},
        onOpenSecondArticle = {}
    )
}
