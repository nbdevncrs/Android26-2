package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.ui.article.ArticleScreen
import ru.urfu.droidpractice1.model.ArticleVote

class MainActivity : LifecycleLoggingActivity() {
    private var vote by mutableStateOf(ArticleVote.NONE)
    private var secondVote by mutableStateOf(ArticleVote.NONE)
    private var isFirstArticleRead by mutableStateOf(false)
    private var isSecondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondVote = ArticleVote.fromName(
                result.data?.getStringExtra(SecondActivity.EXTRA_VOTE)
            )
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                isSecondArticleRead
            ) ?: isSecondArticleRead
            publishResult()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vote = ArticleVote.fromName(
            savedInstanceState?.getString(STATE_VOTE) ?: intent.getStringExtra(EXTRA_VOTE)
        )
        secondVote = ArticleVote.fromName(
            savedInstanceState?.getString(STATE_SECOND_VOTE)
                ?: intent.getStringExtra(SecondActivity.EXTRA_VOTE)
        )
        isFirstArticleRead = savedInstanceState?.getBoolean(STATE_FIRST_ARTICLE_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        isSecondArticleRead = savedInstanceState?.getBoolean(STATE_SECOND_ARTICLE_READ)
            ?: intent.getBooleanExtra(SecondActivity.EXTRA_IS_READ, false)
        publishResult()
        setContent {
            ArticleScreen(
                vote = vote,
                onVoteChange = {
                    vote = it
                    publishResult()
                },
                isFirstArticleRead = isFirstArticleRead,
                onFirstArticleReadChange = {
                    isFirstArticleRead = it
                    publishResult()
                },
                isSecondArticleRead = isSecondArticleRead,
                onNavigateBack = { onBackPressedDispatcher.onBackPressed() },
                onShareArticle = ::shareArticle,
                onOpenSecondArticle = {
                    secondArticleLauncher.launch(
                        SecondActivity.createIntent(this, isSecondArticleRead, secondVote)
                    )
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_VOTE, vote.name)
        outState.putString(STATE_SECOND_VOTE, secondVote.name)
        outState.putBoolean(STATE_FIRST_ARTICLE_READ, isFirstArticleRead)
        outState.putBoolean(STATE_SECOND_ARTICLE_READ, isSecondArticleRead)
        super.onSaveInstanceState(outState)
    }

    private fun shareArticle() {
        val articleText = listOf(
            R.string.first_article_headline,
            R.string.first_article_intro,
            R.string.first_article_section_game,
            R.string.first_article_game_text,
            R.string.first_article_section_set,
            R.string.first_article_set_text,
            R.string.first_article_section_match,
            R.string.first_article_match_text,
            R.string.first_article_tiebreak_note
        ).joinToString("\n\n") { getString(it) }

        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, articleText)
        }
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser_title)))
    }

    private fun publishResult() {
        setResult(
            RESULT_OK,
            Intent()
                .putExtra(EXTRA_VOTE, vote.name)
                .putExtra(EXTRA_IS_READ, isFirstArticleRead)
                .putExtra(SecondActivity.EXTRA_VOTE, secondVote.name)
                .putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
        )
    }

    companion object {
        const val EXTRA_VOTE = "ru.urfu.droidpractice1.extra.VOTE"
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.extra.FIRST_IS_READ"
        private const val STATE_VOTE = "article_vote"
        private const val STATE_SECOND_VOTE = "second_article_vote"
        private const val STATE_FIRST_ARTICLE_READ = "first_article_read"
        const val STATE_SECOND_ARTICLE_READ = "second_article_read"

        fun createIntent(
            context: Context,
            vote: ArticleVote,
            isFirstArticleRead: Boolean,
            secondVote: ArticleVote,
            isSecondArticleRead: Boolean
        ): Intent =
            Intent(context, MainActivity::class.java)
                .putExtra(EXTRA_VOTE, vote.name)
                .putExtra(EXTRA_IS_READ, isFirstArticleRead)
                .putExtra(SecondActivity.EXTRA_VOTE, secondVote.name)
                .putExtra(SecondActivity.EXTRA_IS_READ, isSecondArticleRead)
    }
}
