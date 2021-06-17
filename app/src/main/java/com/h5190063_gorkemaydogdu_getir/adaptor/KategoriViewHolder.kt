package com.h5190063_gorkemaydogdu_getir.adaptor

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.h5190063_gorkemaydogdu_getir.R

class KategoriViewHolder(itemView: View,listener : KategoriAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    var kategoriAdi: TextView
    var kategoriResim: ImageView

    init {
        kategoriAdi = itemView.findViewById(R.id.textViewKategoriAdi)
        kategoriResim = itemView.findViewById(R.id.imageViewKategori)

        itemView.setOnClickListener { listener.onClick(adapterPosition) }
    }
}
