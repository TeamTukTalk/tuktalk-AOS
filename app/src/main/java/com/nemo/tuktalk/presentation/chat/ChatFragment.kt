package com.nemo.tuktalk.presentation.chat

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
import com.nemo.tuktalk.databinding.FragmentChatBinding

class ChatFragment: Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    // 뒤로가기 처리 위함
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("AppTest", "chat fragment onAttach")

        /*callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(3)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback) */
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        Log.e("AppTest", "chat fragment onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "chat fragment onViewCreated")

      /*  callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(3)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)*/



    }

    override fun onResume() {
        super.onResume()

        Constants.BOTTOM_NAVI_NUM = 3
        Log.e("AppTest", "chat fragment onResume, Bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")
    }
}