package com.example.fmo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import java.text.SimpleDateFormat

//##가져오기
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        App.prefs.insertbool = "true"
        val currentTime : Long = System.currentTimeMillis()
        val dataFormat1 = SimpleDateFormat("yyyy-MM-dd")
        val nowtime = dataFormat1.format(currentTime)
        App.prefs.saveday = nowtime.toString()
    }
}
//##가져오기