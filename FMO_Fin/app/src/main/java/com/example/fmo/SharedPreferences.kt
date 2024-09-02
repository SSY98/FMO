package com.example.fmo

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferences (context: Context) {
    private val prefsFilename = "prefs"
    private val prefsKeyEdt = "myEditText"
    private val prefsKeyEdt2 = "myEditText2"
    private val prefsKeyEdt3 = "myEditText3"
    private val prefsKeyEdt4 = "myEditText4"
    private val prefsKeyEdt5 = "myEditText5"
    private val prefsKeyEdt6 = "myEditText6"
    private val prefsKeyEdt7 = "myEditText7"
    private val prefsKeyEdt8 = "myEditText8"
    private val prefsKeyEdt9 = "myEditText9"
    private val prefsKeyEdt10 = "myEditText10"
    private val prefsKeyEdt11 = "myEditText11"


    private val prefsKeyEdt12 = "myEditText12" //아침
    private val prefsKeyEdt13 = "myEditText13"

    private val prefsKeyEdt14 = "myEditText14" //점심
    private val prefsKeyEdt15 = "myEditText15"

    private val prefsKeyEdt16 = "myEditText16" //저녁
    private val prefsKeyEdt17 = "myEditText17"

    private val prefsKeyEdt18 = "myEditText18"

    private val prefsKeyEdt19 = "myEditText19" // bmi
    private val prefsKeyEdt20 = "myEditText20" // bmi 결과
    private val prefsKeyEdt21 = "myEditText21" // 알림 피드백

    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename,MODE_PRIVATE)

    //##가져오기
    private val prefsKeyEdt22 = "insertbool"
    private val prefsKeyEdt23 = "saveday"
    private val prefsKeyEdt24 = "testbool"

    //##가져오기

    // get() 저장된 값을 반환, 기본 값은 "", set(value)은 value 값을 대체한 후 저장
    var myEditText: String?
        get() = prefs.getString(prefsKeyEdt, "")
        set(value) = prefs.edit().putString(prefsKeyEdt, value).apply()
    var myEditText2: String?
        get() = prefs.getString(prefsKeyEdt2, "")
        set(value) = prefs.edit().putString(prefsKeyEdt2, value).apply()
    var myEditText3: String?
        get() = prefs.getString(prefsKeyEdt3, "")
        set(value) = prefs.edit().putString(prefsKeyEdt3, value).apply()
    var myEditText4: String?
        get() = prefs.getString(prefsKeyEdt4, "")
        set(value) = prefs.edit().putString(prefsKeyEdt4, value).apply()
    var myEditText5: String?
        get() = prefs.getString(prefsKeyEdt5, "")
        set(value) = prefs.edit().putString(prefsKeyEdt5, value).apply()
    var myEditText6: String?
        get() = prefs.getString(prefsKeyEdt6, "")
        set(value) = prefs.edit().putString(prefsKeyEdt6, value).apply()
    var myEditText7: Boolean
        get() = prefs.getBoolean(prefsKeyEdt7, false)
        set(value) = prefs.edit().putBoolean(prefsKeyEdt7, value).apply()
    var myEditText8: Boolean
        get() = prefs.getBoolean(prefsKeyEdt8, false)
        set(value) = prefs.edit().putBoolean(prefsKeyEdt8, value).apply()
    var myEditText9: Boolean
        get() = prefs.getBoolean(prefsKeyEdt9, false)
        set(value) = prefs.edit().putBoolean(prefsKeyEdt9, value).apply()
    var myEditText10: Boolean
        get() = prefs.getBoolean(prefsKeyEdt10, false)
        set(value) = prefs.edit().putBoolean(prefsKeyEdt10, value).apply()
    var myEditText11: Boolean
        get() = prefs.getBoolean(prefsKeyEdt11, false)
        set(value) = prefs.edit().putBoolean(prefsKeyEdt11, value).apply()

    //아침,점심,저녁
    var myEditText12: String?
        get() = prefs.getString(prefsKeyEdt12, "")
        set(value) = prefs.edit().putString(prefsKeyEdt12, value).apply()
    var myEditText13: String?
        get() = prefs.getString(prefsKeyEdt13, "0")
        set(value) = prefs.edit().putString(prefsKeyEdt13, value).apply()

    var myEditText14: String?
        get() = prefs.getString(prefsKeyEdt14, "")
        set(value) = prefs.edit().putString(prefsKeyEdt14, value).apply()
    var myEditText15: String?
        get() = prefs.getString(prefsKeyEdt15, "0")
        set(value) = prefs.edit().putString(prefsKeyEdt15, value).apply()

    var myEditText16: String?
        get() = prefs.getString(prefsKeyEdt16, "")
        set(value) = prefs.edit().putString(prefsKeyEdt16, value).apply()
    var myEditText17: String?
        get() = prefs.getString(prefsKeyEdt17, "0")
        set(value) = prefs.edit().putString(prefsKeyEdt17, value).apply()
   
    // 권장칼로리, bmi 지수, bmi 결과
    var myEditText18: String?
        get() = prefs.getString(prefsKeyEdt18, "0")
        set(value) = prefs.edit().putString(prefsKeyEdt18, value).apply()
    var myEditText19: String?
        get() = prefs.getString(prefsKeyEdt19, "0")
        set(value) = prefs.edit().putString(prefsKeyEdt19, value).apply()
    var myEditText20: String?
        get() = prefs.getString(prefsKeyEdt20, "")
        set(value) = prefs.edit().putString(prefsKeyEdt20, value).apply()

    // 알림 피드백
    var myEditText21: String?
        get() = prefs.getString(prefsKeyEdt21, "")
        set(value) = prefs.edit().putString(prefsKeyEdt21, value).apply()

    //##가져오기
    var insertbool: String?
        get() = prefs.getString(prefsKeyEdt22, "false")
        set(value) = prefs.edit().putString(prefsKeyEdt22, value).apply()
    var saveday: String?
        get() = prefs.getString(prefsKeyEdt23, "")
        set(value) = prefs.edit().putString(prefsKeyEdt23, value).apply()
    //##가져오기
    var testbool: String?
        get() = prefs.getString(prefsKeyEdt24, "false")
        set(value) = prefs.edit().putString(prefsKeyEdt24, value).apply()

}

