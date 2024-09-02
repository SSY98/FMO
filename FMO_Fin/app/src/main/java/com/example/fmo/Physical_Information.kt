package com.example.fmo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.physical_information.*
import kotlinx.android.synthetic.main.physical_information.btnCancel
import kotlinx.android.synthetic.main.register_information.*

class Physical_Information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.physical_information)

        age_txt.setText(App.prefs.myEditText)
        height_txt.setText(App.prefs.myEditText2)
        weight_txt.setText(App.prefs.myEditText3)
        goalweight_txt.setText(App.prefs.myEditText4)
        gender_txt.setText(App.prefs.myEditText5)
        goal_txt.setText(App.prefs.myEditText6)
        RecommendKcal_txt.setText(App.prefs.myEditText18)
        bmi_txt.setText(App.prefs.myEditText19)
        bmi_result_txt.setText(App.prefs.myEditText20)

        btnChange.setOnClickListener {
            val intent = Intent(this,Change_Information::class.java)
            startActivity(intent)
        }
        btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}