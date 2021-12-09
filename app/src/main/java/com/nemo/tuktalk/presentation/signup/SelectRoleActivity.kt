package com.nemo.tuktalk.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivitySelectRoleBinding


class SelectRoleActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySelectRoleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 멘티로 가입
        binding.cardviewMentee.setOnClickListener {
            val intent = Intent(this, SelectCategoryActivity::class.java)
            intent.putExtra("isMentee", true)
            startActivity(intent)
        }

        // 멘토로 가입
        binding.cardviewMentor.setOnClickListener {
            val intent = Intent(this, SelectCategoryActivity::class.java)
            intent.putExtra("isMentee", false)
            startActivity(intent)
        }

        // 좌측 상단 뒤로가기 아이콘 클릭 시
        /*binding.clBackBtn.setOnClickListener {
            onBackPressed()
        }*/

        // toolbar 설정
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기

    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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