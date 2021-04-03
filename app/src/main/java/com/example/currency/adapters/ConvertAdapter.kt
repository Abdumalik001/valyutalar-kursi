package com.example.currency.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.valyutalarkursi.model.ValyutaDataItem
import java.util.*

class ConvertAdapter : RecyclerView.Adapter<ConvertAdapter.VH>() {
    var listData = ArrayList<ValyutaDataItem>()
        set(value) {
            field.addAll(value)
            notifyItemInserted(field.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindData(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    class VH(item: View) : RecyclerView.ViewHolder(item) {
        @SuppressLint("SetTextI18n")
        fun onBindData(data: ValyutaDataItem) {
            val state: TextView = itemView.findViewById(R.id.txt_state)
            val valyuta: TextView = itemView.findViewById(R.id.txt_valyuta)
            val diff: TextView = itemView.findViewById(R.id.txt_diff)
            val imageView: ImageView = itemView.findViewById(R.id.img_diff)
            state.text = data.Date
            valyuta.text = data.Rate + " so'm"
            when {
                data.Diff.startsWith("-") -> {
                    diff.text = data.Diff
                    diff.setTextColor(Color.RED)
                    imageView.setImageResource(R.drawable.ic_down_24)
                }
                data.Diff == "0" -> {
                    diff.text = ""
                    imageView.setImageDrawable(null)
                }
                else -> {
                    diff.text = "+" + data.Diff
                    diff.setTextColor(Color.GREEN)
                    imageView.setImageResource(R.drawable.ic_up)
                }

            }

        }
    }

}