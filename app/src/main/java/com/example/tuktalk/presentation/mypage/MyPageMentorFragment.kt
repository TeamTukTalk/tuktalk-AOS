package com.example.tuktalk.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMypageMenteeBinding
import com.example.tuktalk.databinding.FragmentMypageMentorBinding
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.example.tuktalk.presentation.signup.SelectRoleActivity

class MyPageMentorFragment: Fragment() {

    private lateinit var binding : FragmentMypageMentorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentor fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_mentor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentor fragment onViewCreated")

        // 모드 전환 액티비티로 이동
        binding.btnMentor.setOnClickListener {
            Log.e("AppTest","go to test2 activity")
            val intent = Intent(context, Test2Activity::class.java)
            startActivity(intent)
        }

        // 멘토 등록 액티비티 이동
        binding.btnMentorRegist.setOnClickListener {
            Log.e("AppTest","go to mentor regist activity")
            val intent = Intent(context, MentorRegistActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        Log.e("AppTest", "mentor fragment onResume")
    }
}