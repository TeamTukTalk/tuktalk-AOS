package com.nemo.tuktalk.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.nemo.tuktalk.databinding.ActivitySplashBinding
import com.nemo.tuktalk.presentation.guide.GuideActivity

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바 없애기
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1.6초 뒤에 안내 페이지로 넘어가도록 현재 설정
        startLoading()

    }


    fun startLoading(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, GuideActivity::class.java)  // 로그인 화면으로 이동
            startActivity(intent)
            finish()  // 스플래쉬 화면은 최초 실행 시 보여줘야 하므로 finish 해주기!!
        }, 1600)
    }
}