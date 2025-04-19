package org.ankitkumar.darkmodeapplication

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TransitionActivity : AppCompatActivity() {

    private lateinit var screenShotImageView: CircularRevealImageView
    private var isDarkMode = false
    private var centerPoint: Point? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0,0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        screenShotImageView = findViewById(R.id.screenshot_image_view)
        isDarkMode = intent.getBooleanExtra(EXTRA_IS_DARK_MODE, false)
        centerPoint = intent.getParcelableExtra(EXTRA_CENTER_POINT) as? Point
        setUpImageView()
    }

    private fun setUpImageView() {
        if (TransitionUtil.transitionBitmap == null) {
            TransitionUtil.updateTheme(isDarkMode)
            finish()
            return
        }

        screenShotImageView.setImageBitmap(TransitionUtil.transitionBitmap)
        screenShotImageView.post {
            TransitionUtil.updateTheme(isDarkMode)
            screenShotImageView.startRevealAnimation(centerPoint) {
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TransitionUtil.transitionBitmap = null
    }

    companion object {
        const val EXTRA_IS_DARK_MODE = "isDarkMode"
        const val EXTRA_CENTER_POINT = "center_point"
        fun getIntent(context: Context, isDarkMode: Boolean, centerPoint: Point): Intent {
            return Intent(context, TransitionActivity::class.java).apply {
                putExtra(EXTRA_IS_DARK_MODE, isDarkMode)
                putExtra(EXTRA_CENTER_POINT, centerPoint)
            }
        }
    }
}