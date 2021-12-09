package com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.FragmentMentorProfileStep6Binding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep6Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep6Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step6 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step6, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 프로필 등록 완료시 마이페이지 첫 화면 가기 설정 하기
       binding.btnComplete.setOnClickListener {
           Log.e("AppTest", "MentorProfileStep6Fragment/  등록 완료 화면 -> 마이페이지 첫 화면 돌아가기")
           activity?.finish()
        }

    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step6 fragment onResume")
    }


}