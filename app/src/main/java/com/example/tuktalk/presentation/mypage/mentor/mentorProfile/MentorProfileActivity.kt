package com.example.tuktalk.presentation.mypage.mentor.mentorProfile

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMentorProfileBinding

// 멘토 프로필 등록 activity
class MentorProfileActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMentorProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","mentor profile activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMentorProfileBinding>(this, R.layout.activity_mentor_profile)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("프로필 등록/수정")
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_close ->{
                // 닫기 버튼 누를 시
                Log.e("AppTest", "toolbar close btn clicked")

            }
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}