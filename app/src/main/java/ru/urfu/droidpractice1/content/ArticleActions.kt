package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

@Composable
internal fun ArticleActions(
    likes: Int,
    dislikes: Int,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    onShareArticle: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        FilledTonalButton(
            onClick = onShareArticle,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.share_article))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = onLike, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.likes_count, likes))
            }
            OutlinedButton(onClick = onDislike, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.dislikes_count, dislikes))
            }
        }
    }
}
