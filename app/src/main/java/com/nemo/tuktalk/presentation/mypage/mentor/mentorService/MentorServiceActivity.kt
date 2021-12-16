package com.nemo.tuktalk.presentation.mypage.mentor.mentorService

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityMentorServiceBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.adapter.MentorServicePagerFragmentStateAdapter
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.tab.MentorServiceMentoringTabFragment
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.tab.MentorServicePortfolioTabFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nemo.tuktalk.presentation.mypage.MyPageMentorViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MentorServiceActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMentorServiceBinding
    private val viewModel: MentorServiceViewModel by viewModel()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    val portfolioTabFragment = MentorServicePortfolioTabFragment()
    val mentoringTabFragment = MentorServiceMentoringTabFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest", "MentorServiceActivity/ mentor service activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMentorServiceBinding>(this, R.layout.activity_mentor_service)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) //
        supportActionBar!!.setTitle("서비스 관리")

        ///////////////////////////////

        // tablayout & viewpager2 설정
        val pagerAdapter = MentorServicePagerFragmentStateAdapter(this)
        pagerAdapter.addFragment(portfolioTabFragment)
        pagerAdapter.addFragment(mentoringTabFragment)

        viewPager = binding!!.viewPager2
        tabLayout = binding!!.tabLayout

        // viewpager2 adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("AppTest", "Mentor Service Page ${position+1}")
            }
        })

        // tablayout 연결
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> "포트폴리오"
                1 -> "1:1 멘토링"
                else -> {"TAB"}
            }
        }.attach()


    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "MentorServiceActivity/ mentor service activity onResume")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "MentorServiceActivity/ mentor service activity onDestroy")

    }
}