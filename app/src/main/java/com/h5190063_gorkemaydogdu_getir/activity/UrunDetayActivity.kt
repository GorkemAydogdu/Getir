package com.h5190063_gorkemaydogdu_getir.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.h5190063_gorkemaydogdu_getir.R

class UrunDetayActivity : AppCompatActivity() {
    lateinit var urunDetay : Intent
    var urunDetayAd : String? = null
    var urunDetayAciklama : String? = null
    var urunDetayFiyat : String? = null
    var urunDetayPorsiyon : String? = null
    var urunDetayResim : String? = null
    lateinit var txtUrunAdi : TextView
    lateinit var txtUrunAciklama : TextView
    lateinit var txtUrunFiyat : TextView
    lateinit var txtUrunPorsiyon : TextView
    lateinit var imgResim : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urun_detay)
        init()
    }
    fun init(){
        urunDetay = intent
        urunDetayAd = urunDetay.getStringExtra(resources.getString(R.string.urunDetayAdi))
        urunDetayAciklama = urunDetay.getStringExtra(resources.getString(R.string.urunDetayHakkinda))
        urunDetayFiyat = urunDetay.getStringExtra(resources.getString(R.string.urunDetayFiyat))
        urunDetayPorsiyon = urunDetay.getStringExtra(resources.getString(R.string.urunDetayPorsiyon))
        urunDetayResim = urunDetay.getStringExtra(resources.getString(R.string.urunDetayResim))

        txtUrunAdi = findViewById(R.id.textViewDetayIsım)
        txtUrunAciklama = findViewById(R.id.textViewDetayAciklama)
        txtUrunFiyat = findViewById(R.id.textViewDetayFiyat)
        txtUrunPorsiyon = findViewById(R.id.textViewDetayPorsiyon)
        imgResim = findViewById(R.id.imageViewUrunDetay)

        Glide.with(imgResim).load(urunDetayResim).into(imgResim)
        txtUrunAdi.text = urunDetayAd
        txtUrunAciklama.text = urunDetayAciklama
        txtUrunFiyat.text = urunDetayFiyat
        txtUrunPorsiyon.text = urunDetayPorsiyon
    }
}