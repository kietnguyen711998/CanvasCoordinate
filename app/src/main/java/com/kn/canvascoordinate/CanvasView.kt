package com.kn.canvascoordinate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {
    private var rowColor = 0
    private var lineColor = 0
    private var textPointPaint = Paint()
    private var textPaint = Paint()
    private var rowPointPaint = Paint()
    private var rowPaint = Paint()
    private var vectorPaint = Paint()
    private var pointPaint = Paint()
    private var isClickButton: Boolean = false
    private var maxX = 110
    private var maxY = 110
    private var posX1 = 0f
    private var posX2 = 0f
    private var posY1 = 0f
    private var posY2 = 0f
    private var pointX1 = 0f
    private var pointX2 = 0f
    private var pointY1 = 0.0f
    private var pointY2 = 0.0f
    private var linePointX1 = 0f
    private var linePointX2 = 0f
    private var linePointY1 = 0f
    private var linePointY2 = 0f

    init {
        rowPointPaint.isAntiAlias = true
        rowPointPaint.color = Color.BLACK
        rowPointPaint.strokeWidth = 5F

        rowPaint.isAntiAlias = true
        rowPaint.color = Color.BLACK
        rowPaint.strokeWidth = 3F

        vectorPaint.isAntiAlias = true
        vectorPaint.color = Color.BLACK
        vectorPaint.strokeWidth = 3F

        pointPaint.isAntiAlias = true
        pointPaint.color = Color.RED
        pointPaint.strokeWidth = 10F

        textPaint.isAntiAlias = true
        textPaint.color = Color.BLACK
        textPaint.textSize = 20F

        textPointPaint.isAntiAlias = true
        textPointPaint.color = Color.RED
        textPointPaint.textSize = 30F

        attributeSet.let {
            val typesArray = context.obtainStyledAttributes(it, R.styleable.CanvasView, 0, 0)
            rowColor = typesArray.getColor(R.styleable.CanvasView_setRowColor, resources.getColor(R.color.color_choose))
            rowPaint.color = rowColor
            lineColor = typesArray.getColor(R.styleable.CanvasView_setLineColor, resources.getColor(R.color.color_choose))
            vectorPaint.color = lineColor
            typesArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(width / 2F, 0F, width / 2F, height.toFloat(), rowPaint)
        canvas.drawLine(0F, height / 2F, width.toFloat(), height / 2F, rowPaint)
        drawBorder(canvas)
        drawArrow(canvas)
        drawMark(canvas)
        if (isClickButton) {
            canvas.drawPoint(pointX1, pointY1, pointPaint)
            canvas.drawText("($posX1, $posY1)", pointX1 + 10, pointY1 + 35, textPointPaint)
            canvas.drawPoint(pointX2, pointY2, pointPaint)
            canvas.drawText("($posX2, $posY2)", pointX2 + 10, pointY2 + 35, textPointPaint)
            canvas.drawLine(linePointX1, linePointY1, linePointX2, linePointY2, vectorPaint)
        }
    }

    private fun drawBorder(canvas: Canvas) {
        val paint3 = Paint(Paint.ANTI_ALIAS_FLAG)
        paint3.color = Color.BLACK
        paint3.style = Paint.Style.STROKE
        paint3.strokeWidth = 3F
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paint3)
    }

    private fun drawArrow(canvas: Canvas) {
        canvas.drawLine(width / 2F, 0F, width / 2F - 10F, 10F, rowPaint)
        canvas.drawLine(width / 2F, 0F, width / 2F + 10F, 10F, rowPaint)
        canvas.drawLine(width.toFloat(), height / 2F, width - 10F, height / 2F - 10, rowPaint)
        canvas.drawLine(width.toFloat(), height / 2F, width - 10F, height / 2F + 10, rowPaint)
    }

    private fun drawMark(canvas: Canvas) {
        canvas.drawText("0", width / 2F - 30, height / 2F + 30, textPaint)
        canvas.drawText("X", width - 30F, height / 2F + 20, textPaint)
        canvas.drawText("Y", width / 2F + 20, 20F, textPaint)

        canvas.drawPoint(width / 2F, height.toFloat() / 22F, rowPointPaint)
        canvas.drawText((maxY - (maxY / 11)).toString(), width / 2F + 20, height / 22F, textPaint)
        canvas.drawPoint(width / 2F, (height / 22F) * 6, rowPointPaint)
        canvas.drawText(
            ((maxY - (maxY / 11)) / 2).toString(),
            width / 2F + 20,
            (height / 22F) * 6,
            textPaint
        )
        canvas.drawPoint(width / 2F, (height / 22F) * 16, rowPointPaint)
        canvas.drawText(
            ((-1 * maxY + (maxY / 11)) / 2).toString(),
            width / 2F + 20,
            (height / 22F) * 16,
            textPaint
        )
        canvas.drawPoint(width / 2F, (height / 22F) * 21, rowPointPaint)
        canvas.drawText(
            (-1 * maxY + (maxY / 11)).toString(),
            width / 2F + 20,
            (height / 22F) * 21,
            textPaint
        )
        canvas.drawPoint(width / 22F, height / 2F, rowPointPaint)
        canvas.drawText(
            (-1 * maxX + (maxX / 11)).toString(),
            width / 22F,
            height / 2F - 10,
            textPaint
        )
        canvas.drawPoint((width / 22F) * 6, height / 2F, rowPointPaint)
        canvas.drawText(
            ((-1 * maxX + (maxX / 11)) / 2).toString(),
            (width / 22F) * 6,
            height / 2F - 10,
            textPaint
        )
        canvas.drawPoint((width / 22F) * 16, height / 2F, rowPointPaint)
        canvas.drawText(
            ((maxX - (maxX / 11)) / 2).toString(),
            (width / 22F) * 16,
            height / 2F - 10,
            textPaint
        )
        canvas.drawPoint((width / 22F) * 21, height / 2F, rowPointPaint)
        canvas.drawText(
            (maxX - (maxX / 11)).toString(),
            (width / 22F) * 21,
            height / 2F - 10,
            textPaint
        )
    }

    fun drawVector(a: Float, b: Float) {
        if (a == 0f && b == 0f) {
            posX1 = 0f
            posY1 = 0f
            posX2 = 5f
            posY2 = 0f

            getMaxValueX(posX2)
            getMaxValueY(posY1)

            pointX1 = getPositionX(posX1)
            pointY1 = getPositionY(posY1)
            pointX2 = getPositionX(posX2)
            pointY2 = getPositionY(posY2)

            linePointX1 = getPositionX(-1100f)
            linePointY1 = getPositionY(0f)
            linePointX2 = getPositionX(1100F)
            linePointY2 = getPositionY(0f)
        }
        if (a == 0f && b != 0f) {
            posX1 = 0f
            posY1 = b
            posX2 = 5f
            posY2 = b

            getMaxValueX(posX2)
            getMaxValueY(posY1)

            pointX1 = getPositionX(posX1)
            pointY1 = getPositionY(posY1)
            pointX2 = getPositionX(posX2)
            pointY2 = getPositionY(posY2)

            linePointX1 = getPositionX(-1100f)
            linePointY1 = getPositionY(b)
            linePointX2 = getPositionX(1100F)
            linePointY2 = getPositionY(b)
        }
        if (a != 0f && b == 0f) {
            posX1 = 0f
            posY1 = 0f
            posX2 = 1f
            posY2 = a * posX2

            getMaxValueX(posX2)
            getMaxValueY(posY2)

            pointX1 = getPositionX(posX1)
            pointY1 = getPositionY(posY1)
            pointX2 = getPositionX(posX2)
            pointY2 = getPositionY(posY2)

            linePointX1 = getPositionX(-1100f)
            linePointY1 = getPositionY(a * -1100f)
            linePointX2 = getPositionX(1100f)
            linePointY2 = getPositionY(a * 1100f)
        }
        if (a != 0f && b != 0f) {
            posX1 = 0f
            posY1 = a * posX1 + b
            posX2 = -b / a
            posY2 = 0f

            getMaxValueX(posX2)
            getMaxValueY(posY1)

            pointX1 = getPositionX(posX1)
            pointY1 = getPositionY(posY1)
            pointX2 = getPositionX(posX2)
            pointY2 = getPositionY(posY2)

            linePointX1 = getPositionX(-1100f)
            linePointY1 = getPositionY(a * -1100f + b)
            linePointX2 = getPositionX(1100f)
            linePointY2 = getPositionY(a * 1100f + b)
        }

        isClickButton = true

        invalidate()
    }

    private fun getPositionX(i: Float): Float {
        return if (i == 0f) {
            width.toFloat() / 2f
        } else (width / 2) + (((width.toFloat() * 10 / 2) / maxX) * (i / 10))
    }

    private fun getPositionY(i: Float): Float {
        return if (i == 0f) {
            height / 2f
        } else (height / 2) - (((height.toFloat() * 10 / 2) / maxY) * (i / 10))
    }

    private fun getMaxValueX(i: Float) {
        if (i > -10 && i <= 10) {
            maxX = 11
        }
        if (i > 10 && i <= 100 || i < -10 && i >= -100) {
            maxX = 110
        }
        if (i > 100 || i < -100) {
            maxX = 1100
        }
    }

    private fun getMaxValueY(i: Float) {
        if (i > -10 && i <= 10) {
            maxY = 11
        }
        if (i > 10 && i <= 100 || i < -10 && i >= -100) {
            maxY = 110
        }
        if (i > 100 || i < -100) {
            maxY = 1100
        }
    }

    fun getColorRow(): Int {
        return rowColor
    }

    fun getColorLine(): Int {
        return lineColor
    }

    fun setColorRow(color: String) {
        rowColor = Color.parseColor(color)
        rowPaint.color = rowColor
        invalidate()
    }

    fun setColorLine(color: String) {
        lineColor = Color.parseColor(color)
        vectorPaint.color = lineColor
        invalidate()
    }
}