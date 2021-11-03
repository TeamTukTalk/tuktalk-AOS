package com.example.tuktalk.presentation.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentSearchDirectBinding
import com.example.tuktalk.databinding.FragmentSearchItBinding

class SearchDirectFragment: Fragment() {

    private lateinit var binding: FragmentSearchDirectBinding
    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    override fun onAttach(context: Context) {
        super.onAttach(context)

        /*callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (parentFragment as SearchFragment).goToSearchSelect()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "search Direct fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_direct, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search Direct fragment onViewCreated")

        /*callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (parentFragment as SearchFragment).goToSearchSelect()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)*/

        /// 좌측 상단 뒤로가기 버튼 누를 시
        binding.ivBack.setOnClickListener {
            (parentFragment as SearchFragment).goToSearchSelect()
        }

        //////////////////////////////////////   * edit text 의 inputType 'text'로 해주면 완료버튼 보임
        // 직접 검색 후 키보드에서 완료버튼 누르면 닫힘
        binding.etDirectSearch.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false
            if(action == EditorInfo.IME_ACTION_DONE){
                Log.e("AppTest", "키워드 입력 후 키보드 완료 버튼 누름")
                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etDirectSearch.windowToken, 0)

                handled = true
            }

            handled
        }

        binding.etDirectSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 길이 0 되면 첫 화면 보임
                var keywordText = binding.etDirectSearch.text
                if(keywordText.length <= 0){
                    binding.clDirectRV.visibility = View.INVISIBLE
                    binding.clDirectDes.visibility = View.VISIBLE
                }
                else{
                    // 리사이클러뷰 초기화 먼저 해주기!!
                    //
                    binding.clDirectRV.visibility = View.VISIBLE
                    binding.clDirectDes.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


    }

    override fun onResume() {
        super.onResume()

        Constants.SEARCH_FRAGMENT = 3
        Log.e("AppTest", "search Direct fragment onResume")
    }
}