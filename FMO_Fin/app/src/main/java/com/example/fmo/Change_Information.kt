package com.example.fmo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_information.*

class Change_Information : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_information)

        // 변수 선언
        var Age : EditText
        var Gender : RadioGroup
        var Man : RadioButton
        var Woman : RadioButton
        var Height : EditText
        var Weight : EditText
        var Goal : RadioGroup
        var LossWeight : RadioButton
        var MaintainWeight : RadioButton
        var GainWeight : RadioButton
        var GoalWeight : EditText

        // 변수에 위젯 대입
        Age = findViewById<EditText>(R.id.age_edt)
        Gender = findViewById<RadioGroup>(R.id.gender_rgroup)
        Man = findViewById<RadioButton>(R.id.gender_rbutton1)
        Woman = findViewById<RadioButton>(R.id.gender_rbutton2)
        Height = findViewById<EditText>(R.id.height_edt)
        Weight = findViewById<EditText>(R.id.weight_edt)
        Goal = findViewById<RadioGroup>(R.id.goal_rGroup)
        LossWeight = findViewById<RadioButton>(R.id.goal_rbutton1)
        MaintainWeight = findViewById<RadioButton>(R.id.goal_rbutton2)
        GainWeight = findViewById<RadioButton>(R.id.goal_rbutton3)
        GoalWeight = findViewById<EditText>(R.id.goalweight_edt)

        // 저장된 값을 반환한다.
        age_edt.setText(App.prefs.myEditText)
        height_edt.setText(App.prefs.myEditText2)
        weight_edt.setText(App.prefs.myEditText3)
        goalweight_edt.setText(App.prefs.myEditText4)
        gender_rbutton1.setChecked(App.prefs.myEditText7)
        gender_rbutton2.setChecked(App.prefs.myEditText8)
        goal_rbutton1.setChecked(App.prefs.myEditText9)
        goal_rbutton2.setChecked(App.prefs.myEditText10)
        goal_rbutton3.setChecked(App.prefs.myEditText11)

        // 확인 버튼 클릭시
        btnConfirm.setOnClickListener {
            // 각각의 항목(edittext)에 입력된 값 가져오기
            var myAge : String = Age.getText().toString()
            var myHeight : String = Height.getText().toString()
            var myWeight : String = Weight.getText().toString()
            var myGoalWeight : String = GoalWeight.getText().toString()

            // 확인 버튼 누르면 모든 항목에 대해 값이 입력되었는지 확인, 입력이 되지 않았으면 메세지 출력
            if (myAge.isEmpty()){
                Toast.makeText(this,"나이를 입력하세요",Toast.LENGTH_LONG).show()
            }
            else if (!Man.isChecked and !Woman.isChecked){
                Toast.makeText(this,"성별을 입력하세요",Toast.LENGTH_LONG).show()
            }
            else if (myHeight.isEmpty()){
                Toast.makeText(this,"키를 입력하세요",Toast.LENGTH_LONG).show()
            }
            else if (myWeight.isEmpty()){
                Toast.makeText(this,"체중을 입력하세요",Toast.LENGTH_LONG).show()
            }
            else if (!LossWeight.isChecked and !MaintainWeight.isChecked and !GainWeight.isChecked){
                Toast.makeText(this,"목표를 입력하세요",Toast.LENGTH_LONG).show()
            }
            else if (myGoalWeight.isEmpty()){
                Toast.makeText(this,"목표 체중을 입력하세요",Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this, Physical_Information::class.java)
                startActivity(intent)
            }
            // 확인 버튼 누르면 입력값들이 저장되고 껐다 켜도 유지됨
            App.prefs.myEditText = Age.text.toString()
            App.prefs.myEditText2 = Height.text.toString()
            App.prefs.myEditText3 = Weight.text.toString()
            App.prefs.myEditText4 = GoalWeight.text.toString()

            // 라디오 버튼
            if (gender_rbutton1.isChecked) {
                App.prefs.myEditText5 = Man.text.toString()
                App.prefs.myEditText7 = true
                App.prefs.myEditText8 = false
            }
            else if (gender_rbutton2.isChecked){
                App.prefs.myEditText5 = Woman.text.toString()
                App.prefs.myEditText7 = false
                App.prefs.myEditText8 = true
            }

            if (goal_rbutton1.isChecked) {
                App.prefs.myEditText6 = LossWeight.text.toString()
                App.prefs.myEditText9 = true
                App.prefs.myEditText10 = false
                App.prefs.myEditText11 = false
            }
            else if (goal_rbutton2.isChecked){
                App.prefs.myEditText6 = MaintainWeight.text.toString()
                App.prefs.myEditText9 = false
                App.prefs.myEditText10 = true
                App.prefs.myEditText11 = false
            }
            else if (goal_rbutton3.isChecked){
                App.prefs.myEditText6 = GainWeight.text.toString()
                App.prefs.myEditText9 = false
                App.prefs.myEditText10 = false
                App.prefs.myEditText11 = true
            }
            // bmi 계산
            val che = App.prefs.myEditText3?.toFloat()
            val kee = App.prefs.myEditText2?.toFloat()
            val nai = App.prefs.myEditText?.toFloat()
            val Bmi = Math.round(che!! / ((kee!!/100) * (kee/100)))
            App.prefs.myEditText19 = Bmi.toString()

            // bmi 결과
            when (Bmi){
                in 0..19 ->  App.prefs.myEditText20 = "저체중"
                in 19 until 23 -> App.prefs.myEditText20 = "정상"
                in 23 until 25 -> App.prefs.myEditText20 = "과체중"
                in 25 until 35 -> App.prefs.myEditText20 = "비만"
                in 35 until 200 -> App.prefs.myEditText20 = "고도비만"
                else -> App.prefs.myEditText20 = "정보 재입력 요망"
            }
            
            // 권장 칼로리 계산
            var kcal = 0.0
            if (App.prefs.myEditText5 == "남성")
                kcal = (((10*che)+(6.25*kee)-(5.0*nai!!)+5))
            else
                kcal  = (((10*che)+(6.25*kee)-(5.0*nai!!)-151))
            if (App.prefs.myEditText6 == "체중 감량")
                kcal = Math.round((kcal*1.5)-500).toDouble()
            if (App.prefs.myEditText6 == "체중 증가")
                kcal = Math.round((kcal*1.5)+500).toDouble()
            App.prefs.myEditText18 = kcal.toString()
        }
        // 취소 버튼 클릭시
        btnCancel.setOnClickListener {
            val intent = Intent(this, Physical_Information::class.java)
            startActivity(intent)
        }
    }
}