package com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.databinding.FragmentMentorinfoInfoTabBinding

class InfoTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoInfoTabBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentor info tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_info_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "mentor info tab fragment onViewCreated")
    }
}