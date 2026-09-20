package ru.urfu.droidpractice1.ui.article

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R

@Composable
internal fun ArticleBody() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.first_article_name).uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = colorResource(R.color.article_accent)
        )
        Text(
            text = stringResource(R.string.first_article_headline),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.article_text_primary)
        )
        Text(
            text = stringResource(R.string.first_article_intro),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.article_text_secondary)
        )
        AsyncImage(
            model = R.drawable.tennis_match,
            contentDescription = stringResource(R.string.first_article_image_description),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.first_article_image_caption),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.article_text_secondary)
        )
        ArticleSection(R.string.first_article_section_game, R.string.first_article_game_text)
        ArticleSection(R.string.first_article_section_set, R.string.first_article_set_text)
        ArticleSection(R.string.first_article_section_match, R.string.first_article_match_text)
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.first_article_tiebreak_note),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.article_text_primary)
        )
    }
}

@Composable
private fun ArticleSection(@StringRes title: Int, @StringRes body: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.article_text_primary)
        )
        Text(
            text = stringResource(body),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.article_text_primary)
        )
    }
}
