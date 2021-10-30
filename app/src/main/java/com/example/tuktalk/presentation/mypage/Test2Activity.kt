package com.example.tuktalk.presentation.mypage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.ActivityTest2Binding

class Test2Activity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityTest2Binding>(this, R.layout.activity_test2)

        Log.e("AppTest", "test2 activity onCreate")

        binding.tvTest2.setOnClickListener {
            if(Constants.USER_MODE == 0){
                Constants.USER_MODE = 1
                Log.e("AppTest", "test2 activity user mode change : mentor -> mentee")
            }
            else{
                Constants.USER_MODE = 0
                Log.e("AppTest", "test2 activity user mode change : mentee -> mentor")
            }
        }


    }
}