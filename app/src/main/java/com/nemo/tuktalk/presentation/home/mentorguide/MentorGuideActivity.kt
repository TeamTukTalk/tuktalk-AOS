package com.nemo.tuktalk.presentation.home.mentorguide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityAccountOptionBinding
import com.nemo.tuktalk.databinding.ActivityMentorGuideBinding
import com.nemo.tuktalk.databinding.ActivityUserBasicInfoBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity

class MentorGuideActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMentorGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "ActivityUserBasicInfo/ mentor service activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMentorGuideBinding>(this, R.layout.activity_mentor_guide)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) //
       // supportActionBar!!.setTitle("계정 설정")
        ///////////////////////////////


        binding.tv51.text = resources.getString(R.string.tv_mentor_guide_5_1).replace(" ", "\u00A0")
        binding.tv61.text = resources.getString(R.string.tv_mentor_guide_6_1).replace(" ", "\u00A0")

//////////////////////////////

        // 뚝딱 멘토 등록하기 버튼 클릭 시
        binding.btnRegistMentor.setOnClickListener {
            Log.e("AppTest","MentorGuideActivity/ go to mentor regist activity")
            val intent = Intent(this, MentorRegistActivity::class.java)
            startActivity(intent)
            finish()  // 멘토 가이드 액티비티는 제거
        }

    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}