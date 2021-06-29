package com.example.testexample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import dp
import getBitmap

class MutiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmap = getBitmap(resources,200.dp)

    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f;
    private var downY = 0f;
    private var originOffsetX = 0f;
    private var originOffsetY = 0f;
    private var trackingPointerId = 0

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint)
    }

    //协作行手指虚拟中心点
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var focusX = 0f
        var focusY = 0f
        var sumX = 0f
        var sumY = 0f
        var pointCount = event.pointerCount
        val isPointUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for(i in 0 until event.pointerCount){
            if(!(i == event.actionIndex && isPointUp)){
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        if(isPointUp){
            pointCount--
        }
        focusX = sumX / pointCount
        focusY = sumY / pointCount
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN,MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackingPointerId)
                offsetX = focusX - downX + originOffsetX
                offsetY = focusY - downY + originOffsetY
                invalidate()
            }
        }
        return true
    }
}