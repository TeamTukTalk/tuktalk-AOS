package com.example.tuktalk.presentation.signup.complete

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tuktalk.databinding.ActivitySignupCompleteBinding
import com.example.tuktalk.presentation.main.MainActivity

class SignUpCompleteActivity: AppCompatActivity() {

    private lateinit var binding : ActivitySignupCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 가입 성공 후 홈 화면 이동
        binding.btnGotoHome.setOnClickListener {
            Log.e("AppTest","회원가입 완료 화면 -> 홈 탭(MainActivity)")
            val intent = Intent(this, MainActivity::class.java)  // 홈 화면 이동
            startActivity(intent)
            finish()
        }



    }

    // 뒤로가기 막기   // 어떻게 처리할지 정하고 다시 수정하기
    override fun onBackPressed() {
        // 현재는 뒤로가기 막은 상태
    }
}