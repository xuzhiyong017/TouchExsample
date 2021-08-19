package com.example.testexample

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
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

    val bounds = RectF()
    val path = Path()
    val wide = 17f.px.toInt()

    init {
        paint.strokeWidth = 1f.px
        paint.style = Paint.Style.FILL

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(wide * 3,wide * 3)
        bounds.set(0f,wide / 2f,wide * 2f,wide * 2f + wide / 2f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.arcTo(bounds, 60.0f, 240.0f)
        path.lineTo(width - wide / 3f,height / 2f)
//        path.quadTo(wide * 3f / 2,
//            (width / 2 - ((sqrt(3.0) / 2.0 )* wide)).toFloat(),
//            width - wide / 3f,height / 2f);
        path.close()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path,paint)
//        canvas.drawCircle(wide * 3f / 2,(width / 2 - ((sqrt(3.0) / 2.0 )* wide)).toFloat(),5f,Paint().apply {
//            setColor(Color.RED)
//        })

//        canvas.drawCircle(width - wide / 3f,height / 2f,1f,paint)
//        canvas.drawCircle(bounds.centerX(),bounds.centerY(),1f,paint)
//        canvas.drawRect(bounds,Paint().apply {
//            color = Color.BLUE
//            style = Paint.Style.STROKE
//        })
    }
}