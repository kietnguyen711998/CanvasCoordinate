package com.kn.canvascoordinate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kn.canvascoordinate.Uitls.ACTION_KEY
import com.kn.canvascoordinate.Uitls.LINE_COLOR_KEY
import com.kn.canvascoordinate.Uitls.LINE_COLOR_TAG
import com.kn.canvascoordinate.Uitls.ROW_COLOR_KEY
import com.kn.canvascoordinate.Uitls.ROW_COLOR_TAG
import com.kn.canvascoordinate.Uitls.hideKeyboard
import kotlinx.android.synthetic.main.activity_canvas_coordinate.*

class CanvasCoordinateActivity : AppCompatActivity(), ColorPickerDialog.OnColorRowSelected,
    ColorPickerDialog.OnColorLineSelected {
    private var rowColor = ""
    private var lineColor = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_coordinate)

        rowColor = "#" + Integer.toHexString(vectorView.getColorRow())
        lineColor = "#" + Integer.toHexString(vectorView.getColorLine())
        vectorView.getColorLine()
        vectorView.getColorRow()
        Log.d("TAG", "onCreate: "+ vectorView.getColorLine())

        btnChooseRowColor.setOnClickListener {
            openChooseColorRowDialog()
        }
        btnChooseLineColor.setOnClickListener {
            openChooseColorLineDialog()
        }
        btnEnter.setOnClickListener {
            hideKeyboard(this)
            if (edtX.text.isNotEmpty() && edtY.text.isNotEmpty()) {
                val inputA = edtX.text.toString().toFloat()
                val inputB = edtY.text.toString().toFloat()
                if (inputA <= 1000 && inputB <= 1000 && inputA >= -1000 && inputB >= -1000) {
                    vectorView.drawVector(inputA, inputB)
                } else Toast.makeText(this, R.string.check_value_infor, Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, R.string.check_value_infor, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openChooseColorRowDialog() {
        val dialogChooseColor = ColorPickerDialog()
        val bundle = Bundle()
        bundle.putString(ACTION_KEY, ROW_COLOR_TAG)
        bundle.putString(ROW_COLOR_KEY, rowColor)
        dialogChooseColor.arguments = bundle
        dialogChooseColor.show(supportFragmentManager, ROW_COLOR_TAG)
    }

    private fun openChooseColorLineDialog() {
        val dialogChooseColor = ColorPickerDialog()
        val bundle = Bundle()
        bundle.putString(ACTION_KEY, LINE_COLOR_TAG)
        bundle.putString(LINE_COLOR_KEY, lineColor)
        dialogChooseColor.arguments = bundle
        dialogChooseColor.show(supportFragmentManager, LINE_COLOR_TAG)
    }

    override fun sendRowColor(color: String) {
        rowColor = color
        vectorView.setColorRow(rowColor)
    }

    override fun sendLineColor(color: String) {
        lineColor = color
        vectorView.setColorLine(lineColor)

    }


}