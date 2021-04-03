package com.example.currency.adapters

import OnItemClickListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.valyutalarkursi.model.ValyutaDataItem

class ValyutaAdapter( context: Context) : RecyclerView.Adapter<ValyutaAdapter.VH>() {

    var listener: OnItemClickListener? = null
    var data = ArrayList<ValyutaDataItem>()

        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        if (context is OnItemClickListener) {
            listener = context
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindData(data[position])
        holder.itemView.setOnClickListener { listener?.onItemCLick(data[position]) }
    }

    override fun getItemCount(): Int = data.size

    class VH(item: View) : RecyclerView.ViewHolder(item) {
        @SuppressLint("SetTextI18n")
        fun onBindData(data: ValyutaDataItem) {
            val state: TextView = itemView.findViewById(R.id.txt_state)
            val valyuta: TextView = itemView.findViewById(R.id.txt_valyuta)
            val diff: TextView = itemView.findViewById(R.id.txt_diff)
            val imageView: ImageView = itemView.findViewById(R.id.img_diff)
            state.text = data.CcyNm_UZ
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