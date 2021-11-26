package com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorinfoMentoringTabBinding
import com.example.tuktalk.databinding.FragmentMentorinfoPortfolioTabBinding

class PortfolioTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoPortfolioTabBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentor mentoring tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_portfolio_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "mentor mentoring tab fragment onViewCreated")
    }
}