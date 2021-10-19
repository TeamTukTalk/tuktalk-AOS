package com.example.tuktalk.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuktalk.databinding.FragmentGuide1Binding

class Guide1Fragment: Fragment() {

    private var guide1binding :FragmentGuide1Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide1Binding.inflate(inflater, container, false)
            guide1binding = binding

            return guide1binding!!.root
    }
}