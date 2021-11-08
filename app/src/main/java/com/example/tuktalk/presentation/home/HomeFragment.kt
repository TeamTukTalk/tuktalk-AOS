package com.example.tuktalk.presentation.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentHomeBinding
import com.example.tuktalk.presentation.home.adapter.BannerVP2Adpater
import com.example.tuktalk.presentation.main.MainActivity

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var callback: OnBackPressedCallback  // 프래그먼트에서 뒤로가기 처리하기 위함


    private var topBannerImageList = mutableListOf(R.drawable.img_banner_1, R.drawable.img_banner_1, R.drawable.img_banner_1)

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
        val offsetPx = screenWidth - screenWidth * 85 / 100

        binding.vp2HomeTopBanner.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }


        binding.vp2HomeTopBanner.offscreenPageLimit = 1
        binding.vp2HomeTopBanner.adapter = BannerVP2Adpater(topBannerImageList)
        binding.vp2HomeTopBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL


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