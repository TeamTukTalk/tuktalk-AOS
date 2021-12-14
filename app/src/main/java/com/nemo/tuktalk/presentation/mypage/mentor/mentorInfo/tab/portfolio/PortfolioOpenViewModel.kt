package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.domain.usecase.mentee.MenteeViewPortfolioUseCase

class PortfolioOpenViewModel(
        private val menteeViewPortfolioUseCase: MenteeViewPortfolioUseCase
): ViewModel() {


    // 멘티가 포트폴리오 파일 열어서 보는 경우 조회 기록 등록 위함
    @SuppressLint("CheckResult")
    fun menteeViewPortfolio(portfolioId : Int){

        menteeViewPortfolioUseCase.menteeViewPortfolio(Constants_gitignore.USER_TOKEN, portfolioId).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "PortfolioOpenViewModel/ 멘티 포트폴리오 열람 등록 성공")
                    }
                    else{
                        Log.e("AppTest", "PortfolioOpenViewModel/ 멘티 포트폴리오 열람 등록 실패, code : ${it.code()}")
                    }
                },
                {
                    throwable -> Log.e("AppTest", "PortfolioOpenViewModel/ throwable : ${throwable}, \n" +
                        "멘티 포트폴리오 열람 등록 오류")
                }
        )
    }
}