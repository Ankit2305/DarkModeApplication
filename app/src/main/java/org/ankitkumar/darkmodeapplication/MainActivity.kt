package org.ankitkumar.darkmodeapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import org.ankitkumar.darkmodeapplication.TransitionUtil.center
import org.ankitkumar.darkmodeapplication.TransitionUtil.takeScreenshot

class MainActivity : AppCompatActivity() {

    private lateinit var themeIcon: ImageView
    private var isDarkMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        themeIcon = findViewById(R.id.theme_icon)

        isDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        updateThemeUI(isDarkMode)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        themeIcon.setOnClickListener {
            isDarkMode = !isDarkMode
            updateThemeUI(isDarkMode)
            val rootView = window.decorView.rootView
            TransitionUtil.transitionBitmap = rootView.takeScreenshot()

            TransitionUtil.switchToTheme(this, isDarkMode, themeIcon.center())
        }
    }

    private fun updateThemeUI(isDarkMode: Boolean) {
        themeIcon.setImageResource(
            if (isDarkMode) R.drawable.ic_dark_mode
            else R.drawable.ic_light_mode
        )
    }
}