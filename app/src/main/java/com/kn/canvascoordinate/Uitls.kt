package com.kn.canvascoordinate

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Uitls {
    const val ACTION_KEY = "action"
    const val ROW_COLOR_KEY = "colorRow"
    const val ROW_COLOR_TAG = "colorRowTag"
    const val LINE_COLOR_KEY = "colorLine"
    const val LINE_COLOR_TAG = "colorLineTag"

    fun hideKeyboard(activity: Activity) {
        val v: View? = activity.window.currentFocus
        if (v != null) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}