package com.example.tuktalk.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.example.tuktalk.databinding.FragmentSearchDirectBinding
import com.example.tuktalk.databinding.FragmentSearchItBinding
import com.example.tuktalk.domain.model.search.SearchItMentorList
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoActivity
import com.example.tuktalk.presentation.search.adpater.SearchDirectRVadapter
import com.example.tuktalk.presentation.search.adpater.SearchITRVadapter
import com.example.tuktalk.presentation.search.viewModel.SearchDirectViewModel
import com.example.tuktalk.presentation.search.viewModel.SearchItViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchDirectFragment: Fragment() {

    private lateinit var binding: FragmentSearchDirectBinding
    private val viewModel: SearchDirectViewModel by viewModel()

    lateinit var rvAdapter: SearchDirectRVadapter
    private var testDataSet = mutableListOf<SearchMentorResponseDto>()

    private var QUERY = ""   // 검색어 값


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "SearchDirectFragment/ onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_direct, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "SearchDirectFragment/ onViewCreated")

        /// 좌측 상단 뒤로가기 버튼 누를 시
        binding.ivBack.setOnClickListener {
            binding.etDirectSearch.setText("")
            (parentFragment as SearchFragment).goToSearchSelect()
        }

        //////////////////////////////////////   * edit text 의 inputType 'text'로 해주면 완료버튼 보임
        // 직접 검색 후 키보드에서 완료버튼 누르면 닫힘
        binding.etDirectSearch.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            if(action == EditorInfo.IME_ACTION_DONE){
                Log.e("AppTest", "SearchDirectFragment/ 키워드 입력 후 키보드 완료 버튼 누름")
                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etDirectSearch.windowToken, 0)

                handled = true

                // 입력한 내용으로 멘토 리스트 가져오기!!
                if(QUERY.length == 0){
                    Log.e("AppTest", "SearchDirectFragment/ 검색어 없음")
                    Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.e("AppTest", "SearchDirectFragment/ 검색어 : ${QUERY}")
                    viewModel.searchMentorList(QUERY)
                }

            }
            handled
        }

        // 직접 검색 내용 감지
        binding.etDirectSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 길이 0 되면 첫 화면 보임
                var keywordText = binding.etDirectSearch.text
                if(keywordText.length <= 0){
                    binding.clDirectRV.visibility = View.INVISIBLE
                    binding.clDirectDes.visibility = View.VISIBLE

                    QUERY = ""
                }
                else{
                    // 리사이클러뷰 초기화 먼저 해주기!! ->  굳이 할 필요 x?
                    //
                    binding.clDirectRV.visibility = View.VISIBLE
                    binding.clDirectDes.visibility = View.INVISIBLE

                    QUERY = keywordText.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        /////////////////////////////////////////////////////////////////////////

        // 멘토 리스트 리사이클러뷰
        rvAdapter = SearchDirectRVadapter(testDataSet,
            selectMentor = { mentorId : Int ->
                Log.e("AppTest","search tab direct -> go to mentor Info activity")
                val intent = Intent(context, MentorInfoActivity::class.java)
                intent.putExtra("mentorId", mentorId)
                startActivity(intent)
            })
        binding.rvDirectList.layoutManager = LinearLayoutManager(context)
        binding.rvDirectList.adapter = rvAdapter
        binding.rvDirectList.addItemDecoration(VerticalItemDecorator(15))

        // 검색 결과
        viewModel.IsSearchDirectMentorListSuccess.observe(viewLifecycleOwner,{
            if(it){
                Log.e("AppTest", "SearchDirectFragment/ 직접검색 리스트 검색 통신 성공")
                rvAdapter.updateList(viewModel.Search_Direct_Mentor_List)

                if(viewModel.IsResultEmpty)
                    binding.clNoResult.visibility = View.VISIBLE
                else
                    binding.clNoResult.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "SearchDirectFragment/ 직접검색 리스트 검색 실패")
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



        ///////////////////////////////////////////////////

        // 돋보기 버튼 누른 경우에도 검색 가능하도록
        binding.ivSearch.setOnClickListener {
            if(QUERY.length == 0){
                Log.e("AppTest", "SearchDirectFragment/ 검색어 없음")
                Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                Log.e("AppTest", "SearchDirectFragment/ 검색어 : ${QUERY}")
                viewModel.searchMentorList(QUERY)
            }
        }


    }

    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 3
        Log.e("AppTest", "SearchDirectFragment/ onResume")
    }

    override fun onDestroyView() {
        Log.e("AppTest", "SearchDirectFragment/ onDestroyView")

        // 리사이클러뷰 및 검색어 초기화 해주기
        rvAdapter.clearList()
        QUERY = ""
        binding.etDirectSearch.setText("")


        super.onDestroyView()
    }
}