package com.nemo.tuktalk.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    val searchSelectFragment = SearchSelectFragment()
    val searchDirectFragment = SearchDirectFragment()
    val searchITFragment = SearchITFragment()
    val searchDesignFragment = SearchDesignFragment()

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("AppTest", "search fragment onAttach")

       /* callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(1)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback) */
    }

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

       /* callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).backToHome(1)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)*/
        /////////////////////////////////////////////////////////////////////////////////////////

        // 첫 화면은 선택 화면
       /* childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchSelectFragment )
                .commit() */

        // 탐색 탭에서 다른 탭으로 이동 전 마지막 화면 기억하기 위함
        if(Constants.SEARCH_FRAGMENT == 0){
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_search, searchSelectFragment )
                    .commit()
        }
        else if(Constants.SEARCH_FRAGMENT == 1){
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_search, searchDesignFragment ,"goToDesign")
                    //.addToBackStack("design")
                    .commit()
        }
        else if(Constants.SEARCH_FRAGMENT == 2){
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_search, searchITFragment )
                    .commit()
        }
        else{
            childFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_search, searchDirectFragment )
                    .commit()
        }

    }

    override fun onResume() {
        super.onResume()

        Constants.BOTTOM_NAVI_NUM = 1
        Log.e("AppTest", "search fragment onResume, Bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")
    }

    fun goToSearchSelect(){
        Log.e("AppTest", "goToSearchSelect")
        Constants.SEARCH_FRAGMENT = 0
        childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchSelectFragment )
                .commit()


    }

    fun goToSearchDesign(){
        childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchDesignFragment )
                .commit()
    }

    fun goToSearchIT(){
        childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchITFragment )
                .commit()
    }

    fun goToSearchDirect(){
        childFragmentManager.beginTransaction()
                .replace(R.id.framelayout_search, searchDirectFragment )
                .commit()
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "Search Fragment onDestroy")
        Constants.SEARCH_FRAGMENT = 0 // 선택화면을 최초로 나오게 다시 설정
    }
}