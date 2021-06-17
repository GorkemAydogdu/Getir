package com.h5190063_gorkemaydogdu_getir.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.h5190063_gorkemaydogdu_getir.R
import com.h5190063_gorkemaydogdu_getir.adaptor.UrunAdapter
import com.h5190063_gorkemaydogdu_getir.model.Urunler


class UrunlerActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var urunler: ArrayList<Urunler>
    lateinit var ref : DatabaseReference
    lateinit var query: Query
    lateinit var urun : Intent
    lateinit var urunDetay: Intent
    var kategoriAdi: String? = null
    lateinit var layoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urunler)
        init()
    }
    fun init(){
        recyclerView = findViewById(R.id.rvUrunler)
        layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager

        ref = FirebaseDatabase.getInstance().reference.child(resources.getString(R.string.tblUrun))
        urun = intent
        kategoriAdi = urun.getStringExtra(resources.getString(R.string.kategori))
        query = FirebaseDatabase.getInstance().reference.child(resources.getString(R.string.tblUrun)).orderByChild(resources.getString(R.string.sutunAdı)).equalTo(kategoriAdi)

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    urunler = ArrayList()
                    for (ds in snapshot.children) {
                        urunler.add(ds.getValue(Urunler::class.java)!!)
                    }
                    val urunAdapter = UrunAdapter(urunler, applicationContext, object : UrunAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                            val tiklananUrun: Urunler = urunler[position]
                            detayEkrani()
                            urunDetay.putExtra(resources.getString(R.string.urunDetayResim), tiklananUrun.urunResim)
                            urunDetay.putExtra(resources.getString(R.string.urunDetayAdi), tiklananUrun.urunAdi)
                            urunDetay.putExtra(resources.getString(R.string.urunDetayFiyat), tiklananUrun.urunFiyat)
                            urunDetay.putExtra(resources.getString(R.string.urunDetayHakkinda), tiklananUrun.urunHakkinda)
                            urunDetay.putExtra(resources.getString(R.string.urunDetayPorsiyon), tiklananUrun.urunPorsiyon)
                            startActivity(urunDetay)
                        }
                    })
                    recyclerView.adapter = urunAdapter
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        query.addListenerForSingleValueEvent(valueEventListener)
    }
    fun detayEkrani(){
        urunDetay = Intent(this, UrunDetayActivity::class.java)
    }


}