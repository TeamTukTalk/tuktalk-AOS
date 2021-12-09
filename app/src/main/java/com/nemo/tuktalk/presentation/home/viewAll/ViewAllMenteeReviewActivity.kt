package com.nemo.tuktalk.presentation.home.viewAll

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.VerticalItemDecorator
import com.nemo.tuktalk.databinding.ActivityViewallMenteeReviewBinding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem_viewAll
import com.nemo.tuktalk.presentation.home.viewAll.adapter.ViewAllMenteeReviewRVAdapter

class ViewAllMenteeReviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewallMenteeReviewBinding

    lateinit var rvAdapter: ViewAllMenteeReviewRVAdapter
    private var testDataList = mutableListOf<RealTimeMenteeReviewRVitem_viewAll>()

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

        testDataList.apply{ // 임시 데이터
            add(RealTimeMenteeReviewRVitem_viewAll(1, "", "", "",
                    0,"","",
                    " ", 1, false
            ))
            add(RealTimeMenteeReviewRVitem_viewAll(1, "리즈", "네이버", "UIUX 디자인",
                    5,"애니","2021. 10. 12",
                    "궁금했던 내용을 실무 경험으로 얘기해주셔서 너무 도움이 되었습니다. 포트폴리오를 만들려고 하니까 막막했는데 한 멘토님이 어떤 방법으로 제작해야 할지 방향을 잡아주셔서 좋았습니다.",
                    2, false
            ))
            add(RealTimeMenteeReviewRVitem_viewAll(1, "제임스", "쿠팡", "앱",
                    4,"에스","2021. 10. 12", "리뷰 테스트 한 줄 넘어가는 테스트",2, false
            ))
            add(RealTimeMenteeReviewRVitem_viewAll(1, "킴", "네이버", "데이터",
                    1,"제이슨","2021. 10. 12",
                    "리뷰 테스트 리뷰 테스트 긴 문장 테스트 입니다 뚝딱뚝딱뚝딱뚝딱뚝딱 뚝딱뚝딱뚝딱",2, false
            ))
            add(RealTimeMenteeReviewRVitem_viewAll(1, "브라이언", "삼성전자", "UIUX 디자인",
                    2,"존","2021. 10. 12", "리뷰 테스트",2, false
            ))
        }
        rvAdapter.updateList(testDataList)
        /////////////////////////////////////////////////////////////////////////////





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