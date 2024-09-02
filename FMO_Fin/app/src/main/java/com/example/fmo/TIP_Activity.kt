package com.example.fmo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tip.*

class TIP_Activity: AppCompatActivity() {
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        setContentView(R.layout.tip)

        site1.setOnClickListener {
            Toast.makeText(this, "영양사가 추천하는 다이어트 추천 식단!",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.blog.naver.com/kyungm2001/221718705226"))
            startActivity(intent)
        }

        site2.setOnClickListener {
            Toast.makeText(this, "식단관리의 정석!",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://brunch.co.kr/brunchbook/prettybody2"))
            startActivity(intent)
        }

        site3.setOnClickListener {
            Toast.makeText(this, "건강한 다이어트 식단 짜기!",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://itembbal.com/%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EC%8B%9D%EB%8B%A8-%EC%B6%94%EC%B2%9C-%EA%B1%B4%EA%B0%95%ED%95%9C-%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EC%8B%9D%EB%8B%A8-%EC%A7%9C%EA%B8%B0/"))
            startActivity(intent)
        }

        man.setOnClickListener {
            Toast.makeText(this, "남자 식단관리!",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ktong.kr/entry/%EB%82%A8%EC%9E%90-%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EC%8B%9D%EB%8B%A8/"))
            startActivity(intent)
        }

        woman.setOnClickListener {
            Toast.makeText(this, "여자 식단관리!",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ktong.kr/entry/%EC%97%AC%EC%9E%90-%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8-%EC%8B%9D%EB%8B%A8/"))
            startActivity(intent)
        }

        you1.setOnClickListener {
            Toast.makeText(this, "유튜브에서 식단관리를 검색합니다! ",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EC%8B%9D%EB%8B%A8%EA%B4%80%EB%A6%AC"))
            startActivity(intent)
        }

        you2.setOnClickListener {
            Toast.makeText(this, "유튜브에서 식단 짜는 방법을 검색합니다! ",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lTBPAjs2BtA"))
            startActivity(intent)
        }

        you3.setOnClickListener {
            Toast.makeText(this, "유튜브에서 식단 관리 동기부여 영상을 검색합니다! ",Toast.LENGTH_LONG).show()
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=A5CCe1-Q0t4"))
            startActivity(intent)
        }


    }
}