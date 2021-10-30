package com.example.tuktalk.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentSearchSelectBinding

class SearchSelectFragment: Fragment() {

    private lateinit var binding: FragmentSearchSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search select fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_select, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search select fragment onViewCreated")


    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "search select fragment onResume")
    }
}