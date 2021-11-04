package com.example.tuktalk.presentation.mypage.mentor.mentorProfile

import android.os.Bundle
import android.util.Log
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

    }
}