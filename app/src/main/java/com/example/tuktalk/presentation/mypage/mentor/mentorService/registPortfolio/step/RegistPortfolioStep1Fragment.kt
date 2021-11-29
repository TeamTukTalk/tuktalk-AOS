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
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegistPortfolioStep1Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep1Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep1Fragment/ mentor regist portfolio step1 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etPortfolioDescription.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etPortfolioDescription.text.toString()
                if(userinput.length > 0) {
                    viewModel.fillDescription(userinput, true)
                }
                else {
                    viewModel.fillDescription("",  false)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        /////////////////////////////

        // 내용 입력 감지
        viewModel.Step1Checked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnGotoStep2Active.visibility = View.VISIBLE
                binding.btnGotoStep2Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep2Active.visibility = View.INVISIBLE
                binding.btnGotoStep2Unactive.visibility = View.VISIBLE
            }
        })

        // 다음 버튼 누를 시
        binding.btnGotoStep2Active.setOnClickListener {
            Log.e("AppTest","RegistPortfolioStep1Fragment/ go to step2, description : ${viewModel.DESCRIPTION}")
            (activity as RegistPortfolioActivity).goToStep2()
        }


        // 작성 TIP 누를 시
        binding.tvOpenTip.setOnClickListener {
            Log.e("AppTest","RegistPortfolioStep1Fragment/ tip dialog open")


        }



    }


}