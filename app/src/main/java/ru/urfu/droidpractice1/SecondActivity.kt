package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LIFECYCLE_TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isRead = savedInstanceState?.getBoolean(STATE_IS_READ)
            ?: intent.getBooleanExtra(EXTRA_IS_READ, false)
        binding.readSwitch.isChecked = isRead
        binding.articleImage.load(R.drawable.tennis_tiebreak)
        publishResult()

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.readSwitch.setOnCheckedChangeListener { _, checked ->
            isRead = checked
            publishResult()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_IS_READ, isRead)
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

    private fun publishResult() {
        setResult(RESULT_OK, Intent().putExtra(EXTRA_IS_READ, isRead))
    }

    companion object {
        private const val LIFECYCLE_TAG = "SecondActivityLifecycle"
        const val EXTRA_IS_READ = "ru.urfu.droidpractice1.extra.IS_READ"
        private const val STATE_IS_READ = "is_read"

        fun createIntent(context: Context, isRead: Boolean): Intent =
            Intent(context, SecondActivity::class.java).putExtra(EXTRA_IS_READ, isRead)
    }
}
