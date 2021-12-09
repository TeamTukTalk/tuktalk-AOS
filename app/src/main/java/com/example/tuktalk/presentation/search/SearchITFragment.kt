package com.example.tuktalk.presentation.search

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.databinding.FragmentSearchItBinding
import com.example.tuktalk.databinding.FragmentSearchSelectBinding
import com.example.tuktalk.domain.model.search.SearchDesignMentorList
import com.example.tuktalk.domain.model.search.SearchItMentorList
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoActivity
import com.example.tuktalk.presentation.search.adpater.SearchDesignRVadapter
import com.example.tuktalk.presentation.search.adpater.SearchITRVadapter
import com.example.tuktalk.presentation.search.dialog.TagDialogFragment
import com.example.tuktalk.presentation.search.dialog.TagDialogFragment_IT
import com.example.tuktalk.presentation.search.viewModel.SearchItViewModel
import com.google.android.material.card.MaterialCardView
import org.koin.android.viewmodel.ext.android.viewModel

class SearchITFragment: Fragment() {

    private lateinit var binding: FragmentSearchItBinding
    private val viewModel: SearchItViewModel by viewModel()

   // private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    private var isCategorySelected = Array<Boolean>(8) { false }
    // category list
    var categoryCvList = arrayOfNulls<MaterialCardView>(8)
    var categoryTvList = arrayOfNulls<TextView>(8)

    lateinit var rvAdapter: SearchITRVadapter
    private var testDataSet = mutableListOf<SearchItMentorList>()

    private var CAREER_VALUE = ""
    private var COMPANY_INDEX = -1
    private var CAREER_INDEX = -1

    private var COMPANY_VALUE = ""
    private var START_YEAR = 0
    private var SUB_SPECIALITY = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "SearchITFragment/ onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_it, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "SearchITFragment/ onViewCreated")



        // 토글 카드뷰 배열화
        categoryCvList[0] = binding.cvItToggle1
        categoryCvList[1] = binding.cvItToggle2
        categoryCvList[2] = binding.cvItToggle3
        categoryCvList[3] = binding.cvItToggle4
        categoryCvList[4] = binding.cvItToggle5
        categoryCvList[5] = binding.cvItToggle6
        categoryCvList[6] = binding.cvItToggle7
        categoryCvList[7] = binding.cvItToggle8


        // 토글 텍스트뷰 배열화
        categoryTvList[0] = binding.tvCategory1
        categoryTvList[1] = binding.tvCategory2
        categoryTvList[2] = binding.tvCategory3
        categoryTvList[3] = binding.tvCategory4
        categoryTvList[4] = binding.tvCategory5
        categoryTvList[5] = binding.tvCategory6
        categoryTvList[6] = binding.tvCategory7
        categoryTvList[7] = binding.tvCategory8

