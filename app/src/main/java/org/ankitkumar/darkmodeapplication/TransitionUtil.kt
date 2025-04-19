package org.ankitkumar.darkmodeapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

object TransitionUtil {
    var transitionBitmap: Bitmap? = null

    fun switchToTheme(context: Context, isDarkMode: Boolean, centerPoint: Point) {
        context.startActivity(TransitionActivity.getIntent(context, isDarkMode, centerPoint))
    }

    fun updateTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun View.takeScreenshot(): Bitmap {
        val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        this.draw(canvas)
        return bitmap
    }
    
    fun View.center(): Point {
        return Point((this.x + this.width / 2).toInt(), (this.y + this.height / 2).toInt())
    }
    
}