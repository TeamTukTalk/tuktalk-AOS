package com.example.tuktalk.presentation.mypage.mentor.mentorRegist

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMentorRegistBinding
import com.example.tuktalk.presentation.signup.info.breakaway.BreakAwayDialogFragment


class MentorRegistActivity: AppCompatActivity() {

    private val viewModel : MentorRegistViewModel by viewModels()

    val step1Fragment = MentorRegistStep1Fragment()
    val step2Fragment = MentorRegistStep2Fragment()
    val step3Fragment = MentorRegistStep3Fragment()

    var REGIST_STEP = 1

    private lateinit var binding : ActivityMentorRegistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest","mentor regist activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMentorRegistBinding>(this, R.layout.activity_mentor_regist)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("멘토등록")


        // 시작은 step1
        supportFragmentManager.beginTransaction()
                .apply {
                    REGIST_STEP = 1
                    replace(R.id.framelayout_mentor_regist_step, step1Fragment)
                    commit()
                }

    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_close ->{
                // 닫기 버튼 누를 시
                Log.e("AppTest", "toolbar close btn clicked")

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

    fun goToStep1(){
        binding.ivStep2.setImageResource(R.drawable.ic_step2_off)
        binding.tvStep2.setTextColor(resources.getColor(R.color.tuktalk_sub_content_4))

        supportFragmentManager.beginTransaction()
                .apply {
                    REGIST_STEP = 1
                    replace(R.id.framelayout_mentor_regist_step, step1Fragment)
                    commit()
                }
    }


    fun goToStep2(){
        binding.ivStep2.setImageResource(R.drawable.ic_step2_on)
        binding.tvStep2.setTextColor(resources.getColor(R.color.tuktalk_primary))
        binding.ivStep3.setImageResource(R.drawable.ic_step3_off)
        binding.tvStep3.setTextColor(resources.getColor(R.color.tuktalk_sub_content_4))

        supportFragmentManager.beginTransaction()
                .apply {
                    REGIST_STEP = 2
                    replace(R.id.framelayout_mentor_regist_step, step2Fragment)
                    commit()
                }
    }

    fun goToStep3(){
        binding.ivStep3.setImageResource(R.drawable.ic_step3_on)
        binding.tvStep3.setTextColor(resources.getColor(R.color.tuktalk_primary))

        supportFragmentManager.beginTransaction()
                .apply {
                    REGIST_STEP = 3
                    replace(R.id.framelayout_mentor_regist_step, step3Fragment)
                    commit()
                }
    }

    override fun onBackPressed() {
        if(REGIST_STEP == 1) {
            super.onBackPressed()
        }
        if(REGIST_STEP == 2){
            goToStep1()
        }
        if(REGIST_STEP == 3){
            goToStep2()
        }
    }
}