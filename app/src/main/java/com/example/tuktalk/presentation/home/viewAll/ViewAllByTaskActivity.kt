package com.example.tuktalk.presentation.home.viewAll

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuktalk.R
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.databinding.ActivityLoginBinding
import com.example.tuktalk.databinding.ActivityViewallBytaskBinding
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.home.MentorListRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import com.example.tuktalk.presentation.home.adapter.Top5MentorRVAdpater
import com.example.tuktalk.presentation.home.viewAll.adapter.ViewAllByTaskRVAdapter

class ViewAllByTaskActivity: AppCompatActivity() {

    private lateinit var binding : ActivityViewallBytaskBinding

    lateinit var rvAdapter: ViewAllByTaskRVAdapter
    private var testDataList = mutableListOf<MentorListRVitem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewallBytaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 제목 안보임
        ///////////

        // 리사이클러뷰 설정
        rvAdapter = ViewAllByTaskRVAdapter(testDataList)
        binding.RVByTask.layoutManager = LinearLayoutManager(this)
        binding.RVByTask.adapter = rvAdapter
        binding.RVByTask.addItemDecoration(VerticalItemDecorator(15))

        // 처음 상태는 디자인 토글 선택 -> 액티비티 생성되면서 디자인 값으로 리스트 가져오기!! @@@@@@@@@@@@@@@

        testDataList.apply{
            add(MentorListRVitem(0, "", "", "","",
                    1))
            add(MentorListRVitem(1, "제이슨", "네이버", "UIUX 디자인",
                    "#이직 #실무 #상담 #UX #면접 #공채 #앱 #이직",
                    2))
            add(MentorListRVitem(1, "제이슨", "네이버", "UIUX 디자인","#이직 #실무 #상담 #UX #면접 #공채 #앱 #이직",
                    2))
            add(MentorListRVitem(1, "제이슨", "네이버", "UIUX 디자인",
                    "#이직 #실무 #상담 #UX #면접 #공채 #앱 #이직",
                    2))
            add(MentorListRVitem(1, "제이슨", "네이버", "UIUX 디자인",
                    "#이직 #실무 #상담 #UX #면접 #공채 #앱 #이직",
                    2))
        }

        rvAdapter.updateList(testDataList)
        /////////////////////////////////////////////////////////////////


        // 직무별 cardview 토글
        binding.cvByTaskDesign.setOnClickListener {
            // 토글 선택 시 RV 리스트 초기화 후 통신 결과 리스트로 업데이트 해주기
            // 이때 첫 번째는  empty item view 넣어주기!!
            binding.cvByTaskDesign.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
            binding.tvCategoryDesign.setTextColor(resources.getColor(R.color.white))
            binding.cvByTaskIt.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
            binding.tvCategoryIt.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))

        }
        binding.cvByTaskIt.setOnClickListener {
            binding.cvByTaskIt.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
            binding.tvCategoryIt.setTextColor(resources.getColor(R.color.white))
            binding.cvByTaskDesign.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
            binding.tvCategoryDesign.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
        }

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