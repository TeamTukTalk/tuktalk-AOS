package com.example.tuktalk.presentation.guide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityGuideBinding
import com.example.tuktalk.presentation.guide.adapter.PagerFragmentStateAdapter
import com.example.tuktalk.presentation.login.LoginActivity

class GuideActivity: AppCompatActivity() {

    // 안내 화면 activity
    // 1. view pager를 통해 안내 화면 3개 보여주기
    // 2. 각 화면에 맞게 circle pager indicator 나타내기
    // 3. 세 번째 화면에서는 로그인 activity로 넘어가기 위한 버튼 활성화 시키기!!

    private lateinit var binding : ActivityGuideBinding

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = PagerFragmentStateAdapter(this)

        pagerAdapter.addFragment(Guide1Fragment())
        pagerAdapter.addFragment(Guide2Fragment())
        pagerAdapter.addFragment(Guide3Fragment())

        viewPager = binding.viewpager2
        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(position == 0){
                    binding.btnStart.visibility = View.INVISIBLE
                    binding.ivIndicator1.setImageResource(R.drawable.indicator_circle_purple)
                    binding.ivIndicator2.setImageResource(R.drawable.indicator_circle_gray)
                    binding.ivIndicator3.setImageResource(R.drawable.indicator_circle_gray)
                }
                else if(position == 1){
                    binding.btnStart.visibility = View.INVISIBLE
                    binding.ivIndicator1.setImageResource(R.drawable.indicator_circle_gray)
                    binding.ivIndicator2.setImageResource(R.drawable.indicator_circle_purple)
                    binding.ivIndicator3.setImageResource(R.drawable.indicator_circle_gray)
                }
                else{
                    binding.btnStart.visibility = View.VISIBLE
                    binding.ivIndicator1.setImageResource(R.drawable.indicator_circle_gray)
                    binding.ivIndicator2.setImageResource(R.drawable.indicator_circle_gray)
                    binding.ivIndicator3.setImageResource(R.drawable.indicator_circle_purple)
                }

                Log.e("AppTest", "Page ${position+1}")
            }
        })


        // 시작하기 버튼 클릭 시 로그인 화면으로 이동
        binding.btnStart.setOnClickListener {
            Log.e("AppTest", "go to login activity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)



            // 로그인 화면 넘어가면 안내페이지로 다시 돌아가지 못하게 설정하기??
            // 현재는 갈 수 있음!!!
        }

    }


}