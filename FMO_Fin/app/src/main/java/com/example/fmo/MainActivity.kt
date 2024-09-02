package com.example.fmo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.physical_information.*
import kotlinx.android.synthetic.main.register_information.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    //DB 클래스 생성
    inner class myDBHelper(context: Context) : SQLiteOpenHelper(context, "FMOtable", null, 1) {

        //테이블생성
        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL("CREATE TABLE  FMOtable ( fDate DATE PRIMARY KEY, fWeight INT, fKcal INT);")
            //테이블이름 FMOtable, 속성<fDate 날짜형(기본키), fWeight 정수형, fKcal 정수형>
        }


        //테이블 삭제, 초기화 호출할때 사용
        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("DROP TABLE IF EXISTS FMOtable")

            onCreate(p0)
        }
    }
    //DB 클래스 생성 끝
    lateinit var myHelper: myDBHelper //DB 클래스 변수
    lateinit var sqlDB: SQLiteDatabase //sqllite 변수
    //현재시간 알아오기##가져오기
    val currentTime : Long = System.currentTimeMillis()
    val dataFormat1 = SimpleDateFormat("yyyy-MM-dd")
    val nowtime = dataFormat1.format(currentTime)
    //##가져오기


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FMO)
        super.onCreate(savedInstanceState)
        myHelper = myDBHelper(this) //myDB헬퍼 인스턴스 생성(inner oncreate 실행)

        //개인정보 입력값 저장되어있는지 가져오기
        var data:Boolean?
        data=intent.getBooleanExtra("key",false)
        //개인정보 입력값 저장되어있는지 여부
        if(data == true){
            //##가져오기
            if(App.prefs.insertbool=="true"){
                val daykcal : Int = ((App.prefs.myEditText15?.toInt() ?:0 ) + (App.prefs.myEditText17?.toInt() ?: 0) + (App.prefs.myEditText13?.toInt()
                    ?: 0))
                sqlDB = myHelper.writableDatabase //쓰기용 데이터베이스로 열기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+App.prefs.saveday+"'),"+App.prefs.myEditText3+","+daykcal+");")  //insert문을 사용해서 값 넣기
                sqlDB.close()
                App.prefs.insertbool = "false"
                App.prefs.myEditText12=""
                App.prefs.myEditText13="0"
                App.prefs.myEditText14=""
                App.prefs.myEditText15="0"
                App.prefs.myEditText16=""
                App.prefs.myEditText17="0"
            }
            //##가져오기
            //실험실 추가 버튼 누를시 데이터베이스 추가
            if(App.prefs.testbool=="true"){
                val cal = Calendar.getInstance()
                cal.time = Date()
                val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                cal.add(Calendar.DATE, -1)
                sqlDB = myHelper.writableDatabase //쓰기용 데이터베이스로 열기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),71,2500);")  //insert문을 사용해서 값 넣기
                cal.add(Calendar.DATE, -1); //일 빼기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),72,1900);")  //insert문을 사용해서 값 넣기
                cal.add(Calendar.DATE, -1); //일 빼기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),73,2150);")  //insert문을 사용해서 값 넣기
                cal.add(Calendar.DATE, -1); //일 빼기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),74,2270);")  //insert문을 사용해서 값 넣기
                cal.add(Calendar.DATE, -1); //일 빼기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),75,2110);")  //insert문을 사용해서 값 넣기
                cal.add(Calendar.DATE, -1); //일 빼기
                sqlDB.execSQL("INSERT INTO FMOtable VALUES ( date('"+df.format(cal.getTime())+"'),74,2020);")  //insert문을 사용해서 값 넣기
                sqlDB.close()
                App.prefs.testbool = "stop"
            }

            //최근 일주일 데이터 가져오기(알람매니저 성공하면 지워도됨)//##가져오기
            sqlDB = myHelper.readableDatabase
            var cursor2: Cursor
            cursor2 = sqlDB.rawQuery("SELECT fDate,fKcal FROM FMOtable WHERE fDate BETWEEN DATE('"+nowtime+"', '-6 days') AND '"+nowtime+"' order by fDate asc;", null)
            var t = 0
            while (cursor2.moveToNext()){
                t+=1
            }
            cursor2.close()
            sqlDB.close()
            //##가져오기

            //값넣기(알람매니저 성공하면 지워도됨)//##가져오기
            if(t==0){
                //알람
                val calendar = Calendar.getInstance()
                calendar[Calendar.HOUR_OF_DAY] = 23 //시간 설정
                calendar[Calendar.MINUTE] = 50 //분 설정
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, MyReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    (1 * 60 * 1000 * 60 * 24).toLong(), //하루마다반복
                    pendingIntent
                )
                //알람
            }
            //##가져오기

            // 메인에서 신체정보 데이터를 볼 수 있도록 데이터를 불러옴
            setContentView(R.layout.activity_main)
            age_main.setText(App.prefs.myEditText)
            height_main.setText(App.prefs.myEditText2)
            weight_main.setText(App.prefs.myEditText3)
            goalweight_main.setText(App.prefs.myEditText4)
            gender_main.setText(App.prefs.myEditText5)
            goal_main.setText(App.prefs.myEditText6)
            // 신체정보 버튼 클릭하면 신체정보 화면으로 이동
            //bmi
            val che = App.prefs.myEditText3?.toFloat()
            val kee = App.prefs.myEditText2?.toFloat()
            val nai = App.prefs.myEditText?.toFloat()
            val Bmi = Math.round(che!! / ((kee!!/100) * (kee/100)))

            App.prefs.myEditText19 = Bmi.toString()
            bmi.setText(App.prefs.myEditText19)

            when (Bmi){
                in 0..19 ->  App.prefs.myEditText20 = "저체중"
                in 19 until 23 -> App.prefs.myEditText20 = "정상"
                in 23 until 25 -> App.prefs.myEditText20 = "과체중"
                in 25 until 35 -> App.prefs.myEditText20 = "비만"
                in 35 until 200 -> App.prefs.myEditText20 = "고도비만"
                else -> App.prefs.myEditText20 = "정보 재입력 요망"
            }

            result.setText(App.prefs.myEditText20)

            var kcal = 0.0
            if (App.prefs.myEditText5 == "남성")
                kcal = (((10*che)+(6.25*kee)-(5.0*nai!!)+5)) *1.5
            else
                kcal  = (((10*che)+(6.25*kee)-(5.0*nai!!)-151)) *1.5
            if (App.prefs.myEditText6 == "체중 감량")
                kcal = Math.round((kcal)-500).toDouble()
            if (App.prefs.myEditText6 == "체중 증가")
                kcal = Math.round((kcal)+500).toDouble()
            bmi.setText(Bmi.toString())

            App.prefs.myEditText18 = kcal.toString()
            goal_kcal.setText(App.prefs.myEditText18)
            //bmi

            // 하루 총 섭취 칼로리
            val daykcal : Int = ((App.prefs.myEditText15?.toInt() ?:0 ) + (App.prefs.myEditText17?.toInt() ?: 0) + (App.prefs.myEditText13?.toInt()
                ?: 0))

            if (kcal+100 < daykcal){
                App.prefs.myEditText21 = "먹는양을 줄이세요!"
            }
            if (kcal-100 > daykcal){
                App.prefs.myEditText21 = "먹는양을 늘리세요!"
            }
            if (kcal-100 <= daykcal && daykcal <= kcal+100){
                App.prefs.myEditText21 = "좋은 식습관이에요!"
            }

            //메인에서 아점저 데이터를 볼 수 있도록 데이터를 불러옴
            breakfast_main.setText(App.prefs.myEditText12)
            breakfast_kcal_main.setText(App.prefs.myEditText13)
            lunch_main.setText(App.prefs.myEditText14)
            lunch_kcal_main.setText(App.prefs.myEditText15)
            dinner_main.setText(App.prefs.myEditText16)
            diner_kcal_main.setText(App.prefs.myEditText17)

            //그래프사용
            val lineChart  = findViewById<LineChart>(R.id.chart)

            lineChart.setBackgroundColor(Color.WHITE)
            lineChart.setTouchEnabled(false)
            lineChart.description.setEnabled(false)
            var xAxis = lineChart.xAxis
            var yAxisLeft = lineChart.axisLeft
            var yAxisRight = lineChart.axisRight
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
            xAxis.textSize = 13f

            yAxisRight.setDrawLabels(false)
            yAxisLeft.setDrawGridLines(false)
            yAxisLeft.setDrawAxisLine(false)
            yAxisRight.setDrawAxisLine(false)
            yAxisRight.setDrawGridLines(false)
            yAxisLeft.textSize = 13f

            val entries = ArrayList<Entry>()

            val tentries = ArrayList<Entry>()

            val days = arrayOf("1","2","3","4","5","6","7")
            //그래프에 데이터 넣기//##가져오기
            sqlDB = myHelper.readableDatabase
            var cursor: Cursor
            cursor = sqlDB.rawQuery("SELECT fDate,fKcal FROM FMOtable WHERE fDate BETWEEN DATE('"+nowtime+"', '-6 days') AND '"+nowtime+"' order by fDate asc;", null)
            var i = 0
            while (cursor.moveToNext()){
                days.set(i,cursor.getString(0).substring(5,10))
                i+=1
                entries.add(Entry(i.toFloat(),cursor.getString(1).toFloat()))
                tentries.add(Entry(i.toFloat(),(kcal).toFloat()))
            }
            cursor.close()
            sqlDB.close()
            class MyXAxisFormatter : ValueFormatter(){
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return days.getOrNull(value.toInt()-1) ?: value.toString()
                }
            }
            xAxis.setGranularityEnabled(true);
            xAxis.run {
                valueFormatter = MyXAxisFormatter()
            }

            var set = LineDataSet(entries,"칼로리")//데이터셋 초기화 하기
            var tset = LineDataSet(tentries,"표준")
            set.lineWidth = 4f
            tset.lineWidth = 3f
            set.setColor(Color.rgb(255,165,0))
            tset.setColor(Color.MAGENTA)
            val dataSet :ArrayList<ILineDataSet> = ArrayList()
            dataSet.add(set)
            dataSet.add(tset)
            val data = LineData(dataSet)
            lineChart.run {
                this.data = data //차트의 데이터를 data로 설정해줌.
                data.setValueTextSize(0f)
                invalidate()
            }
            //그래프 사용끝

            //DB
            //값입력 #매일 23:59에 값넣기 #미구현
            /*val currentTime : Long = System.currentTimeMillis()
            val dataFormat1 = SimpleDateFormat("yyyy-MM-dd")
            val nowtime = dataFormat1.format(currentTime)*/


            btnInformation.setOnClickListener {
                val intent = Intent(this, Physical_Information::class.java)
                startActivity(intent)
            }
            //아,점,저 눌렀을때
            breakfast.setOnClickListener {

                val intent = Intent(this, addBreakfast_Activity::class.java)
                startActivity(intent)
            }
            lunch.setOnClickListener {
                val intent = Intent(this, addLunch_Activity::class.java)
                startActivity(intent)
            }
            dinner.setOnClickListener{
                val intent = Intent(this, addDinner_Activity::class.java)
                startActivity(intent)
            }
            //아점저 눌렀을때 끝
            btn_navi.setOnClickListener {
                layout_drawer.openDrawer(GravityCompat.START) // START는 left와 같은말
            }
            naviView.setNavigationItemSelectedListener(this) // 네비게이션 메뉴 아이템에 클릭 속성 부여

        }
        else{
            val intent = Intent(this, Register_Information::class.java)
            startActivity(intent)
        }


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean { // 네비게이션 메뉴 아이템 클릭시 수행
        when (item.itemId)
        {
            R.id.tip -> {
                val intent = Intent(this, TIP_Activity::class.java)
                startActivity(intent)}
            R.id.alarm ->{
                val intent = Intent(this, Alarm_Activity::class.java)
                startActivity(intent)}
            R.id.setting ->{
                val intent = Intent(this, Setting_Activity::class.java)
                startActivity(intent)}
            R.id.tester ->{
                val intent = Intent(this, Tester_Activity::class.java)
                startActivity(intent)}

        }
        layout_drawer.closeDrawers() // 네비게이션 뷰닫기
        return false
    }
    var mBackWait:Long = 0 // 뒤로가기 연속 클릭 대기 시간

    override fun onBackPressed() {
        if (layout_drawer.isDrawerOpen(GravityCompat.START))
        {
            layout_drawer.closeDrawers()
        }
        else
        {
            if(System.currentTimeMillis() - mBackWait >= 2000){ //홈 화면에서 백버튼 2초이내로 누를시 앱 종료
                mBackWait = System.currentTimeMillis()
                Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show() }
            else {
                ActivityCompat.finishAffinity(this)
                System.runFinalization()
                System.exit(0)
            }
            //super.onBackPressed() 일반 백버튼 기능(finish)
        }
    }

}
