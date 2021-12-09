package com.nemo.tuktalk.presentation.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMypageBinding

var mentorFragment = MyPageMentorFragment()
var menteeFragment = MyPageMenteeFragment()

class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("AppTest", "mypage fragment onAttach")
       /* callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(4)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback) */
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mypage fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(4)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)*/

        // 멘토 등록 화면 mypage 프래그먼트 내부의 프래그먼트로??
        //
        //

        Log.e("AppTest", "mypage fragment onViewCreated")




    }

    override fun onResume() {
        super.onResume()

        Constants.BOTTOM_NAVI_NUM = 4
        Log.e("AppTest", "mypage fragment onResume, Bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")

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