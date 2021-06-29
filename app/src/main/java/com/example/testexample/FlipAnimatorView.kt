package com.example.testexample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import dp
import getBitmap

class FlipAnimatorView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val BITMAP_SIZE = 200.dp
    val BITMAP_PADDING = 100.dp
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmap = getBitmap(resources,BITMAP_SIZE)
    val camera = Camera()

    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {

    }

    override fun onDraw(canvas: Canvas) {

        canvas.withSave {
            canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2f),(BITMAP_PADDING + BITMAP_SIZE / 2f))
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-BITMAP_SIZE,-BITMAP_SIZE,
                BITMAP_SIZE,0f)
            canvas.rotate(flipRotation)
            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2f),-(BITMAP_PADDING + BITMAP_SIZE / 2f))
            canvas.drawBitmap(bitmap,BITMAP_PADDING,BITMAP_PADDING,paint)

        }

        canvas.withSave {
            canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2f),(BITMAP_PADDING + BITMAP_SIZE / 2f))
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-BITMAP_SIZE,0f,
                BITMAP_SIZE,BITMAP_SIZE)
            canvas.rotate(flipRotation)
            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2f),-(BITMAP_PADDING + BITMAP_SIZE / 2f))
            canvas.drawBitmap(bitmap,BITMAP_PADDING,BITMAP_PADDING,paint)
        }
    }

}