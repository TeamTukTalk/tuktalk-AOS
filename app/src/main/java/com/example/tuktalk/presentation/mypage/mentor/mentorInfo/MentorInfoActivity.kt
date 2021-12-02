package com.example.tuktalk.presentation.mypage.mentor.mentorInfo

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMentorInfoBinding
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.adapter.MentorInfoPagerFragmentStateAdapter
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab.InfoTabFragment
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab.MentoringTabFragment
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab.PortfolioTabFragment
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.tab.ReviewTabFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class MentorInfoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMentorInfoBinding
    private val viewModel : MentorInfoViewModel by viewModel()

    val infoTabFragment = InfoTabFragment()
    val portfolioTabFragment = PortfolioTabFragment()
    val mentoringTabFragment = MentoringTabFragment()
    val reviewTabFragment = ReviewTabFragment()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private var MENTOR_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest", "Mentor Info Activity onCreate")
        
        binding = DataBindingUtil.setContentView<ActivityMentorInfoBinding>(this, R.layout.activity_mentor_info)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기
        ///////////////////////////////////////////////////////////////////////////


        // 멘토리스트 선택 후 해당 멘토의 아이디 받아오기
        MENTOR_ID = intent.getIntExtra("mentorId", 0)
        Log.e("AppTest", "선택한 멘토 id : ${MENTOR_ID}")
        ////////////////////////////////////////////////////

        // tablayout & viewpager2 설정
        val pagerAdapter = MentorInfoPagerFragmentStateAdapter(this)
        pagerAdapter.addFragment(infoTabFragment)
        pagerAdapter.addFragment(portfolioTabFragment)
        pagerAdapter.addFragment(mentoringTabFragment)
        pagerAdapter.addFragment(reviewTabFragment)

        viewPager = binding!!.viewPager2
        tabLayout = binding!!.tabLayout

        // viewpager2 adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("AppTest", "Page ${position+1}")
            }
        })

        // tablayout 연결
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> "멘토정보"
                1 -> "포트폴리오"
                2 -> "1:1 멘토링"
                3 -> "후기"
                else -> {"TAB"}
            }
        }.attach()

      tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var currentTabLayout: LinearLayout = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab!!.position) as LinearLayout
                var textView: TextView = currentTabLayout.getChildAt(1) as TextView
                textView.setTypeface(textView.typeface, Typeface.BOLD)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var currentTabLayout: LinearLayout = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab!!.position) as LinearLayout
                var textView: TextView = currentTabLayout.getChildAt(1) as TextView
                textView.setTypeface(null, Typeface.NORMAL)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        //tabLayout.selectTab(tabLayout.getTabAt(0))
        //

    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu4, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_like ->{
                // 하트 버튼 누를 시
                Log.e("AppTest", "toolbar like btn clicked")

            }
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