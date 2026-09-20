package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.model.ArticleVote

class SecondActivity : LifecycleLoggingActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var vote = ArticleVote.NONE
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isRead = savedInstanceState?.getBoolean(STATE_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        vote = ArticleVote.fromName(
            savedInstanceState?.getString(STATE_VOTE) ?: intent.getStringExtra(EXTRA_VOTE)
        )
        binding.readSwitch.isChecked = isRead
        binding.articleImage.load(R.drawable.tennis_tiebreak)
        updateVoteButtons()
        publishResult()

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.likeButton.setOnClickListener { changeVote(ArticleVote.LIKE) }
        binding.dislikeButton.setOnClickListener { changeVote(ArticleVote.DISLIKE) }
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            isRead = checked
            publishResult()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_VOTE, vote.name)
        outState.putBoolean(STATE_IS_READ, isRead)
        super.onSaveInstanceState(outState)
    }

    private fun changeVote(selection: ArticleVote) {
        vote = vote.toggled(selection)
        updateVoteButtons()
        publishResult()
    }

    private fun updateVoteButtons() {
        val neutralColor = getColor(android.R.color.darker_gray)
        binding.likeButton.imageTintList = ColorStateList.valueOf(
            if (vote == ArticleVote.LIKE) getColor(R.color.reaction_like) else neutralColor
        )
        binding.dislikeButton.imageTintList = ColorStateList.valueOf(
            if (vote == ArticleVote.DISLIKE) getColor(R.color.reaction_dislike) else neutralColor
        )
        binding.likeButton.isSelected = vote == ArticleVote.LIKE
        binding.dislikeButton.isSelected = vote == ArticleVote.DISLIKE
        binding.likeCount.text = getString(R.string.reaction_count, if (vote == ArticleVote.LIKE) 1 else 0)
        binding.dislikeCount.text = getString(
            R.string.reaction_count,
            if (vote == ArticleVote.DISLIKE) 1 else 0
        )
    }

    private fun publishResult() {
        setResult(
            RESULT_OK,
            Intent()
                .putExtra(EXTRA_IS_READ, isRead)
                .putExtra(EXTRA_VOTE, vote.name)
        )
    }

    companion object {
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.extra.IS_READ"
        const val EXTRA_VOTE = "ru.urfu.droidpractice1.extra.SECOND_VOTE"
        private const val STATE_IS_READ = "is_read"
        private const val STATE_VOTE = "article_vote"

        fun createIntent(context: Context, isRead: Boolean, vote: ArticleVote): Intent =
            Intent(context, SecondActivity::class.java)
                .putExtra(EXTRA_IS_READ, isRead)
                .putExtra(EXTRA_VOTE, vote.name)
    }
}
