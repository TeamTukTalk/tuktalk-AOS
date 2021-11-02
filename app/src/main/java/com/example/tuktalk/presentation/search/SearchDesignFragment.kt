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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.databinding.FragmentSearchDesignBinding
import com.example.tuktalk.databinding.FragmentSearchDirectBinding
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import com.example.tuktalk.presentation.search.adpater.SearchDesignRVadapter
import com.example.tuktalk.presentation.search.dialog.TagDialogFragment
import com.google.android.material.card.MaterialCardView

class SearchDesignFragment: Fragment() {

    private lateinit var binding: FragmentSearchDesignBinding
    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    private var isCategorySelected = Array<Boolean>(5) { false }
    // category list
    var categoryCvList = arrayOfNulls<MaterialCardView>(5)
    var categoryTvList = arrayOfNulls<TextView>(5)

    lateinit var rvAdapter: SearchDesignRVadapter
    private var testDataSet = mutableListOf<PortfolioRV_item>()

   override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search design fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_design, container, false)

       /* callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (parentFragment as SearchFragment).goToSearchSelect()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback) */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search design fragment onViewCreated")


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

                if(isCategorySelected[index]){ // 미선택 -> 선택
                    toggleSelect(index) // 나머지 토글은 미선택 처리
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.white))
                }
                else{
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
                }

            }
        }

        //////////////////////////////
        // 포트폴리오 리사이클러뷰
        rvAdapter = SearchDesignRVadapter(testDataSet)
        binding.recyclerViewDesign.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewDesign.adapter = rvAdapter
        binding.recyclerViewDesign.addItemDecoration(VerticalItemDecorator(15))

        // 임시 데이터
        testDataSet.apply{
            add(PortfolioRV_item(0, "", "", "","",
                    1))
            add(PortfolioRV_item(1, "제이슨", "네이버", "UIUX 디자인","",
            2))
            add(PortfolioRV_item(1, "제이슨", "네이버", "UIUX 디자인","",
                    2))
            add(PortfolioRV_item(1, "제이슨", "네이버", "UIUX 디자인","",
                    2))
            add(PortfolioRV_item(1, "제이슨", "네이버", "UIUX 디자인","",
                    2))
        }

        rvAdapter.updateList(testDataSet)
        rvAdapter.notifyDataSetChanged()


        ///////////////////////////////
        // 우측 상단 x 버튼 누를 시
        binding.ivDeleteCircle.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchSelect()
        }

        //////////
        // 태그 bottom sheet dialog 나타내기
        var dialogView = TagDialogFragment()
        binding.clSelectCareer.setOnClickListener {
            dialogView.show(childFragmentManager, "tag_dialog_open")
        }
        binding.clSelectCompanyType.setOnClickListener {
            dialogView.show(childFragmentManager, "tag_dialog_open")
        }
    }



    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 1
        Log.e("AppTest", "search design fragment onResume")
    }


    fun toggleSelect(selected : Int){ // 인자로 들어온 index 제외 모두 미선택 상태로 변경
        for(index in 0..4){
            if(index != selected){
                isCategorySelected[index] = false
                categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
                categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("AppTest","search design fragment onDestoryView  && clear RV dataSet")
        rvAdapter.clearList()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest","search design fragment onDestory")
    }
}