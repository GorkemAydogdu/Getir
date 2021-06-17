package com.h5190063_gorkemaydogdu_getir.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.h5190063_gorkemaydogdu_getir.R
import com.h5190063_gorkemaydogdu_getir.model.Urunler

class UrunAdapter(list: ArrayList<Urunler>, context: Context, listener: OnItemClickListener) : RecyclerView.Adapter<UrunViewHolder>() {
    var list: ArrayList<Urunler>
    var context: Context
    var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrunViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_urunler, parent, false)
        return UrunViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: UrunViewHolder, position: Int) {
        holder.urunAdi.text = list[position].urunAdi
        holder.urunFiyat.text = list[position].urunFiyat
        holder.urunPorsiyon.text = list[position].urunPorsiyon
        Glide.with(holder.urunResim).load(list[position].urunResim).into(holder.urunResim)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    init {
        this.list = list
        this.context = context
        this.listener = listener
    }
}
