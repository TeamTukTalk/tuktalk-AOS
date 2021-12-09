package com.example.tuktalk.presentation.mypage.mentor.mentorInfo

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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

        viewModel.CURRENT_MENTOR_ID = MENTOR_ID
        ////////////////////////////////////////////////////

        // tablayout & viewpager2 설정
        val pagerAdapter = MentorInfoPagerFragmentStateAdapter(this)
        pagerAdapter.addFragment(infoTabFragment)
        pagerAdapter.addFragment(portfolioTabFragment)
        //pagerAdapter.addFragment(mentoringTabFragment)  // 1:1 멘토링 기능 보류
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

        // tablayout 연결    // 1:1 멘토링 보류
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = when(position){
                0 -> "멘토정보"
                1 -> "포트폴리오"
                //2 -> "1:1 멘토링"
                2 -> "후기"
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

        /////////////////////////////////////////////////////////////////////////
        // 전달 받은 mentorId로 멘토 상세 정보, 포트폴리오 상제 정보 조회 후 뷰모델에 데이터 저장
        viewModel.getMentorDetailInfo(MENTOR_ID)
        viewModel.getPortfolioDetailInfo(MENTOR_ID)

        viewModel.IsGetMentorDetialInfoSuccess.observe(this, {
            if(it){

                setProfileBackgroundColor(viewModel.ProfileImageColor)    // 프로필 배경
                binding.tvProfileFirstLetter.text = viewModel.FirstLetter  // 닉네임 첫글자
                binding.tvNickname.text = viewModel.Nickname // 멘토 닉네임
                binding.tvCompnayName.text = viewModel.CompanyName // 회사이름
                binding.tvTaskName.text = viewModel.SubSpecialties[0].subSpecialtyValue   // 처음 거로 가져오기? -> department 따로 있어야 되는지 물어보기
                binding.tvSimpleIntroduction.text = viewModel.SimpleIntroduction

            }
            else{
                Log.e("AppTest", "MentorInfoActivity/ 멘토 상세 정보 조회 실패")
                Toast.makeText(this, "멘토 정보를 가져오는 도중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                finish()  // 이전 화면가기
            }
        })

        viewModel.ProgressBarVisibility_info.observe(this, {
            if(it)
                binding.loadingProgressBarInfo.visibility = View.VISIBLE
            else
                binding.loadingProgressBarInfo.visibility = View.INVISIBLE
        })

        /////////////////////////////////////////////////

        viewModel.IsGetPortfolioDetialInfoSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MentorInfoActivity/ 포트폴리오 상세 정보 조회 성공")
            }
            else{
                Log.e("AppTest", "MentorInfoActivity/ 포트폴리오 상세 정보 조회 실패")
                Toast.makeText(this, "멘토의 포트폴리오 정보를 가져오는 도중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ProgressBarVisibility_portfolio.observe(this, {
            if(it)
                binding.loadingProgressBarPortfolio.visibility = View.VISIBLE
            else
                binding.loadingProgressBarPortfolio.visibility = View.INVISIBLE
        })

        // 포트폴리오 열람 버튼 누를 시 -> 먼저 포트폴리오 상세 조회 후  정보들 viewModel에 저장해두기  ->  그 다음 pdf url 열기
        binding.btnGotoOpenPortfolio.setOnClickListener {
            Log.e("AppTest", "MentorInfoActivity/ 포트폴리오 열람 버튼 클릭")
            //viewModel.getPortfolioDetailInfo(MENTOR_ID)

            if(viewModel.IsPortfolioUnregistered){
                Toast.makeText(this, "현재 멘토는 포트폴리오 미등록 상태입니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(viewModel.PortFolioPdfUrl.length <= 0){
                    Toast.makeText(this, "멘토의 포트폴리오 정보가 확인되지 않았습니다. 뒤로가기 후 다시 멘토를 선택해주세요.",
                        Toast.LENGTH_SHORT).show()
                }
            }

            if(viewModel.PortFolioPdfUrl != null && viewModel.PortFolioPdfUrl.length > 0){
                Log.e("AppTest", "MentorInfoActivity/ pdf 열기 페이지로 이동")
            }

        }



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

    fun setProfileBackgroundColor(profileImageColor : String){
        // 프로필 배경색
        when(profileImageColor){
            "profileBlue" -> {
                binding.clProfileImage.setBackgroundResource(R.drawable.profile_image_circle_background_blue)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileBlue_text))
            }
            "profileRed"->{
                binding.clProfileImage.setBackgroundResource(R.drawable.profile_image_circle_background_red)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileRed_text))
            }
            "profileYellow"->{
                binding.clProfileImage.setBackgroundResource(R.drawable.profile_image_circle_background_yellow)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileYellow_text))
            }
            "profileGray"->{
                binding.clProfileImage.setBackgroundResource(R.drawable.profile_image_circle_background_gray)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileGray_text))
            }
            "profileGreen"->{
                binding.clProfileImage.setBackgroundResource(R.drawable.profile_image_circle_background_green)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileGreen_text))
            }
        }
    }
}