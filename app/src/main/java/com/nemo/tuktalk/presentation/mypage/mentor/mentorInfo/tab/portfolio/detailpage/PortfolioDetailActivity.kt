package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.detailpage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityPortfolioDetailBinding
import com.nemo.tuktalk.databinding.ActivityPortfolioOpenBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.PortfolioOpenActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.PortfolioOpenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PortfolioDetailActivity: AppCompatActivity() {  // 포트폴이리오 상세페이지

    private lateinit var binding : ActivityPortfolioDetailBinding
    private val viewModel : PortfolioDetailViewModel by viewModel()

    var MENTOR_ID = -1  // 멘티모드에서 최근 본 포트폴리오 목록에서 상세보기 누를 시 해당 멘토 아이디 넘어온다

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "PortfolioDetailActivity/ onCreate")
        binding = DataBindingUtil.setContentView<ActivityPortfolioDetailBinding>(this, R.layout.activity_portfolio_detail)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 제목 보이게하기
        supportActionBar!!.setTitle("상세보기")
        /////////////////////


        // 현재 유저 상태 멘토, 멘티냐에 따라 분기 처리 해줘야 한다
        if(Constants.USER_MODE == 0){

                       //   멘토 모드   //
            Log.e("AppTest", "PortfolioDetailActivity/ 멘토 모드, 모드 번호 = ${Constants.USER_MODE}")

            binding.btnForMentor.visibility = View.VISIBLE  ///////
            binding.btnForMentee.visibility = View.INVISIBLE

            viewModel.getPortfolioDetailInfo(Constants.USER_MENTOR_ID)  // 포트폴이오 상세 정보 가져오기

        }
        else{
                    //  멘티 모드  //
            Log.e("AppTest", "PortfolioDetailActivity/ 멘티 모드, 모드 번호 = ${Constants.USER_MODE}")

            binding.btnForMentor.visibility = View.INVISIBLE
            binding.btnForMentee.visibility = View.VISIBLE  ////////

            MENTOR_ID = intent.getIntExtra("mentorId", -1)  // 선택한 멘토 아이디 받아오기
            Log.e("AppTest", "PortfolioDetailActivity/ mentorId : ${MENTOR_ID}")

            viewModel.getPortfolioDetailInfo(MENTOR_ID)

        }
        ////////////////////////////////////////////////////////////////////////

        // 포트폴리오 상세 정보 조회 후 화면 업데이트
        viewModel.IsGetPortfolioDetialInfoSuccess.observe(this, {
            if(it) {
                Log.e("AppTest", "PortfolioDetailActivity/ 포트폴리오 상세 정보 조회 성공")

                binding.tvPortfolioIntroduction.text = viewModel.PortFolio_Description  // 포트폴리오 소개
                binding.tvProjectCount.text = viewModel.ProjectCount.toString()  // 프로젝트 개수
                binding.tvTotalPage.text = viewModel.TotalPages.toString()  // 총 페이지 수
                binding.tvYearStart.text = viewModel.StartYear.toString()  // 시작일
                binding.tvYearEnd.text = viewModel.EndYear.toString()   // 제작완료 일

                var recommendationTargetDesSample = viewModel.RecommendationTargetDescription
                binding.tvRecommendationTargetDescription.text = recommendationTargetDesSample.replace(" ", "\u00A0") // 추천 내용

            }
            else {
                Log.e("AppTest", "PortfolioDetailActivity/ 포트폴리오 상세 정보 조회 실패")
                Toast.makeText(this, "포트폴리오 상세정보 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility_portfolio.observe(this, {
            if(it)
                binding.loadingProgressBarPortfolio.visibility = View.VISIBLE
            else
                binding.loadingProgressBarPortfolio.visibility = View.INVISIBLE
        })

        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 멘토, 멘티 버튼 누를 시 각각,  수정하기 & 열람하기
        binding.btnForMentor.setOnClickListener {
            Log.e("AppTest", "PortfolioDetailActivity/ 멘토 - 수정하기 버튼 클릭")

            // 수정하기는 우선 서비스 준비 중 상태
            Toast.makeText(this, "서비스 준비중입니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()

        }

        // 멘티 - 열람하기 버튼 누를 시
        binding.btnForMentee.setOnClickListener {
            Log.e("AppTest", "PortfolioDetailActivity/ 멘티 - 열람하기 버튼 클릭")

            // pdf url 열기 구현하기!!
            val intent = Intent(this, PortfolioOpenActivity::class.java)
            intent.putExtra("portfolioPdfUrl", viewModel.PortFolioPdfUrl)  // url 전달
            intent.putExtra("portfolioId", -100) // 이 경우는 멘티 열람 기록에 추가되는게 아니므로 포폴 아이디 값 전달 x
            startActivity(intent)
        }

    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "PortfolioDetailActivity/ toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}