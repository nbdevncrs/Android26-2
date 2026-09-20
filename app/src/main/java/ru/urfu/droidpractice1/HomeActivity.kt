package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.ui.home.HomeScreen
import ru.urfu.droidpractice1.model.ArticleVote

class HomeActivity : LifecycleLoggingActivity() {
    private var firstVote by mutableStateOf(ArticleVote.NONE)
    private var secondVote by mutableStateOf(ArticleVote.NONE)
    private var isFirstArticleRead by mutableStateOf(false)
    private var isSecondArticleRead by mutableStateOf(false)

    private val firstArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            firstVote = ArticleVote.fromName(result.data?.getStringExtra(MainActivity.EXTRA_VOTE))
            secondVote = ArticleVote.fromName(result.data?.getStringExtra(SecondActivity.EXTRA_VOTE))
            isFirstArticleRead = result.data?.getBooleanExtra(
                MainActivity.EXTRA_IS_READ,
                isFirstArticleRead
            ) ?: isFirstArticleRead
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                isSecondArticleRead
            ) ?: isSecondArticleRead
        }
    }

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            secondVote = ArticleVote.fromName(result.data?.getStringExtra(SecondActivity.EXTRA_VOTE))
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                isSecondArticleRead
            ) ?: isSecondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstVote = ArticleVote.fromName(savedInstanceState?.getString(STATE_FIRST_VOTE))
        secondVote = ArticleVote.fromName(savedInstanceState?.getString(STATE_SECOND_VOTE))
        isFirstArticleRead = savedInstanceState?.getBoolean(STATE_FIRST_ARTICLE_READ) ?: false
        isSecondArticleRead = savedInstanceState?.getBoolean(STATE_SECOND_ARTICLE_READ) ?: false

        setContent {
            HomeScreen(
                isFirstArticleRead = isFirstArticleRead,
                isSecondArticleRead = isSecondArticleRead,
                onOpenFirstArticle = {
                    firstArticleLauncher.launch(
                        MainActivity.createIntent(
                            this,
                            firstVote,
                            isFirstArticleRead,
                            secondVote,
                            isSecondArticleRead
                        )
                    )
                },
                onOpenSecondArticle = {
                    secondArticleLauncher.launch(
                        SecondActivity.createIntent(this, isSecondArticleRead, secondVote)
                    )
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_FIRST_VOTE, firstVote.name)
        outState.putString(STATE_SECOND_VOTE, secondVote.name)
        outState.putBoolean(STATE_FIRST_ARTICLE_READ, isFirstArticleRead)
        outState.putBoolean(STATE_SECOND_ARTICLE_READ, isSecondArticleRead)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val STATE_FIRST_VOTE = "first_article_vote"
        const val STATE_SECOND_VOTE = "second_article_vote"
        const val STATE_FIRST_ARTICLE_READ = "first_article_read"
        const val STATE_SECOND_ARTICLE_READ = "second_article_read"
    }
}
