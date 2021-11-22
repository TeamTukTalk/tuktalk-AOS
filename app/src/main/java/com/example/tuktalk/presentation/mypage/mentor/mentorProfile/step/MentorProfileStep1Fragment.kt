package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding

class MentorProfileStep1Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step1 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닉네임은 가져오기
        binding.etNickname.setText(Constants.USER_NICKNAME)  // edittext의 경우 text= 형태가 아닌 setText로 텍스트 값 할당!

        // 한 줄 소개 edit text 활성화 시 커서, cardview 테두리 색상 변경하기
        binding.etOneIntroduce.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et one introduce focused")
                binding.cvOneIntroduce.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvOneIntroduce.invalidate()
            }
            else{
                Log.e("AppTest","et one introduce  focus x")
                binding.cvOneIntroduce.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvOneIntroduce.invalidate()
            }
        })


    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step1 fragment onResume")
    }


}