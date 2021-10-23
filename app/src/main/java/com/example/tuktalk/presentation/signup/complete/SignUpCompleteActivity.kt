package com.example.tuktalk.presentation.signup.complete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tuktalk.databinding.ActivitySignupCompleteBinding

class SignUpCompleteActivity: AppCompatActivity() {

    private lateinit var binding : ActivitySignupCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}