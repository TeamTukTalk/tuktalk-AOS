package com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.utils.VerticalItemDecorator
import com.nemo.tuktalk.databinding.ActivityMenteeRecentPortfolioBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.domain.model.mypage.mentee.wishlist.WishListItem
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.adapter.MenteeRecentPorfolioRVadapter
import com.nemo.tuktalk.presentation.mypage.mentee.wishlist.adapter.MenteeWishListRVadapter
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.portfolio.PortfolioOpenActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MenteeRecentPortfolioActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMenteeRecentPortfolioBinding
    private val viewModel : MenteeRecentPortfolioViewModel by viewModel()

    lateinit var rvAdapter: MenteeRecentPorfolioRVadapter
    private var testDataSet = mutableListOf<RecentHistoryItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","mentee recent portfolio activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMenteeRecentPortfolioBinding>(this, R.layout.activity_mentee_recent_portfolio)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화 -> HomeAsUp item id = R.id.home
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("최근 본 포트폴리오")
        /////////////////////

        // RV 설정
        rvAdapter = MenteeRecentPorfolioRVadapter(testDataSet,
                openPdfUrl = {
                    // pdf url 열기 구현하기!!
                    val intent = Intent(this, PortfolioOpenActivity::class.java)
                    intent.putExtra("portfolioPdfUrl", it)  // url 전달
                    intent.putExtra("portfolioId", -100) // 포트폴리오 아이디 전달, 이 경우 포폴 아이디 값 음수 전달!!
                    startActivity(intent)
                })
        binding.rvMenteeRecentHistory.layoutManager = LinearLayoutManager(this)
        binding.rvMenteeRecentHistory.adapter = rvAdapter
        binding.rvMenteeRecentHistory.addItemDecoration(VerticalItemDecorator(17))


        // 최근 본 기록 있는지 바로 살펴보기
        viewModel.getRecentHistory()

        viewModel.Is_Get_Recent_History_Success.observe(this, {
            if(it){
                Log.e("AppTest", "MenteeRecentPortfolioActivity/ 최근 본 리스트 검색 통신 성공")
                rvAdapter.updateList(viewModel.RecentHistoryList)

                if(viewModel.IsResultEmpty)
                    binding.llNoRecentHistory.visibility = View.VISIBLE
                else
                    binding.llNoRecentHistory.visibility = View.INVISIBLE
            }
            else{
                Log.e("AppTest", "MenteeRecentPortfolioActivity/ 최근 본 리스트 검색 통신 실패")
                Toast.makeText(this, "최근 본 목록 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        /////////////////////////////////////////////////////////////////////////////////////////////////////


    }


    // toolbar -> 현재 뒤로가기만 활성화
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