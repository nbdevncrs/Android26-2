package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(onShareArticle = ::shareArticle)
        }
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
}
