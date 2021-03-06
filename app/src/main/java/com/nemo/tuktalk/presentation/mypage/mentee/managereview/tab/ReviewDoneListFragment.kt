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
import com.nemo.tuktalk.databinding.FragmentMenteeManageReviewDoneBinding
import com.nemo.tuktalk.databinding.FragmentMenteeManageReviewPossibleBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.domain.model.mypage.mentee.reviewlist.ReviewListRvItem
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.MenteeManageReviewViewModel
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.adapter.ReviewDoneRVadapter
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.adapter.ReviewPossibleRVadapter
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.writereview.WriteReviewActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReviewDoneListFragment: Fragment() {

    private lateinit var binding : FragmentMenteeManageReviewDoneBinding
    private val viewModel : MenteeManageReviewViewModel by sharedViewModel()

    lateinit var rvAdapter: ReviewDoneRVadapter
    private var testDataSet = mutableListOf<ReviewListRvItem>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "ReviewDoneListFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentee_manage_review_done, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "ReviewDoneListFragment/ onViewCreated")

        // RV ??????
        rvAdapter = ReviewDoneRVadapter(testDataSet)
        binding.rvReviewDone.layoutManager = LinearLayoutManager(context)
        binding.rvReviewDone.adapter = rvAdapter
        binding.rvReviewDone.addItemDecoration(VerticalItemDecorator(17))

        // ????????? ?????? ????????? ???????????? -> onResume ???????!
        //viewModel.getMenteeReivewList()

        viewModel.Is_Get_Review_List_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "ReviewDoneListFragment/ ?????? ?????? ????????? ?????? ?????? ??????")
                rvAdapter.updateList(viewModel.MenteeReviewList)

                if(viewModel.IsResultEmpty_review)
                    binding.llNoDoneReview.visibility = View.VISIBLE
                else
                    binding.llNoDoneReview.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "ReviewDoneListFragment/ ?????? ?????? ????????? ?????? ?????? ??????")
                Toast.makeText(context, "????????? ?????? ??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility_review.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })
        

    }

    override fun onResume() {   // ?????? ?????? ??? ????????? ?????? ?????? ???????????? ?????? onResume??? ?????? ?????? ????????????
        super.onResume()
        Log.e("AppTest", "ReviewDoneListFragment/ onResume")

        viewModel.getMenteeReivewList()
    }
}