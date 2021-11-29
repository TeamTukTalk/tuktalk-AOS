package com.example.tuktalk.presentation.mypage.mentor.mentorService.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorServiceMentoringTabBinding
import com.example.tuktalk.databinding.FragmentMentorinfoInfoTabBinding

class MentorServiceMentoringTabFragment: Fragment() {   // 1:1 멘토링 tab = 서비스 준비 중

    private lateinit var binding : FragmentMentorServiceMentoringTabBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor service mentoring tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_service_mentoring_tab, container, false)
        return binding.root
    }


}