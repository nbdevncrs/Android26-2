package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

@Composable
internal fun RelatedArticleLink(isRead: Boolean, onOpen: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.related_article_label),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        FilledTonalButton(onClick = onOpen, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.open_second_article))
        }
        Text(
            text = stringResource(
                if (isRead) R.string.related_article_read else R.string.related_article_not_read
            ),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
