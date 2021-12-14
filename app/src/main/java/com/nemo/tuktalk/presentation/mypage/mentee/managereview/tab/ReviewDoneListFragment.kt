package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.FragmentMenteeManageReviewDoneBinding
import com.nemo.tuktalk.databinding.FragmentMenteeManageReviewPossibleBinding
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.MenteeManageReviewViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReviewDoneListFragment: Fragment() {

    private lateinit var binding : FragmentMenteeManageReviewDoneBinding
    private val viewModel : MenteeManageReviewViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "ReviewDoneListFragment/ onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentee_manage_review_done, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "ReviewDoneListFragment/ onViewCreated")




    }
}