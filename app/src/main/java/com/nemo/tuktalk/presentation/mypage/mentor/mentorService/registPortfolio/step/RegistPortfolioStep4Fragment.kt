package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.HorizontalItemDecorator
import com.nemo.tuktalk.databinding.FragmentMentorRegistPortfolioStep4Binding
import com.nemo.tuktalk.domain.model.mypage.mentor.portfolio.UploadPreviewImage
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.RegistPortfolioViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.adapter.UploadPreviewImageRVadpater
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegistPortfolioStep4Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistPortfolioStep4Binding
    private val viewModel : RegistPortfolioViewModel by sharedViewModel()

    lateinit var rvAdapter: UploadPreviewImageRVadpater
    private var testDataSet = mutableListOf<UploadPreviewImage>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "RegistPortfolioStep4Fragment/ mentor regist portfolio step4 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_portfolio_step4, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 미리보기 이미지 Recycler View 설정
        rvAdapter = UploadPreviewImageRVadpater(testDataSet)
        binding.rvPreviewImage.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvPreviewImage.adapter = rvAdapter
        binding.rvPreviewImage.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet.apply {
            add(UploadPreviewImage("",1))
            add(UploadPreviewImage("",2))
            add(UploadPreviewImage("", 3))

        }
        rvAdapter.updateList(testDataSet)





        // 다음 버튼 -> 현재는 그냥 넘어가게 설정
        binding.btnGotoStep5Active.setOnClickListener {

            Log.e("AppTest","RegistPortfolioStep4Fragment/ go to step5")

            (activity as RegistPortfolioActivity).goToStep5()
        }


    }



}