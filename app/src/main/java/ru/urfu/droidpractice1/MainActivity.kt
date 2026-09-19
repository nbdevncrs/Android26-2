package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isSecondArticleRead by mutableStateOf(false)

    private val secondArticleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(
                SecondActivity.EXTRA_IS_READ,
                isSecondArticleRead
            ) ?: isSecondArticleRead
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LIFECYCLE_TAG, "onCreate")
        isSecondArticleRead = savedInstanceState?.getBoolean(STATE_SECOND_ARTICLE_READ) ?: false
        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                onShareArticle = ::shareArticle,
                onOpenSecondArticle = {
                    secondArticleLauncher.launch(
                        SecondActivity.createIntent(this, isSecondArticleRead)
                    )
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_SECOND_ARTICLE_READ, isSecondArticleRead)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LIFECYCLE_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFECYCLE_TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFECYCLE_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFECYCLE_TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LIFECYCLE_TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LIFECYCLE_TAG, "onDestroy")
    }

    private fun shareArticle() {
        val articleText = listOf(
            R.string.article_headline,
            R.string.article_intro,
            R.string.article_section_game,
            R.string.article_game_text,
            R.string.article_section_set,
            R.string.article_set_text,
            R.string.article_section_match,
            R.string.article_match_text,
            R.string.article_tiebreak_note
        ).joinToString("\n\n") { getString(it) }

        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, articleText)
        }
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser_title)))
    }

    private companion object {
        const val LIFECYCLE_TAG = "MainActivityLifecycle"
        const val STATE_SECOND_ARTICLE_READ = "second_article_read"
    }
}
