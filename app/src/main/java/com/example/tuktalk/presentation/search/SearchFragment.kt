package com.example.tuktalk.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.databinding.FragmentSearchBinding
import com.example.tuktalk.presentation.mypage.mentorFragment

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    val searchSelectFragment = SearchSelectFragment()
    val searchDirectFragment = SearchDirectFragment()
    val searchITFragment = SearchITFragment()
    val searchDesignFragment = SearchDesignFragment()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search fragment onViewCreated")

        // 첫 화면은 선택 화면
        childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchSelectFragment )
                .commit()


    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "search fragment onResume")
    }
}