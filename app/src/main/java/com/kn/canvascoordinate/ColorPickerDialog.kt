package com.kn.canvascoordinate

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kn.canvascoordinate.Uitls.ACTION_KEY
import com.kn.canvascoordinate.Uitls.LINE_COLOR_KEY
import com.kn.canvascoordinate.Uitls.LINE_COLOR_TAG
import com.kn.canvascoordinate.Uitls.ROW_COLOR_KEY
import com.kn.canvascoordinate.Uitls.ROW_COLOR_TAG
import kotlinx.android.synthetic.main.fragment_color_picker.*

class ColorPickerDialog : DialogFragment(), ColorPickerAdapter.OnItemClick {
    private val colorPickerList: ArrayList<ColorPicker> = ArrayList()
    private var colorRow = ""
    private var colorLine = ""
    private var onColorRowSelected: OnColorRowSelected? = null
    private var onColorLineSelected: OnColorLineSelected? = null
    private var action = ""
    private var choosingRowColor = ""
    private var choosingLineColor = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dummyColor()
        action = arguments?.getString(ACTION_KEY).toString()
        colorRow = arguments?.getString(ROW_COLOR_KEY).toString()
        colorLine = arguments?.getString(LINE_COLOR_KEY).toString()

        if (action == ROW_COLOR_TAG) {
            var indexRow = -1
            for (i in 0 until colorPickerList.size) {
                if (colorPickerList[i].colorName == colorRow) {
                    indexRow = i
                }
            }
            recycleView.adapter = ColorPickerAdapter(colorPickerList, this, indexRow)
        }
        if (action == LINE_COLOR_TAG) {
            var index = -1
            for (i in 0 until colorPickerList.size) {
                if (colorPickerList[i].colorName == colorLine) {
                    index = i
                }
            }
            recycleView.adapter = ColorPickerAdapter(colorPickerList, this, index)
        }

        recycleView.layoutManager = GridLayoutManager(this.activity, 3)
        recycleView.setHasFixedSize(true)
        recycleView.setRecyclerListener {
            if (action == ROW_COLOR_TAG) {
                val colorString = choosingRowColor
                onColorRowSelected?.sendRowColor(colorString)
            }
            if (action == LINE_COLOR_TAG) {
                val colorString = choosingLineColor
                onColorLineSelected?.sendLineColor(colorString)
            }
        }
        btnCancel.setOnClickListener {
            if (action == ROW_COLOR_TAG) {
                onColorRowSelected?.sendRowColor(colorRow)
            }
            if (action == LINE_COLOR_TAG) {
                onColorRowSelected?.sendRowColor(colorLine)
            }
            dismiss()
        }
        btnOk.setOnClickListener {
            if (action == ROW_COLOR_TAG) {
                val colorString = choosingRowColor
                if (colorString.isEmpty()) {
                    onColorRowSelected?.sendRowColor(colorRow)
                } else onColorRowSelected?.sendRowColor(colorString)
            }
            if (action == LINE_COLOR_TAG) {
                val colorString = choosingLineColor
                if (colorString.isEmpty()) {
                    onColorLineSelected?.sendLineColor(colorLine)
                } else onColorLineSelected?.sendLineColor(colorString)
            }
            dismiss()
        }
    }

    private fun dummyColor(): List<ColorPicker> {
        colorPickerList.add(ColorPicker("#FFFF0000"))
        colorPickerList.add(ColorPicker("#38FF3E"))
        colorPickerList.add(ColorPicker("#00FFEA"))
        colorPickerList.add(ColorPicker("#757575"))
        colorPickerList.add(ColorPicker("#FF5C29"))
        colorPickerList.add(ColorPicker("#FFE500"))
        colorPickerList.add(ColorPicker("#ED47FF"))
        colorPickerList.add(ColorPicker("#4DD2FF"))
        colorPickerList.add(ColorPicker("#DFBF9F"))
        return colorPickerList
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        if (action == ROW_COLOR_TAG) {
            val selectedColor = arguments?.getString(ROW_COLOR_KEY)
            if (selectedColor != null) {
                onColorRowSelected?.sendRowColor(selectedColor)
            }
        }
        if (action == LINE_COLOR_TAG) {
            val selectedColor = arguments?.getString(LINE_COLOR_KEY)
            if (selectedColor != null) {
                onColorLineSelected?.sendLineColor(selectedColor)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onColorRowSelected = context as OnColorRowSelected
            onColorLineSelected = context as OnColorLineSelected
        } catch (e: ClassCastException) {
        }
    }

    override fun onItemChoose(colorName: String) {
        if (action == ROW_COLOR_TAG) {
            choosingRowColor = colorName
        }
        if (action == LINE_COLOR_TAG) {
            choosingLineColor = colorName
        }
    }

    interface OnColorRowSelected {
        fun sendRowColor(color: String)
    }

    interface OnColorLineSelected {
        fun sendLineColor(color: String)
    }
}