package com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep1Binding
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep2Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegistPortfolioStep2Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep2Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep2Fragment/ mentor regist portfolio step2 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step2, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로젝트 개수
        binding.etProjectCount.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","project count et position focused")
                binding.cvProjectCount.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvProjectCount.invalidate()
            }
            else{
                Log.e("AppTest","project count et position focus x")
                binding.cvProjectCount.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvProjectCount.invalidate()
            }
        })

        // 페이지 수
        binding.etPageCount.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","page count et position focused")
                binding.cvPageCount.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvPageCount.invalidate()
            }
            else{
                Log.e("AppTest","page count et position focus x")
                binding.cvPageCount.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvPageCount.invalidate()
            }
        })

        // 제작일 - 시작
        binding.etStartYear.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","start year et position focused")
                binding.cvStartYear.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvStartYear.invalidate()
            }
            else{
                Log.e("AppTest","start year et position focus x")
                binding.cvStartYear.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvStartYear.invalidate()
            }
        })

        // 제작일 - 종료
        binding.etEndYear.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","end year et position focused")
                binding.cvEndYear.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvEndYear.invalidate()
            }
            else{
                Log.e("AppTest","end year et position focus x")
                binding.cvEndYear.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvEndYear.invalidate()
            }
        })
        ///////////////////////////////////////

        binding.etProjectCount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etProjectCount.text.toString()
                if(userinput.length > 0) {
                  var count = userinput.toInt()
                    viewModel.fillProjectCount(count)
                }
                else {
                    viewModel.fillProjectCount(0)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etPageCount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etPageCount.text.toString()
                if(userinput.length > 0) {
                    var count = userinput.toInt()
                    viewModel.fillPageCount(count)
                }
                else {
                    viewModel.fillPageCount(0)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etStartYear.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etStartYear.text.toString()
                if(userinput.length > 0) {
                    var count = userinput.toInt()
                    viewModel.fillStartYear(count)
                }
                else {
                    viewModel.fillStartYear(0)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etEndYear.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etEndYear.text.toString()
                if(userinput.length > 0) {
                    var count = userinput.toInt()
                    viewModel.fillEndYear(count)
                }
                else {
                    viewModel.fillEndYear(0)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        ////////////////
        viewModel.Step2Checked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnGotoStep3Active.visibility = View.VISIBLE
                binding.btnGotoStep3Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep3Active.visibility = View.INVISIBLE
                binding.btnGotoStep3Unactive.visibility = View.VISIBLE
            }
        })


        // 다음
        binding.btnGotoStep3Active.setOnClickListener {
            Log.e("AppTest", "RegistPortfolioStep2Fragment/ go to step3, " +
                    "projectCount : ${viewModel.PROJECT_COUNT}\n" +
                    "totalPages : ${viewModel.TOTAL_PAGES}\n" +
                    "startYear : ${viewModel.START_YEAR}\n" +
                    "endYear : ${viewModel.END_YEAR}")
            (activity as RegistPortfolioActivity).goToStep3()
            
        }





    }

}