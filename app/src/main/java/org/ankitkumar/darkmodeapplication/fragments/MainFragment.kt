package org.ankitkumar.darkmodeapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.ankitkumar.darkmodeapplication.R
import org.ankitkumar.darkmodeapplication.TransitionUtil
import org.ankitkumar.darkmodeapplication.TransitionUtil.center
import org.ankitkumar.darkmodeapplication.TransitionUtil.takeScreenshot

class MainFragment: Fragment(R.layout.fragment_main) {
    
    companion object {
        const val TAG = "MainFragment"
    }

    private lateinit var themeIcon: ImageView
    private var isDarkMode: Boolean = false
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        themeIcon = view.findViewById(R.id.theme_icon)

        isDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        updateThemeUI(isDarkMode)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i("DebugTag", "MainFragment onViewCreated called")
        themeIcon.setOnClickListener {
            isDarkMode = !isDarkMode
            updateThemeUI(isDarkMode)
            Log.i("DebugTag", "Theme changed to: $isDarkMode $view")
            TransitionUtil.transitionBitmap = view.takeScreenshot()

            TransitionUtil.switchToTheme(requireContext(), isDarkMode, themeIcon.center())
        }
    }


    private fun updateThemeUI(isDarkMode: Boolean) {
        themeIcon.setImageResource(
            if (isDarkMode) R.drawable.ic_dark_mode
            else R.drawable.ic_light_mode
        )
    }
}