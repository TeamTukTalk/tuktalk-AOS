package com.example.tuktalk.presentation.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.databinding.FragmentMypageBinding

var mentorFragment = MyPageMentorFragment()
var menteeFragment = MyPageMenteeFragment()

class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mypage fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
        // 멘토 등록 화면 mypage 프래그먼트 내부의 프래그먼트로??
        //
        //

        Log.e("AppTest", "mypage fragment onViewCreated")




    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mypage fragment onResume")

        if(Constants.USER_MODE == 0){  // 멘토 모드
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_mypage, mentorFragment)
                    .commit()
        }
        else{
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_mypage, menteeFragment)
                    .commit()
        }
    }
}