package com.example.tuktalk.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuktalk.databinding.FragmentGuide3Binding

class Guide3Fragment: Fragment() {

    private var guide3binding : FragmentGuide3Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide3Binding.inflate(inflater, container, false)
            guide3binding = binding

            return guide3binding!!.root
    }
}