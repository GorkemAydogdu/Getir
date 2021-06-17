package com.h5190063_gorkemaydogdu_getir.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.h5190063_gorkemaydogdu_getir.R

class SplashEkraniActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashekrani)
        init(applicationContext)
    }
    private fun init(context: Context){
        object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                Log.e(
                        context.resources.getString(R.string.counter),
                        context.resources.getString(R.string.finish)
                )
            }
            override fun onFinish() {
                networkControl()
            }
        }.start()
    }
    private fun networkControl(){
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo = cm.activeNetworkInfo
        if (netinfo != null && netinfo.isConnectedOrConnecting) {
            ikinciEkraniGetir(applicationContext)
        } else {
            alertDialog(applicationContext)
        }
    }
    private fun ikinciEkraniGetir(context: Context?) {
        val intent = Intent(this, GirisActivity::class.java)
        startActivity(intent)
    }
    private fun alertDialog(context: Context) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(context.resources.getString(R.string.uyarı))
        builder.setMessage(context.resources.getString(R.string.mesaj))
        builder.setPositiveButton(context.resources.getString(R.string.internet_ac)){ dialogInterface: DialogInterface, i: Int->
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        builder.setNegativeButton(context.resources.getString(R.string.cikis)){ dialogInterface: DialogInterface, i: Int->
            finish()}
        builder.show()
    }
}