        // 토글 터치시 효과 설정해주기
        for(index in 0..7){
            categoryCvList[index]!!.setOnClickListener {
                isCategorySelected[index] = !isCategorySelected[index]  // 토글 구현 / 이전 선택 상태의 반대로!!

                if(isCategorySelected[index]){ // 미선택 -> 선택
                    toggleSelect(index) // 나머지 토글은 미선택 처리
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.white))

                    Log.e("AppTest", "SearchITFragment/ 현재 선택된 subspeciality : ${categoryTvList[index]!!.text}")
                    SUB_SPECIALITY = categoryTvList[index]!!.text.toString()
                    viewModel.searchMentorList(SUB_SPECIALITY, COMPANY_VALUE, START_YEAR)
                    // 선택한 토글 반영해서 리스트 호출
                }
                else{
                    categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
                    categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))

                    Log.e("AppTest", "SearchITFragment/ subspeciality 아무것도 선택x")
                    SUB_SPECIALITY = ""
                    viewModel.searchMentorList(SUB_SPECIALITY, COMPANY_VALUE, START_YEAR)
                    // 토글 취소 반영해서 리스트 호출
                }

            }
        }

        //////////////////////////////
        // 멘토 리스트 리사이클러뷰
        rvAdapter = SearchITRVadapter(testDataSet,
            selectMentor = { mentorId : Int ->
                Log.e("AppTest","search tab it -> go to mentor Info activity")
                val intent = Intent(context, MentorInfoActivity::class.java)
                intent.putExtra("mentorId", mentorId)
                startActivity(intent)
            })
        binding.recyclerViewIt.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewIt.adapter = rvAdapter
        binding.recyclerViewIt.addItemDecoration(VerticalItemDecorator(15))

        // 검색 결과
        viewModel.IsSearchItMentorListSuccess.observe(viewLifecycleOwner,{
            if(it){
                Log.e("AppTest", "SearchITFragment/ IT 멘토 리스트 검색 통신 성공")
                rvAdapter.updateList(viewModel.Search_It_Mentor_List)

                if(viewModel.IsResultEmpty)
                    binding.clNoResult.visibility = View.VISIBLE
                else
                    binding.clNoResult.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "SearchITFragment/ 디자인 멘토 리스트 검색 실패")
                Toast.makeText(context, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })
        // 로딩바

        ///////////////////////////////


        //////////
        // 태그 bottom sheet dialog 나타내기
        var dialogView = TagDialogFragment_IT()
        binding.clSelectCareer.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("index_company", COMPANY_INDEX)
            bundle.putInt("index_career", CAREER_INDEX)
            dialogView.arguments = bundle
            dialogView.show(childFragmentManager, "tag_dialog_open")
        }
        binding.clSelectCompanyType.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("index_company", COMPANY_INDEX)
            bundle.putInt("index_career", CAREER_INDEX)
            dialogView.arguments = bundle
            dialogView.show(childFragmentManager, "tag_dialog_open")
        }




        // 우측 상단 x 버튼 클릭 시 뒤로가기
        binding.ivDeleteCircle.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchSelect()
        }


        /////////////////////////////////////////////////////////////////////////////
        // 처음 페이지 생성 시 자동으로 검색 수행
        Log.e("AppTest", "첫 IT/개발 멘토 리스트 조회 test")
        viewModel.searchMentorList(SUB_SPECIALITY, COMPANY_VALUE, START_YEAR)

    }


    fun toggleSelect(selected : Int){ // 인자로 들어온 index 제외 모두 미선택 상태로 변경
        for(index in 0..7){
            if(index != selected){
                isCategorySelected[index] = false
                categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
                categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
            }
        }
    }

    fun clearToggle(){
        for(index in 0..7){
            isCategorySelected[index] = false
            categoryCvList[index]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
            categoryTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
        }
    }


    // 다이얼로그에서 태그 선택되면, 해당 값으로 통신하기!!!
    fun tagSelected(company: String, career : String, index_compnay: Int, index_career:Int, startYear : Int){
        Log.e("AppTest", "SearchITFragment/ tagSelected called, company : ${company} , career : ${career} ...")

        COMPANY_VALUE = company
        CAREER_VALUE = career
        COMPANY_INDEX = index_compnay
        CAREER_INDEX = index_career
        START_YEAR = startYear

        binding.cl1TvCompanyType.text = COMPANY_VALUE
        binding.cl2TvCareer.text = CAREER_VALUE

        // 선택된 태그 적용해서 리스트 호출
        Log.e("AppTest", "SearchITFragment/ companySize : ${COMPANY_VALUE}, startYear : ${START_YEAR}")
        viewModel.searchMentorList(SUB_SPECIALITY, COMPANY_VALUE, START_YEAR)
    }


    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 2
        Log.e("AppTest", "SearchITFragment/ onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("AppTest","SearchITFragment/ onDestoryView  && clear RV dataSet && 설정값 모두 초기화")

        rvAdapter.clearList()
        clearToggle()

        COMPANY_VALUE = ""
        COMPANY_INDEX = -1

        CAREER_VALUE = ""
        CAREER_INDEX = -1

        START_YEAR = 0
        SUB_SPECIALITY = ""
        // 설정 값들 초기화 해주기!!
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "SearchITFragment/ onDestroy")
    }

}