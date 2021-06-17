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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.h5190063_gorkemaydogdu_getir.R
import java.util.HashMap

class KayitOlActivity : AppCompatActivity() {
    private lateinit var firebaseAuth : FirebaseAuth
    lateinit var uyari : ProgressDialog
    lateinit var getir : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitol)
        init(applicationContext)
    }
    fun init(context: Context){
        firebaseAuth = FirebaseAuth.getInstance()
        val kullanicicepTel = findViewById<EditText>(R.id.editTextCepTel)
        val kullaniciadSoyad = findViewById<EditText>(R.id.editTextAdSoyad)
        val kullaniciePosta = findViewById<EditText>(R.id.editTextEposta)
        val kullanicisifre = findViewById<EditText>(R.id.editTextSifre)
        val btnUyeol = findViewById<Button>(R.id.btn_UyeOl)

        btnUyeol.setOnClickListener{
            val isimSoyisim : String = kullaniciadSoyad.text.toString()
            val sifre : String = kullanicisifre.text.toString()
            val eposta : String = kullaniciePosta.text.toString()
            val cepTel : String = kullanicicepTel.text.toString()
            if (TextUtils.isEmpty(isimSoyisim) || TextUtils.isEmpty(sifre) || TextUtils.isEmpty(eposta) || TextUtils.isEmpty(cepTel)){
                Toast.makeText(applicationContext,resources.getString(R.string.EpostaIsımCepSifre),Toast.LENGTH_SHORT).show()
            }
            else{
                progressDialog(applicationContext)
                kullanicilar(isimSoyisim, sifre, eposta, cepTel, context)
            }
        }
    }
    private fun kullanicilar(isimSoyisim: String, sifre: String, eposta: String, cepTel: String, context: Context) {
        firebaseAuth.createUserWithEmailAndPassword(eposta, sifre).addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 val kullanici_ID = firebaseAuth.currentUser!!.uid
                 getir = FirebaseDatabase.getInstance().reference.child(context.resources.getString(R.string.Kullanicilar)).child(kullanici_ID)
                 val kullanici = HashMap<String, Any>()
                 kullanici[context.resources.getString(R.string.AdSoyad)] = isimSoyisim
                 kullanici[context.resources.getString(R.string.Sifre)] = sifre
                 kullanici[context.resources.getString(R.string.Email)] = eposta
                 kullanici[context.resources.getString(R.string.CepTelefonu)] = cepTel

                 getir.setValue(kullanici).addOnCompleteListener(OnCompleteListener<Void?> { task ->
                     if (task.isSuccessful) {
                         uyari.dismiss()
                         ikinciEkraniGetir()
                     }
                 })
             } else {
                 uyari.dismiss()
                 Toast.makeText(applicationContext, context.resources.getString(R.string.HATA) + task.exception!!.message, Toast.LENGTH_SHORT).show()
             }
        }
    }
    private fun progressDialog(context: Context){
        uyari = ProgressDialog(this@KayitOlActivity)
        uyari.setTitle(context.resources.getString(R.string.proggresdialogBaslik))
        uyari.setMessage(context.resources.getString(R.string.proggresdialogMesaj))
        uyari.show()
    }
    private fun ikinciEkraniGetir() {
        val intent = Intent(this, KategoriActivity::class.java)
        startActivity(intent)
    }
}