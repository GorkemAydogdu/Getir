package com.h5190063_gorkemaydogdu_getir.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.h5190063_gorkemaydogdu_getir.R
import com.h5190063_gorkemaydogdu_getir.model.Kategoriler
import kotlin.collections.ArrayList

class KategoriAdapter(list: ArrayList<Kategoriler>, context: Context, listener: OnItemClickListener) : RecyclerView.Adapter<KategoriViewHolder>() {
    var list: ArrayList<Kategoriler>
    var context: Context
    var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_kategori, parent, false)
        return KategoriViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        holder.kategoriAdi.text = list[position].kategoriAdi
        Glide.with(holder.kategoriResim).load(list[position].kategoriResim).into(holder.kategoriResim)
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
