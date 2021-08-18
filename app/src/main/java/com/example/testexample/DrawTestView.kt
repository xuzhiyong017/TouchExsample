package com.example.testexample

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import px
import kotlin.math.sqrt

/**
 * @author: xuzhiyong
 * @date: 2021/8/18  下午3:14
 * @Email: 18971269648@163.com
 * @description:
 */
class DrawTestView (context: Context, attrs: AttributeSet?) : View(context,attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 80f.px
        textAlign = Paint.Align.CENTER
    }
    val RADIUS = 100f.px
    val OPEN_ANGLE = 120f
    val DASH_WIDTH = 2f.px
    val DASH_LENGTH = 10f.px
    val dash = Path()
    lateinit var pathEffect: PathDashPathEffect
    val bounds: RectF = RectF()
    val fontMetrics = Paint.FontMetrics()
    init {
        paint.strokeWidth = 3f.px
        paint.style = Paint.Style.STROKE
        pathEffect = PathDashPathEffect(dash,50f,0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(0f,0f,RADIUS,RADIUS)
        dash.reset()
        dash.arcTo(bounds, 60.0f, 240.0f)
        dash.quadTo(RADIUS /2,
            (RADIUS /2 - (sqrt(3.0) / 2.0 * (RADIUS / 3))).toFloat(),
            RADIUS - RADIUS /3/3,RADIUS / 2);
        dash.close()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(dash,paint)
    }
}