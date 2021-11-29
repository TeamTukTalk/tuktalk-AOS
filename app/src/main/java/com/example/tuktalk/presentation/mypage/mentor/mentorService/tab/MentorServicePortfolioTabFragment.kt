package com.example.tuktalk.presentation.mypage.mentor.mentorService.tab

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorServiceMentoringTabBinding
import com.example.tuktalk.databinding.FragmentMentorServicePortfolioTabBinding
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity

class MentorServicePortfolioTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorServicePortfolioTabBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "MentorServicePortfolioTabFragment/ mentor service portfolio tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_service_portfolio_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "MentorServicePortfolioTabFragment/ mentor service portfolio tab fragment onCreateView")


        // 등록하기 버튼 클릭 시
        binding.btnGotoRegistPortfolio.setOnClickListener {
            if(Constants.IS_CERTIFIED_MENTOR){
                val intent = Intent(context, RegistPortfolioActivity::class.java)
                startActivity(intent)
            }
            else{  // 기업 메일 인증 연동 후 else 에서는 Toast 메시지만 보여주기!!!
                val intent = Intent(context, RegistPortfolioActivity::class.java)
                startActivity(intent)
            }
        }

    }

}