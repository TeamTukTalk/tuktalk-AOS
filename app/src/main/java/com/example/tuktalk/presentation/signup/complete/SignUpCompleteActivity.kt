package com.example.tuktalk.presentation.signup.complete

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tuktalk.databinding.ActivitySignupCompleteBinding

class SignUpCompleteActivity: AppCompatActivity() {

    private lateinit var binding : ActivitySignupCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    // 뒤로가기 막기   // 어떻게 처리할지 정하고 다시 수정하기
    override fun onBackPressed() {
        Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
    }
}