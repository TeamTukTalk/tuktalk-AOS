package com.example.tuktalk.presentation.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentCommunityBinding
import com.example.tuktalk.databinding.FragmentHomeBinding

class CommunityFragment: Fragment() {

    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)
        return binding.root
    }
}