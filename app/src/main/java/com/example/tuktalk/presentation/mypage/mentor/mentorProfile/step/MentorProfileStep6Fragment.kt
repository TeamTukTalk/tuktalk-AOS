package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.databinding.FragmentMentorProfileStep6Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep6Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep6Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step1 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step6, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 프로필 등록 완료시 마이페이지 첫 화면 가기 설정 하기
       binding.btnComplete.setOnClickListener {

        }

    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step6 fragment onResume")
    }


}