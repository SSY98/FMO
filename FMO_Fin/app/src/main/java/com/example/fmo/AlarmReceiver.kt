package com.example.fmo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val CheckBreakfast = App.prefs.myEditText12
        val CheckLunch = App.prefs.myEditText14
        val CheckDinner = App.prefs.myEditText16

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "100",
                "식단 알람",
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel)
        }

        if(CheckBreakfast == "") {
            createNotificationChannel(context)
            breakfastNotification(context)
        }
        if (CheckLunch == ""){
            createNotificationChannel(context)
            lunchNotification(context)
        }
        if (CheckDinner == ""){
            createNotificationChannel(context)
            dinnerNotification(context)
        }
        if (CheckBreakfast !== "" && CheckLunch !== "" && CheckDinner !== ""){
            createNotificationChannel2(context)
            feedbackNotification(context)
        }
    }
    private fun createNotificationChannel(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "100",
                "식단 알람",
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel)
        }
    }
    private fun createNotificationChannel2(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "101",
                "식단 알람",
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel)
        }
    }

    private fun breakfastNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            val build = NotificationCompat.Builder(context, "100")
                .setContentTitle("아침 식사")
                .setContentText("식단을 입력해주세요.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notify(1000, build.build())
        }
    }
    private fun lunchNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            val build = NotificationCompat.Builder(context, "100")
                .setContentTitle("점심 식사")
                .setContentText("식단을 입력해주세요.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notify(1001, build.build())
        }
    }

    private fun dinnerNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            val build = NotificationCompat.Builder(context, "100")
                .setContentTitle("저녁 식사")
                .setContentText("식단을 입력해주세요.")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notify(1002, build.build())
        }
    }

    private fun feedbackNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            val needkcal :String = App.prefs.myEditText18.toString()
            val daykcal : Int = ((App.prefs.myEditText15?.toInt() ?:0 ) + (App.prefs.myEditText17?.toInt() ?: 0) + (App.prefs.myEditText13?.toInt()
                ?: 0))
            var feedback : String = App.prefs.myEditText21.toString()
            val build = NotificationCompat.Builder(context, "101")
                .setContentTitle("오늘의 피드백")
                .setContentText("권장/섭취 : "+needkcal+" / "+daykcal+" kcal "+"   피드백 : "+feedback)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notify(1003, build.build())
        }
    }
}