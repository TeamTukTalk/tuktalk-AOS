package com.example.tuktalk.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuktalk.databinding.FragmentGuide2Binding

class Guide2Fragment: Fragment() {

    private var guide2binding : FragmentGuide2Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide2Binding.inflate(inflater, container, false)
            guide2binding = binding

            return guide2binding!!.root
    }
}