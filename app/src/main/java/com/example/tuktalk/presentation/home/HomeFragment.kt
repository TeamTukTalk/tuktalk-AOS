package com.example.tuktalk.presentation.home

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.utils.HorizontalItemDecorator
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.domain.model.home.ByTaskMentorRVitem
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem
import com.example.tuktalk.presentation.home.adapter.BannerVP2Adpater
import com.example.tuktalk.presentation.home.adapter.ByTaskMentorRVAdpater
import com.example.tuktalk.presentation.home.adapter.RealTimeMenteeReviewRVAdpater
import com.example.tuktalk.presentation.home.adapter.Top5MentorRVAdpater
import com.example.tuktalk.presentation.home.viewAll.ViewAllByTaskActivity
import com.example.tuktalk.presentation.home.viewAll.ViewAllMenteeReviewActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.google.android.material.card.MaterialCardView

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    // 상단 배너 이미지 리스트, 뷰페이저2 와 연결
    private var topBannerImageList = mutableListOf(R.drawable.img_banner_1, R.drawable.img_banner_1, R.drawable.img_banner_1)

    lateinit var rvAdapter_top5: Top5MentorRVAdpater
    lateinit var rvAdapter_byTask : ByTaskMentorRVAdpater
    lateinit var rvAdapter_realTime_mentee : RealTimeMenteeReviewRVAdpater
    private var testDataSet_top5 = mutableListOf<HomeTop5MentorRVitem>()
    private var testDataSet_byTask = mutableListOf<ByTaskMentorRVitem>()
    private var testDataSet_realTime_mentee = mutableListOf<RealTimeMenteeReviewRVitem>()

    // 직무별 뚝딱멘토
    private var TaskSelectedList = Array<Boolean>(2) {false}
    private var TaskNum = 2
    private var TaskCvList = arrayOfNulls<MaterialCardView>(TaskNum)
    private var TaskTvList = arrayOfNulls<TextView>(TaskNum)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "home fragment onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        setHasOptionsMenu(true)  // fragment에서 toolbar 사용하기 위함
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "home fragment onViewCreated")


        // toolbar 설정!!
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기


        // 배너 이미지 뷰페이저 설정
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimens.xml 파일 안에 크기를 정의해두었다. (200dp)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // dimens.xml 파일이 없으면 생성해야함 (50dp)
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        Log.e("AppTest", "home, screenWidth : ${screenWidth}, pageMarginPx : ${pageMarginPx}, pagerWidth : ${pagerWidth} ")
        //val offsetPx = screenWidth - pageMarginPx - pagerWidth
        val offsetPx = screenWidth - screenWidth * 82 / 100  // 이 비율이 적당

        binding.vp2HomeTopBanner.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.vp2HomeTopBanner.offscreenPageLimit = 1  //
        binding.vp2HomeTopBanner.adapter = BannerVP2Adpater(topBannerImageList)
        binding.vp2HomeTopBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        ///////////////////////////////////////////////////////////////////////////////////

        // Top5 멘토 recycler view  // 멘토 이동 보완하기
        rvAdapter_top5 = Top5MentorRVAdpater(testDataSet_top5,
        selectMentor = {
            Log.e("AppTest","go to mentor Info activity")
            val intent = Intent(context, MentorInfoActivity::class.java)
            startActivity(intent)
        })
        binding.rvTop5Mentor.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Mentor.adapter = rvAdapter_top5
        binding.rvTop5Mentor.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet_top5.apply{ // 임시 데이터
            add(HomeTop5MentorRVitem(1, "리즈", "네이버", "UIUX 디자인",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))

            add(HomeTop5MentorRVitem(1, "제이슨", "카카오", "앱 개발",
                    "#이직 #실무 #상담 #UX #면접 #공채 #앱 #이직",
                    2))
            add(HomeTop5MentorRVitem(1, "브라이언", "쿠팡", "UIUX 디자인",
                    "#테스트 #테스트개행테스트띄어쓰기없이" +
                            "",
                    2))
            add(HomeTop5MentorRVitem(1, "제이슨", "네이버", "웹 개발",
                    "#안녕하세요 #안녕 #긴해쉬태그테스트입니다",
                    2))
        }
        rvAdapter_top5.updateList(testDataSet_top5)
        rvAdapter_top5.notifyDataSetChanged()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 직무별 뚝딱멘토
        TaskCvList[0] = binding.cvByTaskDesign
        TaskCvList[1] = binding.cvByTaskIt

        TaskTvList[0] = binding.tvCategoryDesign
        TaskTvList[1] = binding.tvCategoryIt

        // 시작 시 직무별 멘토 - 디자인 분야 선택되어 있는 상태
        selectTask(0)
        for(i in 0..(TaskNum-1)){
            TaskCvList[i]!!.setOnClickListener {
                selectTask(i)

                // 뷰모델 연동하기, 리사이클러뷰 변화
            }
        }

        rvAdapter_byTask = ByTaskMentorRVAdpater(testDataSet_byTask)
        binding.rvByTaskMentor.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvByTaskMentor.adapter = rvAdapter_byTask
        binding.rvByTaskMentor.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet_byTask.apply {
            add(ByTaskMentorRVitem(1, "제인", "네이버웹툰", "서버",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "에스", "카카오", "앱",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "디모", "라인", "웹",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "애니", "삼성", "디자인",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "한", "쿠팡", "앱",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "제인", "네이버웹툰", "서버",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "제인", "네이버웹툰", "서버",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
            add(ByTaskMentorRVitem(1, "제인", "네이버웹툰", "서버",
                    "#실무 #노하우 #업무체계 #진로고민 #대기업 #GUI #피드백",
                    2))
        }
        rvAdapter_byTask.updateList(testDataSet_byTask)
        rvAdapter_byTask.notifyDataSetChanged()

        //////////////////////////////////////////////////////////////////
        // 실시간 멘티 후기 RV
        rvAdapter_realTime_mentee = RealTimeMenteeReviewRVAdpater(testDataSet_realTime_mentee)
        binding.rvRealtimeMenteeReview.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvRealtimeMenteeReview.adapter = rvAdapter_realTime_mentee
        binding.rvRealtimeMenteeReview.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet_realTime_mentee.apply{ // 임시 데이터
            add(RealTimeMenteeReviewRVitem(1, "리즈", "네이버", "UIUX 디자인",
                    5,"애니","2021. 10. 12",
                    "궁금했던 내용을 실무 경험으로 얘기해주셔서 너무 도움이 되었습니다. 포트폴리오를 만들려고 하니까 막막했는데 한 멘토님이 어떤 방법으로 제작해야 할지 방향을 잡아주셔서 좋았습니다.",0
                    ))
            add(RealTimeMenteeReviewRVitem(1, "제임스", "쿠팡", "앱",
                    4,"에스","2021. 10. 12", "리뷰 테스트 한 줄 넘어가는 테스트",0
            ))
            add(RealTimeMenteeReviewRVitem(1, "킴", "네이버", "데이터",
                    1,"제이슨","2021. 10. 12",
                    "리뷰 테스트 리뷰 테스트 긴 문장 테스트 입니다 뚝딱뚝딱뚝딱뚝딱뚝딱 뚝딱뚝딱뚝딱",0
            ))
            add(RealTimeMenteeReviewRVitem(1, "브라이언", "삼성전자", "UIUX 디자인",
                    2,"존","2021. 10. 12", "리뷰 테스트",0
            ))
        }
        rvAdapter_realTime_mentee.updateList(testDataSet_realTime_mentee)
        rvAdapter_realTime_mentee.notifyDataSetChanged()



        // 직무별 뚝딱멘토 전체보기
        binding.tvWatchAllByTask.setOnClickListener {
            Log.e("AppTest","go to viewall bytask activity")
            val intent = Intent(context, ViewAllByTaskActivity::class.java)
            startActivity(intent)
        }

        // 멘토 실시간 후기 전체보기
        binding.tvWatchAllRealtimeMenteeReview.setOnClickListener {
            Log.e("AppTest","go to viewall bytask activity")
            val intent = Intent(context, ViewAllMenteeReviewActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()

        Constants.BOTTOM_NAVI_NUM = 0
        Log.e("AppTest", "home fragment onResume, Bottom navi num : ${Constants.BOTTOM_NAVI_NUM}")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu3, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_notification) {
            Log.e("AppTest","home fragment actionbar bell icon clicked")
        }
        return super.onOptionsItemSelected(item)
    }

    /// 직무별 뚝딱멘토 - 직무 선택 시
    fun selectTask(taskNum : Int){
        for(i in 0..(TaskNum-1)){
            TaskCvList[i]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
            TaskTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
            TaskSelectedList[i] = false
        }

        TaskCvList[taskNum]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
        TaskTvList[taskNum]!!.setTextColor(resources.getColor(R.color.white))
        TaskSelectedList[taskNum] = true

    }

}