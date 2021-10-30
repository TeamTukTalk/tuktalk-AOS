package com.example.tuktalk.presentation.mypage.mentor.mentorRegist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorRegistStep1Binding
import com.example.tuktalk.databinding.FragmentMentorRegistStep3Binding

class MentorRegistStep3Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistStep3Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor regist step3 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_step3, container, false)
        return binding.root
    }
}