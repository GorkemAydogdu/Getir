package com.h5190063_gorkemaydogdu_getir.activity

import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.h5190063_gorkemaydogdu_getir.R

import com.h5190063_gorkemaydogdu_getir.adaptor.KategoriAdapter
import com.h5190063_gorkemaydogdu_getir.model.Kategoriler
import kotlin.system.exitProcess

class KategoriActivity : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var kategoriler: ArrayList<Kategoriler>
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var tiklanan : Intent
    var tiklananKategori: Kategoriler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)
        init()
    }
    fun init(){
        ref = FirebaseDatabase.getInstance().reference.child(applicationContext.getString(R.string.tblKategori))
        recyclerView = findViewById(R.id.rv_kategori)
        searchView = findViewById(R.id.searchviewKategori)
        val layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager

        kategori()
    }
    fun kategori(){
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        kategoriler = ArrayList()
                        for (ds in snapshot.children) {
                            kategoriler!!.add(ds.getValue(Kategoriler::class.java)!!)
                        }
                        val kategoriAdapter = KategoriAdapter(kategoriler, applicationContext, object : KategoriAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                                tiklananKategori = kategoriler[position]
                                ikinciEkraniGetir()
                                tiklanan.putExtra(resources.getString(R.string.kategori), tiklananKategori!!.kategoriAdi)
                                startActivity(tiklanan)
                            }
                        })
                        recyclerView!!.adapter = kategoriAdapter
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@KategoriActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    kategoriAra(newText)
                    return true
                }
            })
        }
    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.uyarı))
        builder.setMessage(resources.getString(R.string.alertCikis))
        builder.setPositiveButton(resources.getString(R.string.Evet)){ dialogInterface: DialogInterface, i: Int->
            finish()
        }
        builder.setNegativeButton(resources.getString(R.string.Hayir)){ dialogInterface: DialogInterface, i: Int->
            dialogInterface.cancel()}
        builder.show()
    }
    private fun ikinciEkraniGetir() {
        tiklanan = Intent(this, UrunlerActivity::class.java)
        //startActivity(tiklanan)
    }
    private fun kategoriAra(str: String) {
        if (str != "") {
            val searchKategori: ArrayList<Kategoriler> = ArrayList<Kategoriler>()
            for (kategori in kategoriler!!) {
                if (kategori!!.kategoriAdi!!.toLowerCase().contains(str.toLowerCase())) {
                    searchKategori.add(kategori)
                }
            }
            val kategoriAdapter = KategoriAdapter(searchKategori, applicationContext, object : KategoriAdapter.OnItemClickListener {
                override fun onClick(position: Int) {
                    tiklananKategori = searchKategori[position]
                    ikinciEkraniGetir()
                    tiklanan.putExtra(resources.getString(R.string.kategori), tiklananKategori!!.kategoriAdi)
                    startActivity(tiklanan)
                }
            })
            recyclerView!!.adapter = kategoriAdapter
        }
    }
}