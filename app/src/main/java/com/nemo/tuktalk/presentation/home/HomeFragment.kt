package com.nemo.tuktalk.presentation.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.utils.HorizontalItemDecorator
import com.nemo.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.nemo.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.nemo.tuktalk.databinding.FragmentHomeBinding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem
import com.nemo.tuktalk.presentation.home.adapter.ByTaskMentorRVAdpater
import com.nemo.tuktalk.presentation.home.adapter.RealTimeMenteeReviewRVAdpater
import com.nemo.tuktalk.presentation.home.adapter.Top5MentorRVAdpater
import com.nemo.tuktalk.presentation.home.viewAll.ViewAllByTaskActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoActivity
import com.google.android.material.card.MaterialCardView
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem
import com.nemo.tuktalk.presentation.home.viewAll.ViewAllMenteeReviewActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    // 상단 배너 이미지 리스트, 뷰페이저2 와 연결
    private var topBannerImageList = mutableListOf(R.drawable.img_banner_1, R.drawable.img_banner_1, R.drawable.img_banner_1)

    lateinit var rvAdapter_top5: Top5MentorRVAdpater
    lateinit var rvAdapter_byTask : ByTaskMentorRVAdpater

    private var testDataSet_top5 = mutableListOf<Top5MentorResponseDto>()
    private var testDataSet_byTask = mutableListOf<ByTaskMentorResponseDto>()

    // 현재는 실시간 멘티 후기 O
    lateinit var rvAdapter_realTime_mentee : RealTimeMenteeReviewRVAdpater
    private var testDataSet_realTime_mentee = mutableListOf<RealTimeReviewRVitem>()


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


        // 배너 이미지 뷰페이저 설정 -> 현재는 배너 이미지 1개로
       /* val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // dimens.xml 파일 안에 크기를 정의해두었다. (200dp)
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
        binding.vp2HomeTopBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL*/
        ///////////////////////////////////////////////////////////////////////////////////

        // top5 멘토 리스트 통신
        viewModel.getTop5MentorList()

        // Top5 멘토 recycler view  // 멘토 이동 보완하기
        rvAdapter_top5 = Top5MentorRVAdpater(testDataSet_top5,
        selectMentor = { mentorId : Int ->
            Log.e("AppTest","Top5 -> go to mentor Info activity")
            val intent = Intent(context, MentorInfoActivity::class.java)
            intent.putExtra("mentorId", mentorId)
            startActivity(intent)
        })
        binding.rvTop5Mentor.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Mentor.adapter = rvAdapter_top5
        binding.rvTop5Mentor.addItemDecoration(HorizontalItemDecorator(12))

        viewModel.Get_top5_mentorList_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ top5 리스트 조회 성공 -> RV 업데이트")
                rvAdapter_top5.updateList(viewModel.Top5_Mentor_List)
            }
            else{
                Log.e("AppTest", "HomeFragment/ top5 리스트 조회 실패")
            }
        })

        viewModel.progressBarVisibility_top5.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarTop5.visibility = View.VISIBLE
            else
                binding.loadingProgressBarTop5.visibility = View.INVISIBLE
        })




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 직무별 뚝딱멘토
        TaskCvList[0] = binding.cvByTaskDesign
        TaskCvList[1] = binding.cvByTaskIt

        TaskTvList[0] = binding.tvCategoryDesign
        TaskTvList[1] = binding.tvCategoryIt

        // 시작 시 직무별 멘토 - 디자인 분야 선택되어 있는 상태
        selectTask(0)  // selectTask 에서 현재 선택한 speciality로 직무별 멘토 리스트 통신 호출

        for(i in 0..(TaskNum-1)){
            TaskCvList[i]!!.setOnClickListener {
                selectTask(i)
            }
        }

        rvAdapter_byTask = ByTaskMentorRVAdpater(testDataSet_byTask,
                selectMentor = { mentorId : Int ->
                    Log.e("AppTest","byTask -> go to mentor Info activity")
                    val intent = Intent(context, MentorInfoActivity::class.java)
                    intent.putExtra("mentorId", mentorId)
                    startActivity(intent)
                })
        binding.rvByTaskMentor.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvByTaskMentor.adapter = rvAdapter_byTask
        binding.rvByTaskMentor.addItemDecoration(HorizontalItemDecorator(12))


        viewModel.Get_byTask_mentorList_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ 직무별 멘토 리스트 조회 성공 -> RV 업데이트")

                rvAdapter_byTask.updateList(viewModel.ByTask_Mentor_List)
            }
            else{
                Log.e("AppTest", "HomeFragment/ 직무별 멘토 리스트 조회 실패")
            }
        })

        viewModel.progressBarVisibility_byTask.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarByTask.visibility = View.VISIBLE
            else
                binding.loadingProgressBarByTask.visibility = View.INVISIBLE
        })


        //////////////////////////////////////////////////////////////////
        // 실시간 멘티 후기 RV

        // 홈 화면 첫 시작 시 바로 통신
        viewModel.getRealTimeReviewList()

        rvAdapter_realTime_mentee = RealTimeMenteeReviewRVAdpater(testDataSet_realTime_mentee)
        binding.rvRealtimeMenteeReview.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvRealtimeMenteeReview.adapter = rvAdapter_realTime_mentee
        binding.rvRealtimeMenteeReview.addItemDecoration(HorizontalItemDecorator(12))

        viewModel.Get_RealTime_Review_List_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ 실시간 후기 리스트 조회 성공 -> RV 업데이트")

                rvAdapter_realTime_mentee.updateList(viewModel.RealTimeReview_List)
            }
            else{
                Log.e("AppTest", "HomeFragment/ 실시간 후기 리스트 조회 실패")
            }
        })

        viewModel.progressBarVisibility_realTimeReview.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarReview.visibility = View.VISIBLE
            else
                binding.loadingProgressBarReview.visibility = View.INVISIBLE
        })

        ////////////////////////////////////////////////////////////////////

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

    ////  홈 화면 알림 아이콘 현재는 비활성화 해두기
    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu3, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_notification) {
            Log.e("AppTest","home fragment actionbar bell icon clicked")
        }
        return super.onOptionsItemSelected(item)
    }*/

    /// 직무별 뚝딱멘토 - 직무 선택 시 -> viewModel 연결하기
    fun selectTask(taskNum : Int){
        for(i in 0..(TaskNum-1)){
            TaskCvList[i]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_gray_5))
            TaskTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
            TaskSelectedList[i] = false
        }

        TaskCvList[taskNum]!!.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tuktalk_primary))
        TaskTvList[taskNum]!!.setTextColor(resources.getColor(R.color.white))
        TaskSelectedList[taskNum] = true


        var speciality = ""
        if(taskNum == 0)
            speciality = "디자인"
        else
            speciality = "IT/개발"


        // 선택한 speciality로 멘토리스트 가져오기!
        viewModel.getByTaskMentorList(speciality)

    }

}