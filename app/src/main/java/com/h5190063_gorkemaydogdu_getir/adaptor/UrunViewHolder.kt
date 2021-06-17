package com.h5190063_gorkemaydogdu_getir.adaptor

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.h5190063_gorkemaydogdu_getir.R

class UrunViewHolder(itemView: View, listener: UrunAdapter.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    var urunAdi: TextView
    var urunResim: ImageView
    var urunFiyat : TextView
    var urunPorsiyon : TextView

    init {
        urunAdi = itemView.findViewById(R.id.textViewUrunAdi)
        urunResim = itemView.findViewById(R.id.imageViewUrun)
        urunFiyat = itemView.findViewById(R.id.textViewFiyat)
        urunPorsiyon = itemView.findViewById(R.id.textViewPorsiyon)

        itemView.setOnClickListener { listener.onClick(adapterPosition) }
    }
}
