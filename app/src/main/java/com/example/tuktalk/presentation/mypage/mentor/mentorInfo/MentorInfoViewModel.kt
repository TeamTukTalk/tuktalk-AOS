package com.example.tuktalk.presentation.mypage.mentor.mentorInfo

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.tuktalk.common.Constants_gitignore
import com.example.tuktalk.domain.usecase.mentor.info.GetMentorDetailInfoUseCase

class MentorInfoViewModel(
    private val getMentorDetailInfoUseCase: GetMentorDetailInfoUseCase
): ViewModel() {


    // 멘토 상제정보 조회
    @SuppressLint("CheckResult")
    fun getMentorDetailInfo(mentorId: Int){

        getMentorDetailInfoUseCase.getMentorEmailCertificationResult(Constants_gitignore.USER_TOKEN, mentorId).subscribe(
            {

            },
            {

            }
        )
    }
}