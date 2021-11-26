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
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class InfoTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoInfoTabBinding
    private val viewModel : MentorInfoViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentor info tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_info_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "mentor info tab fragment onViewCreated")

        // ViewModel에서 정보 먼저 가져오기!!!
        var detailIntroduction = getString(R.string.tv_mentorinfo_info_detailintroduction_sample)
        binding.tvDetailIntroduction.text = detailIntroduction.replace(" ", "\u00A0") // 리뷰 내용



    }
}