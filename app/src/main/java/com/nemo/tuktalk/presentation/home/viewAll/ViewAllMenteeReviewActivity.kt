package com.nemo.tuktalk.presentation.home.viewAll

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.VerticalItemDecorator
import com.nemo.tuktalk.databinding.ActivityViewallMenteeReviewBinding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem_viewAll
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem2
import com.nemo.tuktalk.presentation.home.viewAll.adapter.ViewAllMenteeReviewRVAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ViewAllMenteeReviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewallMenteeReviewBinding
    private val viewModel: ViewAllMenteeReviewViewModel by viewModel()

    lateinit var rvAdapter: ViewAllMenteeReviewRVAdapter
    private var testDataList = mutableListOf<RealTimeReviewRVitem2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewallMenteeReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //////////////////////////////////////////////////////

        rvAdapter = ViewAllMenteeReviewRVAdapter(testDataList)
        binding.RVRealtimeMenteeReview.layoutManager = LinearLayoutManager(this)
        binding.RVRealtimeMenteeReview.adapter = rvAdapter
        binding.RVRealtimeMenteeReview.addItemDecoration(VerticalItemDecorator(15))


        /////////////////////////////////////////////////////////////////////////////

        // 화면 생성 시 바로 통신
        viewModel.viewAllGetReviewList()

        viewModel.ViewAll_Get_Review_List_Success.observe(this, {
            if(it){
                Log.e("AppTest", "ViewAllMenteeReviewActivity/ 실시간 후기 리스트 조회 성공 -> RV 업데이트")

                rvAdapter.updateList(viewModel.ViewAll_Review_List)
            }
            else{
                Log.e("AppTest", "ViewAllMenteeReviewActivity/ 실시간 후기 리스트 조회 실패")
            }
        })

        viewModel.ViewAll_progressBarVisibility_review.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })




    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}