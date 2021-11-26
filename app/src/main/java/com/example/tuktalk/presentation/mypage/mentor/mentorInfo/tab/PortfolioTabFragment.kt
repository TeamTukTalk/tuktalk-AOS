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

        // 포트폴리오 소개
        var portfolioIntroductionSample = getString(R.string.tv_mentorinfo_portfolio_introduction_sample)
        binding.tvPortfolioIntroduction.text = portfolioIntroductionSample.replace(" ", "\u00A0") // 포트폴리오 소개

        // 프로젝트 개수
        binding.tvProjectCount.text = "5"

        // 페이지 수
        binding.tvTotalPage.text = "56"

        // 제작일
        binding.tvYearStart.text = "2019"
        binding.tvYearEnd.text = "2021"

        // 이런분들께 추천합니다  영역
        var recommendationTargetDesSample = "비전공 개발자"
        binding.tvRecommendationTargetDescription.text = recommendationTargetDesSample.replace(" ", "\u00A0") // 추천 내용
    }
}