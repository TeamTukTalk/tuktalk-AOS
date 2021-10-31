package com.example.tuktalk.presentation.search

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentSearchDesignBinding
import com.example.tuktalk.databinding.FragmentSearchDirectBinding
import com.google.android.material.card.MaterialCardView

class SearchDesignFragment: Fragment() {

    private lateinit var binding: FragmentSearchDesignBinding
    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    private var isCategorySelected = Array<Boolean>(5) { false }
    // category list
    var categoryCvList = arrayOfNulls<MaterialCardView>(5)
    var categoryTvList = arrayOfNulls<TextView>(5)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (parentFragment as SearchFragment).goToSearchSelect()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search Design fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_design, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search Design fragment onViewCreated")

        // 토글 카드뷰 배열화
        categoryCvList[0] = binding.cvDesignToggle1
        categoryCvList[1] = binding.cvDesignToggle2
        categoryCvList[2] = binding.cvDesignToggle3
        categoryCvList[3] = binding.cvDesignToggle4
        categoryCvList[4] = binding.cvDesignToggle5

        // 토글 텍스트뷰 배열화
        categoryTvList[0] = binding.tvCategory1
        categoryTvList[1] = binding.tvCategory2
        categoryTvList[2] = binding.tvCategory3
        categoryTvList[3] = binding.tvCategory4
        categoryTvList[4] = binding.tvCategory5

        // 터치시 효과 설정해주기
        for(index in 0..4){
            categoryCvList[index]!!.setOnClickListener {
                isCategorySelected[index] = !isCategorySelected[index]  // 토글 구현

                if(isCategorySelected[index]){ // 선택 되었다면
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.white))
                }
                else{
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
                }

            }
        }

    }



    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 1
        Log.e("AppTest", "search Design fragment onResume")
    }



}