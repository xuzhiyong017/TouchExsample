package com.example.testexample

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import dp
import getBitmap
import kotlin.math.max
import kotlin.math.min

val IMAGE_SIZE = 300.dp
private const val EXTRA_SCALE_FRACTION = 1.5f
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private val bitmap = getBitmap(resources,IMAGE_SIZE)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var offsetX = 0f
    private var offsetY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var big = false
    private val henScaleGestureListener = HenScaleGestureListener()
    private val gestureDetector = GestureDetectorCompat(context,this)
    private val scaleGestureDetector = ScaleGestureDetector(context,henScaleGestureListener)
    private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this,"scaleFraction",0f,1f)
    }

    private val scroller = OverScroller(context)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
        return scaleGestureDetector.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (w - IMAGE_SIZE) / 2f
        originalOffsetY = (h - IMAGE_SIZE) / 2f
        offsetX = 0f
        offsetY = 0f

        if(bitmap.width  / bitmap.height.toFloat() > width / height.toFloat()){
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FRACTION
        }else{
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FRACTION
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate(offsetX * scaleFraction,offsetY  * scaleFraction)
        var scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(scale,scale,width /2f,height /2f)
        canvas.drawBitmap(bitmap,originalOffsetX ,originalOffsetY,paint)
    }

    override fun onDown(e: MotionEvent?): Boolean {
       return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return true
    }

    override fun onScroll(
        downEvent: MotionEvent?,
        currentEvent: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if(big){
            offsetX -= distanceX;
            offsetY -= distanceY
            fixOffset()
            invalidate()
        }
        return false
    }

    private fun fixOffset() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if(big){
            scroller.fling(offsetX.toInt(),offsetY.toInt(),velocityX.toInt(),velocityY.toInt(),
                (-(bitmap.width * bigScale - width) / 2).toInt()
            ,((bitmap.width * bigScale - width) / 2).toInt()
            ,(-(bitmap.height * bigScale - height) / 2) .toInt()
            ,((bitmap.height * bigScale - height) / 2).toInt(),
            80.dp.toInt(),80.dp.toInt())

        }
       return false
    }

    override fun computeScroll() {
        if(scroller.computeScrollOffset()){
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
        }
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        big = !big
        if(big){
            offsetX = (e.x - width / 2) * ( 1 - bigScale / smallScale)
            offsetY = (e.y - height / 2) * ( 1 - bigScale / smallScale)
            fixOffset()
            scaleAnimator.start()
        }else{
            scaleAnimator.reverse()
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    inner class HenScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener{

        override fun onScale(detector: ScaleGestureDetector): Boolean {
           return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
           return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {

        }
    }
}