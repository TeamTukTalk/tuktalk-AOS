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
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep2Binding
import com.example.tuktalk.databinding.FragmentMentorRegistPortfolioStep3Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegistPortfolioStep3Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep3Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep3Fragment/ mentor regist portfolio step3 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step3, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "RegistPortfolioStep3Fragment/ mentor regist portfolio step3 fragment onViewCreated")

        // 내용 입력 되었는지
        binding.etRecommendationTargetDescription.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etRecommendationTargetDescription.text.toString()
                if(userinput.length > 0) {
                    viewModel.fillRecommendationTargetDescription(userinput, true)
                }
                else {
                    viewModel.fillRecommendationTargetDescription("",  false)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        /////////////////////////////

        // 내용 입력 감지
        viewModel.Step3Checked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnGotoStep4Active.visibility = View.VISIBLE
                binding.btnGotoStep4Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep4Active.visibility = View.INVISIBLE
                binding.btnGotoStep4Unactive.visibility = View.VISIBLE
            }
        })

        // 다음 버튼 누를 시
        binding.btnGotoStep4Active.setOnClickListener {
            Log.e("AppTest","RegistPortfolioStep3Fragment/ go to step4,\n" +
                    "recommendation target description : ${viewModel.RECOMMENDATION_TARGET_DESCRIPTION}")

            (activity as RegistPortfolioActivity).goToStep4()

        }
        ////////////////////////

    }

}