package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

internal enum class ArticleVote { NONE, LIKE, DISLIKE }

private val likeColor = Color(0xFF2E7D32)
private val dislikeColor = Color(0xFFC62828)

@Composable
internal fun ArticleActions(
    vote: ArticleVote,
    onVoteChange: (ArticleVote) -> Unit,
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
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row {
                IconToggleButton(
                    checked = vote == ArticleVote.LIKE,
                    onCheckedChange = { checked ->
                        onVoteChange(if (checked) ArticleVote.LIKE else ArticleVote.NONE)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.thumb_up),
                        contentDescription = stringResource(R.string.like_article),
                        tint = if (vote == ArticleVote.LIKE) likeColor
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
                    onCheckedChange = { checked ->
                        onVoteChange(if (checked) ArticleVote.DISLIKE else ArticleVote.NONE)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.thumb_down),
                        contentDescription = stringResource(R.string.dislike_article),
                        tint = if (vote == ArticleVote.DISLIKE) dislikeColor
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = stringResource(R.string.reaction_count, if (vote == ArticleVote.DISLIKE) 1 else 0),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}
