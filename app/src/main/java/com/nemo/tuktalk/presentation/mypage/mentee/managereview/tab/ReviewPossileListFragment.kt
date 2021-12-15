package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.VerticalItemDecorator
import com.nemo.tuktalk.databinding.FragmentMenteeManageReviewPossibleBinding
import com.nemo.tuktalk.databinding.FragmentMentorServicePortfolioTabBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.MenteeManageReviewViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.adapter.ReviewPossibleRVadapter
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview.WriteReviewActivity
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.adapter.MenteeRecentPorfolioRVadapter
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.PortfolioOpenActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReviewPossileListFragment: Fragment() {

    private lateinit var binding : FragmentMenteeManageReviewPossibleBinding
    private val viewModel : MenteeManageReviewViewModel by sharedViewModel()

    lateinit var rvAdapter: ReviewPossibleRVadapter
    private var testDataSet = mutableListOf<RecentHistoryItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "ReviewPossileListFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentee_manage_review_possible, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "ReviewPossileListFragment/ onViewCreated")


        // RV 설정
        rvAdapter = ReviewPossibleRVadapter(testDataSet,
                writeReview = {
                    // pdf url 열기 구현하기!!
                    val intent = Intent(context, WriteReviewActivity::class.java)  // 리뷰 작성 액티비티로 변경하기
                    intent.putExtra("mentorId", it)  // mentorId 전달
                    startActivity(intent)
                })
        binding.rvReviewPossible.layoutManager = LinearLayoutManager(context)
        binding.rvReviewPossible.adapter = rvAdapter
        binding.rvReviewPossible.addItemDecoration(VerticalItemDecorator(17))


        // 리뷰 작성 가능 리스트 가져오기 = 최근 열람 리스트
        viewModel.getRecentHistory()

        viewModel.Is_Get_Recent_History_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "ReviewPossileListFragment/ 최근 본 리스트 검색 통신 성공")
                rvAdapter.updateList(viewModel.RecentHistoryList)

                if(viewModel.IsResultEmpty)
                    binding.llNoPossibleReview.visibility = View.VISIBLE
                else
                    binding.llNoPossibleReview.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "ReviewPossileListFragment/ 최근 본 리스트 검색 통신 실패")
                Toast.makeText(context, "최근 본 목록 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        /////////////////////////////////////////////////////////////////////////////////////////////////////



    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "ReviewPossileListFragment/ onResume")
    }
}