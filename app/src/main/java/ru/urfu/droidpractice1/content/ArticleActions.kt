package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.model.ArticleVote

@Composable
internal fun ArticleActions(
    vote: ArticleVote,
    onVoteChange: (ArticleVote) -> Unit,
    isRead: Boolean,
    onReadChange: (Boolean) -> Unit,
    onShareArticle: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        FilledTonalButton(
            onClick = onShareArticle,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.share_article))
        }
        Text(
            text = stringResource(R.string.article_reactions_label),
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row {
                IconToggleButton(
                    checked = vote == ArticleVote.LIKE,
                    onCheckedChange = {
                        onVoteChange(vote.toggled(ArticleVote.LIKE))
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.thumb_up),
                        contentDescription = stringResource(R.string.like_article),
                        tint = if (vote == ArticleVote.LIKE) colorResource(R.color.reaction_like)
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = stringResource(R.string.reaction_count, if (vote == ArticleVote.LIKE) 1 else 0),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Row {
                IconToggleButton(
                    checked = vote == ArticleVote.DISLIKE,
                    onCheckedChange = {
                        onVoteChange(vote.toggled(ArticleVote.DISLIKE))
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.thumb_down),
                        contentDescription = stringResource(R.string.dislike_article),
                        tint = if (vote == ArticleVote.DISLIKE) colorResource(R.color.reaction_dislike)
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = stringResource(R.string.reaction_count, if (vote == ArticleVote.DISLIKE) 1 else 0),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.article_read_switch),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Switch(checked = isRead, onCheckedChange = onReadChange)
        }
    }
}
