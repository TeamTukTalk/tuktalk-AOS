package com.example.tuktalk.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
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

        // 분야 이동
        // 디자인 분야 선택 시
        binding.cvDesign.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchDesign()
        }

        // it/개발 분야 선택 시
        binding.cvIt.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchIT()
        }

        // 상단 문구 공간 클릭 시 ->  직접검색으로 이동
        binding.llGotoSearchDirect.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchDirect()
        }

    }

    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 0
        Log.e("AppTest", "search select fragment onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "Search Select fragment onDestroy")
    }


}