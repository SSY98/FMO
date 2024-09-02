package com.example.fmo

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class Alarm_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        //step0 뷰를 초기화해주기
        OnOffButton()
        ChangeAlarmTimeButton()
        OnOffButton2()
        ChangeAlarmTimeButton2()

        //step1 데이터 가져오기
        val model = fetchDataFromSharedPreferences()
        val model2 = fetchDataFromSharedPreferences2()

        //step2 뷰에 데이터 그려주기
        renderView(model)
        renderView2(model2)
    }

    private fun OnOffButton() {
        val OnOffButton = findViewById<Button>(R.id.OnOffButton)
        OnOffButton.setOnClickListener {
            //데이터를 확인을 한다.
            val model = it.tag as? AlarmDisplayModel ?: return@setOnClickListener
            val newModel = saveAlarmModel(model.hour, model.minute, model.onOff.not())
            renderView(newModel)
            //온오프에 따라 작업을 처리한다.
            if(newModel.onOff){
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, newModel.hour)
                    set(Calendar.MINUTE, newModel.minute)

                    if(before(Calendar.getInstance())){
                        add(Calendar.DATE, 1)
                    }
                }

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(this, 1000,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )

                val alarmManager2 = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent2 = Intent(this, AlarmReceiver::class.java)
                val pendingIntent2 = PendingIntent.getBroadcast(this, 1001,
                    intent2, PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager2.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent2
                )

                val alarmManager3 = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent3 = Intent(this, AlarmReceiver::class.java)
                val pendingIntent3 = PendingIntent.getBroadcast(this, 1002,
                    intent3, PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager3.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent3
                )

            }else {
                //꺼진 경우 -> 알람을 제거
                cancelAlarm()
            }
        }
    }

    private fun OnOffButton2() {
        val OnOffButton = findViewById<Button>(R.id.OnOffButton2)
        OnOffButton.setOnClickListener {
            //데이터를 확인을 한다.
            val model = it.tag as? AlarmDisplayModel2 ?: return@setOnClickListener
            val newModel = saveAlarmModel2(model.hour, model.minute, model.onOff.not())
            renderView2(newModel)
            //온오프에 따라 작업을 처리한다.
            if(newModel.onOff){
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, newModel.hour)
                    set(Calendar.MINUTE, newModel.minute)

                    if(before(Calendar.getInstance())){
                        add(Calendar.DATE, 1)
                    }
                }

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(this, 1003,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )

            }else {
                //꺼진 경우 -> 알람을 제거
                cancelAlarm()
            }
        }
    }

    private fun ChangeAlarmTimeButton() {
        val AlarmTimeButton = findViewById<Button>(R.id.AlarmTimeButton)
        AlarmTimeButton.setOnClickListener {
            //현재시간을 일단 가져온다
            val calendar = Calendar.getInstance()

            //TimePickDialog 띄워줘서 시간을 설정을 하도록 하게끔 하고, 그 시간을 가져와서
            TimePickerDialog(this, {picker, hour, minute ->

                //데이터를 저장한다.
                val model = saveAlarmModel(hour, minute, false)

                //뷰를 업데이트 한다.
                renderView(model)

                //기존에 있던 알람을 삭제한다.
                cancelAlarm()

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()
        }
    }

    private fun ChangeAlarmTimeButton2() {
        val AlarmTimeButton2 = findViewById<Button>(R.id.AlarmTimeButton2)
        AlarmTimeButton2.setOnClickListener {
            //현재시간을 일단 가져온다
            val calendar = Calendar.getInstance()

            //TimePickDialog 띄워줘서 시간을 설정을 하도록 하게끔 하고, 그 시간을 가져와서
            TimePickerDialog(this, {picker, hour, minute ->

                //데이터를 저장한다.
                val model = saveAlarmModel2(hour, minute, false)

                //뷰를 업데이트 한다.
                renderView2(model)

                //기존에 있던 알람을 삭제한다.
                cancelAlarm2()

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()
        }
    }

    private fun saveAlarmModel(
        hour: Int,
        minute: Int,
        onOff: Boolean
    ): AlarmDisplayModel {
        val model = AlarmDisplayModel(
            hour = hour,
            minute = minute,
            onOff = onOff
        )

        val sharedPreferences = getSharedPreferences("Time", Context.MODE_PRIVATE)

        with(sharedPreferences.edit()){
            putString("Alarm", model.makeDataForDB())
            putBoolean("OnOff", model.onOff)
            commit()
        }
        return model
    }

    private fun saveAlarmModel2(
        hour: Int,
        minute: Int,
        onOff: Boolean
    ): AlarmDisplayModel2 {
        val model = AlarmDisplayModel2(
            hour = hour,
            minute = minute,
            onOff = onOff
        )

        val sharedPreferences = getSharedPreferences("Time2", Context.MODE_PRIVATE)

        with(sharedPreferences.edit()){
            putString("Alarm2", model.makeDataForDB())
            putBoolean("OnOff2", model.onOff)
            commit()
        }
        return model
    }

    private fun fetchDataFromSharedPreferences(): AlarmDisplayModel {
        val sharedPreferences = getSharedPreferences("Time", Context.MODE_PRIVATE)

        val timeDBValue = sharedPreferences.getString("Alarm", "9:30") ?: "9:30"
        val onOffDBValue = sharedPreferences.getBoolean("OnOff", false)
        val alarmData = timeDBValue.split(":")

        val alarmModel = AlarmDisplayModel(
            hour = alarmData[0].toInt(),
            minute = alarmData[1].toInt(),
            onOff = onOffDBValue
        )
        //보정 예외처리
        val pendingIntent = PendingIntent.getBroadcast(this, 1000, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)

        if((pendingIntent == null) and alarmModel.onOff) {
            //알람은 꺼져있는데, 데이터는 켜져있는경우
            alarmModel.onOff = false
        } else if ((pendingIntent != null) and alarmModel.onOff.not()){
            //알람은 켜져있는데, 데이터는 꺼져있는 경우
            //알람을 취소함
            pendingIntent.cancel()
        }
        return alarmModel
    }

    private fun fetchDataFromSharedPreferences2(): AlarmDisplayModel2 {
        val sharedPreferences = getSharedPreferences("Time2", Context.MODE_PRIVATE)

        val timeDBValue = sharedPreferences.getString("Alarm2", "9:30") ?: "9:30"
        val onOffDBValue = sharedPreferences.getBoolean("OnOff2", false)
        val alarmData = timeDBValue.split(":")

        val alarmModel = AlarmDisplayModel2(
            hour = alarmData[0].toInt(),
            minute = alarmData[1].toInt(),
            onOff = onOffDBValue
        )
        //보정 예외처리
        val pendingIntent = PendingIntent.getBroadcast(this, 1003, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)

        if((pendingIntent == null) and alarmModel.onOff) {
            //알람은 꺼져있는데, 데이터는 켜져있는경우
            alarmModel.onOff = false
        } else if ((pendingIntent != null) and alarmModel.onOff.not()){
            //알람은 켜져있는데, 데이터는 꺼져있는 경우
            //알람을 취소함
            pendingIntent.cancel()
        }
        return alarmModel
    }

    private fun renderView(model: AlarmDisplayModel){
        findViewById<TextView>(R.id.ampmTextView).apply{
            text = model.ampmText
        }
        findViewById<TextView>(R.id.timeTextView).apply {
            text = model.timeText
        }
        findViewById<Button>(R.id.OnOffButton).apply{
            text = model.onOffText
            tag = model
        }
    }

    private fun renderView2(model: AlarmDisplayModel2){
        findViewById<TextView>(R.id.ampmTextView2).apply{
            text = model.ampmText
        }
        findViewById<TextView>(R.id.timeTextView2).apply {
            text = model.timeText
        }
        findViewById<Button>(R.id.OnOffButton2).apply{
            text = model.onOffText
            tag = model
        }
    }

    private fun cancelAlarm(){
        val pendingIntent = PendingIntent.getBroadcast(this, 1000, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)
        pendingIntent?.cancel()
    }

    private fun cancelAlarm2(){
        val pendingIntent = PendingIntent.getBroadcast(this, 1003, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)
        pendingIntent?.cancel()
    }
}