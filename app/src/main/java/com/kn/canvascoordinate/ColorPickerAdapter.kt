package com.kn.canvascoordinate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_color_picker.view.*

class ColorPickerAdapter(
    private val colorPickerList: ArrayList<ColorPicker>,
    private var listener: OnItemClick,
    private var rowIndex: Int
) : RecyclerView.Adapter<ColorPickerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_color_picker, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return colorPickerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = colorPickerList[position]
        holder.color.setCardBackgroundColor(android.graphics.Color.parseColor(currentItem.colorName))
        if (position == rowIndex) {
            holder.okCheck.setBackgroundResource(R.drawable.ic_check)
        } else holder.okCheck.setBackgroundResource(0)
        holder.itemView.setOnClickListener {
            rowIndex = position
            listener.onItemChoose(currentItem.colorName)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val color: CardView = itemView.cardView
        val okCheck: ImageView = itemView.imgChoose
    }

    interface OnItemClick {
        fun onItemChoose(colorName: String)
    }
}