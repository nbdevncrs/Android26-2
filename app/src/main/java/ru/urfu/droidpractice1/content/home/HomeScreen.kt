package ru.urfu.droidpractice1.content.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun HomeScreen(
    isFirstArticleRead: Boolean,
    isSecondArticleRead: Boolean,
    onOpenFirstArticle: () -> Unit,
    onOpenSecondArticle: () -> Unit
) {
    DroidPractice1Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                HomeHeader()
                Text(
                    text = stringResource(R.string.home_articles_label),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
                ArticlePreviewCard(
                    label = R.string.home_first_article_label,
                    title = R.string.first_article_name,
                    summary = R.string.home_first_summary,
                    image = R.drawable.tennis_match,
                    status = stringResource(
                        if (isFirstArticleRead) R.string.related_article_read
                        else R.string.related_article_not_read
                    ),
                    onClick = onOpenFirstArticle
                )
                ArticlePreviewCard(
                    label = R.string.home_second_article_label,
                    title = R.string.second_article_name,
                    summary = R.string.home_second_summary,
                    image = R.drawable.tennis_tiebreak,
                    status = stringResource(
                        if (isSecondArticleRead) R.string.related_article_read
                        else R.string.related_article_not_read
                    ),
                    onClick = onOpenSecondArticle
                )
            }
        }
    }
}

@Composable
private fun HomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF174934))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0x44D9F261),
                radius = 105.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(size.width * 0.94f, size.height * 0.15f)
            )
        }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.home_eyebrow),
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFD9F261)
            )
            Text(
                text = stringResource(R.string.home_headline),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.home_intro),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFE3F0E6)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        isFirstArticleRead = false,
        isSecondArticleRead = false,
        onOpenFirstArticle = {},
        onOpenSecondArticle = {}
    )
}
