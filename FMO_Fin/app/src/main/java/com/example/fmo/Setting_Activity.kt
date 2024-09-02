package com.example.fmo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.setting.*
import java.io.File
import java.lang.Exception

class Setting_Activity: AppCompatActivity() {
    private fun clearApplicationData() {
        val cache: File = cacheDir
        try {
        } catch (e: Exception) {
        }
        val appDir = File(cache.getParent())
        if (appDir.exists()) {
            val children: Array<String> = appDir.list()
            for (s in children) {
                //삭제하면 안될것들 "example"
                if (s != "lib" && s != "files") {
                    deleteDir(File(appDir, s))
                }
            }
        }
    }

    private fun deleteDir(dir: File): Boolean {
        if (dir != null && dir.isDirectory()) {
            val children: Array<String> = dir.list()
            for (i in children.indices) {
                val success: Boolean = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir.delete();
    }

    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        setContentView(R.layout.setting)

        app_data_reset.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//
//            editor.clear()//전체삭제
//            editor.commit()

            clearApplicationData()

            //재시작
            finishAffinity()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            System.exit(0)
            //종료(실험)
//            ActivityCompat.finishAffinity(this)
//            System.runFinalization()
//            System.exit(0)

        }
    }
}