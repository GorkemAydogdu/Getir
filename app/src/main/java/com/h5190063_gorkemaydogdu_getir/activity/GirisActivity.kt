package com.h5190063_gorkemaydogdu_getir.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.h5190063_gorkemaydogdu_getir.R

class GirisActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth
    lateinit var uyari : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)
        init(applicationContext)
    }
    fun init(context: Context){
        firebaseAuth = FirebaseAuth.getInstance()
        val btnGirisYap = findViewById<Button>(R.id.buttonGiris)
        val kullaniciEposta = findViewById<EditText>(R.id.editTextGirisYapEposta)
        val kullaniciSifre = findViewById<EditText>(R.id.editTextGirisyapSifre)
        val btnUyeOl = findViewById<Button>(R.id.buttonUye)
        btnGirisYap.setOnClickListener{
            val sifre : String  = kullaniciSifre.text.toString()
            val eposta : String  = kullaniciEposta.text.toString()
            if(TextUtils.isEmpty(eposta) || TextUtils.isEmpty(sifre)) {
                Toast.makeText(applicationContext,resources.getString(R.string.EpostaSifre),Toast.LENGTH_SHORT).show()
            }
            else if(TextUtils.isEmpty(eposta) && TextUtils.isEmpty(sifre)){
                Toast.makeText(applicationContext,resources.getString(R.string.EpostaSifre),Toast.LENGTH_SHORT).show()
            }
            else{
                progressDialog(applicationContext)
                kullanicilar(eposta,sifre,context)
            }
        }
        btnUyeOl.setOnClickListener{
            uyeOlEkraniGetir()
        }
    }
    private fun kullanicilar(eposta: String, sifre: String,context: Context) {
        firebaseAuth.signInWithEmailAndPassword(eposta,sifre).addOnCompleteListener{task ->
            if (task.isSuccessful){
                uyari.dismiss()
                kategoriEkraniGetir()
            }
            else{
                uyari.dismiss()
                Toast.makeText(applicationContext, context.resources.getString(R.string.HATA) + task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun uyeOlEkraniGetir() {
        val intent = Intent(this, KayitOlActivity::class.java)
        startActivity(intent)
    }
    private fun kategoriEkraniGetir() {
        val intent = Intent(this, KategoriActivity::class.java)
        startActivity(intent)
    }
    private fun progressDialog(context: Context){
        uyari = ProgressDialog(this@GirisActivity)
        uyari.setTitle(context.resources.getString(R.string.proggresdialogBaslik))
        uyari.setMessage(context.resources.getString(R.string.proggresdialogMesaj))
        uyari.show()
    }
}