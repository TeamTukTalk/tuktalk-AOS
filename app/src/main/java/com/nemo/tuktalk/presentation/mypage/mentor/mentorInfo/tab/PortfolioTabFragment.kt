package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.FragmentMentorinfoPortfolioTabBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PortfolioTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoPortfolioTabBinding
    private val viewModel : MentorInfoViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "PortfolioTabFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_portfolio_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "PortfolioTabFragment/ onViewCreated")

        ////
        viewModel.IsGetPortfolioDetialInfoSuccess.observe(viewLifecycleOwner, {
            if(it) {
                Log.e("AppTest", "PortfolioTabFragment/ 포트폴리오 상세 정보 조회 성공")

                binding.tvPortfolioIntroduction.text = viewModel.PortFolio_Description  // 포트폴리오 소개
                binding.tvProjectCount.text = viewModel.ProjectCount.toString()  // 프로젝트 개수
                binding.tvTotalPage.text = viewModel.TotalPages.toString()
                binding.tvYearStart.text = viewModel.StartYear.toString()  // 시작일
                binding.tvYearEnd.text = viewModel.EndYear.toString()   // 제작완료 일

                var recommendationTargetDesSample = viewModel.RecommendationTargetDescription
                binding.tvRecommendationTargetDescription.text = recommendationTargetDesSample.replace(" ", "\u00A0") // 추천 내용

            }
            else {
                Log.e("AppTest", "PortfolioTabFragment/ 포트폴리오 상세 정보 조회 실패")
            }
        })
    }

    override fun onResume() {
        super.onResume()

        Log.e("AppTest", "PortfolioTabFragment/ onResume")
    }
}