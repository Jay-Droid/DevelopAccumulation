package com.jay.develop.android.webview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.github.lzyzsd.jsbridge.BridgeWebView
import android.graphics.PorterDuff




class CornerBridgeWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BridgeWebView(context, attrs, defStyleAttr) {

    private var paint1: Paint? = null

    private var paint2: Paint? = null

    private var m_radius = 0f

    private var webWidth = 100

    private var webHeight = 100

    private var x: Int = 0

    private var y: Int = 0

    init {
        init(context)
    }

    private fun init(context: Context) {
        m_radius = 30f
        paint1 = Paint()
        paint1!!.color = Color.WHITE
        paint1!!.isAntiAlias = true
        paint1!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        paint2 = Paint()
        paint2!!.color = Color.WHITE
        paint2!!.isAntiAlias = true
        paint2!!.xfermode = null
    }


    fun setRadius(w: Int, h: Int, radius: Float) {
        setRadius(radius)
        webWidth = w
        webHeight = h
    }

    fun setRadius(radius: Float) {
        m_radius = radius
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //判断 避免 width，height值为0,否则Bitmap.createBitmap()报错
        if (measuredWidth != 0) {
            webWidth = measuredWidth
        }
        if (measuredHeight != 0) {
            webHeight = measuredHeight
        }
    }


    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        x = this.scrollX
        y = this.scrollY
        val bitmap = Bitmap.createBitmap(
            x + width, y + height,
            Bitmap.Config.RGB_565
        )
        val canvas2 = Canvas(bitmap)
        super.draw(canvas2)
        drawLeftUp(canvas2)
        drawRightUp(canvas2)
        canvas.drawBitmap(bitmap, 0f, 0f, paint2)
        bitmap.recycle()
    }


    private fun drawLeftUp(canvas: Canvas) {
        val path = Path()
        path.moveTo(x.toFloat(), m_radius)
        path.lineTo(x.toFloat(), y.toFloat())
        path.lineTo(m_radius, y.toFloat())
        path.arcTo(
            RectF(x.toFloat(), y.toFloat(), x + m_radius * 2, y + m_radius * 2), -90f,
            -90f
        )
        path.close()
        canvas.drawPath(path, paint1!!)
    }


    private fun drawRightUp(canvas: Canvas) {
        val path = Path()
        path.moveTo((x + width).toFloat(), y + m_radius)
        path.lineTo((x + width).toFloat(), y.toFloat())
        path.lineTo(x + width - m_radius, y.toFloat())
        path.arcTo(RectF(x + width - m_radius * 2, y.toFloat(), (x + width).toFloat(), y + m_radius * 2), -90f, 90f)
        path.close()
        canvas.drawPath(path, paint1!!)
    }

}