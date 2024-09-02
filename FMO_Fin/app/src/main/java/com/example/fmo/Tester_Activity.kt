package com.example.fmo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tester.*
import android.view.View
import android.widget.Button


class Tester_Activity: AppCompatActivity() {
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        setContentView(R.layout.tester)

        add_db.setOnClickListener {
            App.prefs.testbool = "true"
            finishAffinity()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if(App.prefs.testbool == "stop"){
            val Btn: Button = findViewById<View>(R.id.add_db) as Button
            Btn.setEnabled(false)
        }
    }
}