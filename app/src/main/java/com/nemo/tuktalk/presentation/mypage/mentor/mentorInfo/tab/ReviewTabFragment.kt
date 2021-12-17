package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab

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
import com.nemo.tuktalk.databinding.FragmentMentorinfoReviewTabBinding
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem2
import com.nemo.tuktalk.domain.model.mypage.mentor.info.ReviewTabRVitem
import com.nemo.tuktalk.presentation.home.viewAll.ViewAllMenteeReviewViewModel
import com.nemo.tuktalk.presentation.home.viewAll.adapter.ViewAllMenteeReviewRVAdapter
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.adapter.ReviewTabRVadapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewTabFragment: Fragment() {

    private lateinit var binding : FragmentMentorinfoReviewTabBinding
    private val viewModel : MentorInfoViewModel by sharedViewModel()

    lateinit var rvAdapter: ReviewTabRVadapter
    private var testDataList = mutableListOf<ReviewTabRVitem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "ReviewTabFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentorinfo_review_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "ReviewTabFragment/ onViewCreated")

        // RV 설정
        rvAdapter = ReviewTabRVadapter(testDataList)
        binding.rvReviewPossible.layoutManager = LinearLayoutManager(context)
        binding.rvReviewPossible.adapter = rvAdapter
        binding.rvReviewPossible.addItemDecoration(VerticalItemDecorator(15))


        // 후기 리스트 조회 결과 업데이트
        viewModel.IsGetMentorReviewListSuccess.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "ReviewTabFragment/ 멘토 후기 리스트 조회 성공")

                rvAdapter.updateList(viewModel.Mentor_Review_List)

                if(viewModel.IsResultEmpty_review)
                    binding.llNoReview.visibility = View.VISIBLE
                else
                    binding.llNoReview.visibility = View.INVISIBLE

            }
            else{
                Log.e("AppTest", "ReviewTabFragment/ 멘토 후기 리스트 조회 실패")
                Toast.makeText(context, "멘토 후기 정보 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })



    }
}