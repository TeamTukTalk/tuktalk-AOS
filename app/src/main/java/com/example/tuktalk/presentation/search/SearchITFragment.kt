package com.example.tuktalk.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentSearchItBinding
import com.example.tuktalk.databinding.FragmentSearchSelectBinding

class SearchITFragment: Fragment() {

    private lateinit var binding: FragmentSearchItBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search IT fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_it, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search IT fragment onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "search IT fragment onResume")
    }

}