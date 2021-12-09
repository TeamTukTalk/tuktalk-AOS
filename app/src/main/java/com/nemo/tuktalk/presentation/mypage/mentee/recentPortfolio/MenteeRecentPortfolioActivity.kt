package com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityMenteeRecentPortfolioBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MenteeRecentPortfolioActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMenteeRecentPortfolioBinding
    private val viewModel : MenteeRecentPortfolioViewModel by viewModel()

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