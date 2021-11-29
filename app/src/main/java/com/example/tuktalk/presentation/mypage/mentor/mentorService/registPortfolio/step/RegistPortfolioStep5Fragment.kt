package com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep4Binding
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep5Binding
import com.example.tuktalk.presentation.login.LoginActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.MentorServiceActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegistPortfolioStep5Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep5Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep5Fragment/ mentor regist portfolio step5 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step5, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "RegistPortfolioStep5Fragment/ mentor regist portfolio step5 fragment onViewCreated")


        // 확인 누르면  나의 서비스로 돌아가면서 등록한 포트폴리오 아이템 뷰가 추가 되어야 한다
        binding.btnComplete.setOnClickListener {
            Log.e("AppTest", "RegistPortfolioStep5Fragment/ portfolio regist finish  back to myService")

            val intent = Intent(activity, MentorServiceActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            // 정보입력 화면에서 그전 액티비티 스택들 모두 제거 후 바로 멘토서비스 액티비티로 이동!

            startActivity(intent)
        }
    }
}