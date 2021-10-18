package com.example.tuktalk.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMainBinding
import com.example.tuktalk.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}