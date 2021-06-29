package com.example.testexample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import dp
import getBitmap

class TouchView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_DOWN ->{
                val actionIndeX = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndeX)
                downX = event.getX(actionIndeX)
                downY = event.getY(actionIndeX)
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndeX = event.actionIndex
                val pointerId = event.getPointerId(actionIndeX)
                if(pointerId == trackingPointerId){
                    val newIndex = if(actionIndeX == event.pointerCount - 1){
                        event.pointerCount - 2
                    }else{
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    originOffsetX = offsetX
                    originOffsetY = offsetY
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackingPointerId)
                offsetX = event.getX(index) - downX + originOffsetX
                offsetY = event.getY(index) - downY + originOffsetY
                invalidate()
            }
        }
        return true
    }
}