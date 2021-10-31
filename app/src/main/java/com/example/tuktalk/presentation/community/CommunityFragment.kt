package com.example.tuktalk.presentation.community

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentCommunityBinding
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.presentation.main.MainActivity

class CommunityFragment: Fragment() {

    private lateinit var binding: FragmentCommunityBinding
    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()

        Constants.BOTTOM_NAVI_NUM = 2
        Log.e("AppTest", "community fragment onResume, Bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")
    }
}