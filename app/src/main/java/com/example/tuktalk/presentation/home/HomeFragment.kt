package com.example.tuktalk.presentation.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.common.utils.HorizontalItemDecorator
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import com.example.tuktalk.presentation.home.adapter.BannerVP2Adpater
import com.example.tuktalk.presentation.home.adapter.Top5MentorRVAdpater
import com.example.tuktalk.presentation.main.MainActivity
import com.example.tuktalk.presentation.search.adpater.SearchDesignRVadapter

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함

    // 상단 배너 이미지 리스트, 뷰페이저2 와 연결
    private var topBannerImageList = mutableListOf(R.drawable.img_banner_1, R.drawable.img_banner_1, R.drawable.img_banner_1)

    lateinit var rvAdapter: Top5MentorRVAdpater
    private var testDataSet = mutableListOf<HomeTop5MentorRVitem>()

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

        // Top5 멘토 recycler view
        rvAdapter = Top5MentorRVAdpater(testDataSet)
        binding.rvTop5Mentor.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Mentor.adapter = rvAdapter
        binding.rvTop5Mentor.addItemDecoration(HorizontalItemDecorator(12))

        testDataSet.apply{ // 임시 데이터
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
            add(HomeTop5MentorRVitem(1, "제이슨", "네이버", "웹 개발","",
                    2))
        }
        rvAdapter.updateList(testDataSet)
        rvAdapter.notifyDataSetChanged()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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


}