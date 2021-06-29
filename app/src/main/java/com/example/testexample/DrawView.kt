
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class DrawView(context:Context,attrs:AttributeSet?) : View(context,attrs) {

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
    val bounds: Rect = Rect()
    val fontMetrics = Paint.FontMetrics()
    init {
        paint.strokeWidth = 3f.px
        paint.style = Paint.Style.STROKE
        dash.addRect(0f,0f,DASH_WIDTH,DASH_LENGTH,Path.Direction.CCW)
        pathEffect = PathDashPathEffect(dash,50f,0f,PathDashPathEffect.Style.ROTATE)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width/2f,height /2f,10f,paint)
        canvas.drawArc(width / 2 - 150f.px,
            height / 2 - 150f.px,
            width/2 + 150f.px,
            height / 2 + 150f.px,
            90 + OPEN_ANGLE / 2,
            360 - OPEN_ANGLE,
            false,
            paint
        )
        paint.pathEffect = pathEffect
        canvas.drawArc(width / 2 - 150f.px,
            height / 2 - 150f.px,
            width/2 + 150f.px,
            height / 2 + 150f.px,
            90 + OPEN_ANGLE / 2,
            360 - OPEN_ANGLE,
            false,
            paint
        )

        paint.pathEffect = null
        paint.color = Color.BLUE

        paint.getTextBounds("居中",0,"居中".length,bounds)
        paint.getFontMetrics(fontMetrics)
        Log.d("testSize=","top=${fontMetrics.top}\n" +
                "ascent=${fontMetrics.ascent}\n" +
                "descent=${fontMetrics.descent}\n" +
                "leading=${fontMetrics.leading}\n" +
                "bottom=${fontMetrics.bottom}\n  ")


        canvas.drawText("居中",width/2f,height /2f - (bounds.top + bounds.bottom) / 2,paint)
    }
}