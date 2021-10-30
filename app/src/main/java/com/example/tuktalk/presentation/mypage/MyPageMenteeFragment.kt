package com.example.tuktalk.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMypageMenteeBinding

class MyPageMenteeFragment: Fragment() {

    private lateinit var binding : FragmentMypageMenteeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentee fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_mentee, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMentee.setOnClickListener {
            Log.e("AppTest","go to test2 activity")
            val intent = Intent(context, Test2Activity::class.java)
            startActivity(intent)
        }
    }
}