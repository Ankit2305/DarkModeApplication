package org.ankitkumar.darkmodeapplication

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.hypot

class CircularRevealImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var revealRadius = 0f
    private var centerX = 0f
    private var centerY = 0f
    private var revealAnimator: ValueAnimator? = null

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        val saved = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)

        val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawCircle(centerX, centerY, revealRadius, maskPaint)

        canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        super.onDraw(canvas)

        canvas.restoreToCount(saved)
    }

    fun startRevealAnimation(centerPoint: Point?, block: () -> Unit) {
        centerX = centerPoint?.x?.toFloat() ?: (width / 2f)
        centerY = centerPoint?.y?.toFloat() ?: (height / 2f)
        val maxRadius = hypot(width.toDouble(), height.toDouble()).toFloat()

        revealAnimator = ValueAnimator.ofFloat(0f, maxRadius).apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                revealRadius = it.animatedValue as Float
                invalidate()
            }
            start()

            addListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) = Unit

                override fun onAnimationEnd(animation: Animator) {
                    block.invoke()
                }

                override fun onAnimationCancel(animation: Animator) {
                    block.invoke()
                }

                override fun onAnimationRepeat(animation: Animator) = Unit

            })
        }
    }
}
