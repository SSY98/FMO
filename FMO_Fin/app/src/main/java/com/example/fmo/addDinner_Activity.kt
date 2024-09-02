package com.example.fmo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fmo.fd.FoodData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.add_breakfast.*
import kotlinx.android.synthetic.main.add_dinner.*
import kotlinx.android.synthetic.main.add_dinner.tv1
import kotlinx.android.synthetic.main.add_lunch.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class addDinner_Activity : AppCompatActivity(){
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.add_dinner)

        val dsk = findViewById<TextView>(R.id.sum_kcal_d) //칼로리 더하기
        val Views_Kcal = ArrayList<Int>() //칼로리 더하기
        val Dsumkcal : Int = Views_Kcal.sum() // 칼로리 값

        tv1.visibility = View.VISIBLE //잔소리 on

        val Views = ArrayList<String>()
        val Views_str = ArrayList<String>()
        val Dname : String

        val tv1 = findViewById<View>(R.id.tv1) as TextView
        val btnDel = findViewById<View>(R.id.btn_del) as Button

        var numd: Int = 100 // ***********************

        val Views_Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, Views) // adapter 생성 (data와 view를 연결해 주는 관리자)
        listView_d.adapter = Views_Adapter // 어댑터 붙이기
        listView_d.setChoiceMode(ListView.CHOICE_MODE_SINGLE)

        add_dinner.setOnClickListener {
            // api  ===
            var wt : Int = 0
            val desc_korr = eat_dinner.text.toString()
            val call = ApiObject.retrofitService.GetFood(desc_korr, pageno, numofrows, data_type)
            call.enqueue(object : retrofit2.Callback<FoodData>{
                override fun onResponse(call: Call<FoodData>, response: Response<FoodData>) {
                    if(response.isSuccessful) {
                        val gson = Gson()
                        val json = gson.toJson(response.body())
                        val b = json
                        val ww: JSONArray

                        val jsonObject = JSONTokener(json).nextValue() as JSONObject
                        val jsonObject1 = jsonObject.getJSONObject("body")
                        val jsonArray1 = jsonObject1.getString("totalCount")
                        if (jsonArray1.toInt() == 0)
                            Toast.makeText(applicationContext, "선택된 항목이 없습니다.", Toast.LENGTH_SHORT)
                                .show()
                        else {
                            ww = jsonObject1.getJSONArray("items")
                            wt = ww.getJSONObject(0).getInt("NUTR_CONT1") //칼로리값

                            Views_Kcal.add(wt*numd/100) //칼로리 더하기
                            dsk.setText(Views_Kcal.sum().toString()) //칼로리 더하기_저장할 문자열
                            Views.add(0, eat_dinner.text.toString()) // 입력한 내용을 ArrayList에 추가

                            Views_str.add(eat_dinner.text.toString()) //추가

                            Views_Adapter.notifyDataSetChanged() // 새로고침
                            eat_dinner.text = null // eat_breakfast에 적었던 값 지우기 (다음에 입력하기 편하라고)
                            numd = 100
                            gram_d.setText(numd.toString()+"g")
                            tv1.visibility = View.GONE


                        }
                    }
                }
                override fun onFailure(call: Call<FoodData>, t: Throwable) {
                    Toast.makeText(applicationContext, "Load fail", Toast.LENGTH_SHORT).show()
                }

            })
            //-----api


        }

        d_btnConfirm.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            if(Views.size > 0){
                var Dname = Views_str.joinToString(",", "", "", 2, "...") //추가
                // 추가 버튼 누르면 입력값들이 저장
                App.prefs.myEditText16 = Dname
                App.prefs.myEditText17 = dsk.text.toString()
            }
            else {
                App.prefs.myEditText16 = ""
                App.prefs.myEditText17 = "0"
            }


        }

        btnDel.setOnClickListener {
            try {
                val pos = listView_d.checkedItemPosition
                Views.removeAt(pos)
                Collections.reverse(Views_Kcal)
                Collections.reverse(Views_str) //체인지
                Views_str.removeAt(pos) //체인지
                Views_Kcal.removeAt(pos) //칼로리 더하기
                Views_Adapter.notifyDataSetChanged()
                listView_d.clearChoices()
                dsk.setText(Views_Kcal.sum().toString())//칼로리 더하기
                // 삭제하기 눌렀는데 리스트에 아무것도 없었을 때 잔소리 ON/OFF
                if (Views_Adapter!!.count == 0){
                    tv1.visibility = View.VISIBLE
                }else{
                    tv1.visibility = View.GONE
                }
            }catch (e:Exception){
                Toast.makeText(applicationContext, "선택된 항목이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        //'+'버튼
        plus_d.setOnClickListener {
            numd = numd + 100
            gram_d.setText(numd.toString()+"g")
        }
        //'-' 버튼
        minus_d.setOnClickListener {
            if (numd > 100){
                numd = numd - 100
            }
            else {
                Toast.makeText(this, "100g이 최솟값입니다.", Toast.LENGTH_SHORT).show()
            }
            gram_d.setText(numd.toString()+"g")
        }
    }
}