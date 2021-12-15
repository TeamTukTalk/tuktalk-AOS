package com.nemo.tuktalk.presentation.mypage.mentee.managereview

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityMenteeManageReviewBinding
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.adapter.MenteeReviewTabPagerFragmentStateAdapter
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.ReviewDoneListFragment
import com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.ReviewPossileListFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MenteeManageReviewActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMenteeManageReviewBinding
    private val viewModel : MenteeManageReviewViewModel by viewModel()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    val reviewPossibleTabFragment = ReviewPossileListFragment()
    val reviewDoneTabFragment = ReviewDoneListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","MenteeManageReviewActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMenteeManageReviewBinding>(this, R.layout.activity_mentee_manage_review)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화 -> HomeAsUp item id = R.id.home
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("리뷰관리")
        /////////////////////

        // tablayout & viewpager2 설정
        val pagerAdapter = MenteeReviewTabPagerFragmentStateAdapter(this)
        pagerAdapter.addFragment(reviewPossibleTabFragment)
        pagerAdapter.addFragment(reviewDoneTabFragment)

        viewPager = binding!!.viewPager2
        tabLayout = binding!!.tabLayout

        // viewpager2 adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("AppTest", "Mentee review manage Page ${position+1}")
            }
        })

        // tablayout 연결
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> "리뷰작성"
                1 -> "작성한 리뷰"
                else -> {"TAB"}
            }
        }.attach()


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

    override fun onResume() {
        super.onResume()
        Log.e("AppTest","MenteeManageReviewActivity/ onResume")
    }
}