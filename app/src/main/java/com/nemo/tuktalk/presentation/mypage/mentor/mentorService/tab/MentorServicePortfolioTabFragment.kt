package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.tab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMentorServicePortfolioTabBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.MentorServiceViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorServicePortfolioTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorServicePortfolioTabBinding
    private val viewModel : MentorServiceViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "MentorServicePortfolioTabFragment/ mentor service portfolio tab fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_service_portfolio_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "MentorServicePortfolioTabFragment/ mentor service portfolio tab fragment onCreateView")


        viewModel.IsGetMentorPortfolioInfoSuccess.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "MentorServicePortfolioTabFragment/ 포트폴리오 정보 조회 성공")

                if(viewModel.IsPortfolioRegistered){
                    Log.e("AppTest", "MentorServicePortfolioTabFragment/ 포트폴리오 등록 상태")

                    binding.llNoPortfolio.visibility = View.INVISIBLE
                    binding.cvPortfolio.visibility = View.VISIBLE

                    binding.tvDate.text = viewModel.PF_CREATED_TIME
                    binding.tvPortfolioDescription.text = viewModel.PF_DESCRIPTION
                }
                else{
                    Log.e("AppTest", "MentorServicePortfolioTabFragment/ 포트폴리오 미등록 상태")

                    binding.llNoPortfolio.visibility = View.VISIBLE
                    binding.cvPortfolio.visibility = View.INVISIBLE
                }
            }
            else{
                Log.e("AppTest", "MentorServicePortfolioTabFragment/ 포트폴리오 정보 조회 실패")
            }
        })

        viewModel.ProgressBarVisibility_info.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })


        // 등록하기 버튼 클릭 시,   멘토 프로필 등록을 했으며 && 기업 인증을 한 경우 포폴 등록 가능!!!!
        binding.btnGotoRegistPortfolio.setOnClickListener {
            Log.e("AppTest", "MentorServicePortfolioTabFragment/ 멘토 기업 이메일 인증 여부 : ${Constants.IS_CERTIFIED_MENTOR}")
            if(Constants.IS_CERTIFIED_MENTOR && Constants.USER_MENTOR_ID > 0){
                val intent = Intent(context, RegistPortfolioActivity::class.java)
                startActivity(intent)
            }
            else{  // 기업 메일 인증 연동 후 else 에서는 Toast 메시지만 보여주기!!!

                if(Constants.USER_MENTOR_ID <= 0){
                    Toast.makeText(context, "멘토 프로필 등록 (마이뚝딱-프로필 이미지 클릭) 을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "멘토 기업 이메일 인증을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "MentorServicePortfolioTabFragment/ onResume")


        // 등록한 포트폴리오 있는지 가져와야 한다   //  현재는 최대 1개만 존재재
        viewModel.getMentorPortfolioInfo()

        // 포폴등록 후 업데이트 위해 onResume에 통신부분 배치 -> onResume에서 통신하는 것이 괜찮은 것인지??

    }

}