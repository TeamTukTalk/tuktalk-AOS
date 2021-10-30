package com.example.tuktalk.presentation.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentTest1Binding

class Test1Fragment: Fragment() {

    private lateinit var binding: FragmentTest1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "Test1 Fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1, container, false)
        return binding.root

    }

}