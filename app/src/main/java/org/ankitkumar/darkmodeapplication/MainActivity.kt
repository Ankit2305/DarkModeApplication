package org.ankitkumar.darkmodeapplication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import org.ankitkumar.darkmodeapplication.TransitionUtil.center
import org.ankitkumar.darkmodeapplication.TransitionUtil.takeScreenshot
import org.ankitkumar.darkmodeapplication.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, MainFragment(), MainFragment.TAG)
                .commit()
        }
    }
}