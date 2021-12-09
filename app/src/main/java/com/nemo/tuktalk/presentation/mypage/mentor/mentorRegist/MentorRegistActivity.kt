package com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityMentorRegistBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MentorRegistActivity: AppCompatActivity() {

    private val viewModel : MentorRegistViewModel by viewModel()

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

        //////////////////////////////////////

        // 시작은 step1
        /*supportFragmentManager.beginTransaction()
                .apply {
                    REGIST_STEP = 1
                    replace(R.id.framelayout_mentor_regist_step, step1Fragment)
                    commit()
                }*/

        // 첫 시작은 step1 !!
        var fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentByTag("mentor_regist_step1") != null) {
            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("mentor_regist_step1")!!).commit()
        } else {
            fragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_step, step1Fragment, "mentor_regist_step1").commit()
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
                // 우측 상단 뒤로가기 버튼 누를 시
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

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step1") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mentor_regist_step1")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_step, step1Fragment, "mentor_regist_step1").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mentor_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step3")!!).commit()
        }
    }


    fun goToStep2(){
        REGIST_STEP = 2

        binding.ivStep2.setImageResource(R.drawable.ic_step2_on)
        binding.tvStep2.setTextColor(resources.getColor(R.color.tuktalk_primary))
        binding.ivStep3.setImageResource(R.drawable.ic_step3_off)
        binding.tvStep3.setTextColor(resources.getColor(R.color.tuktalk_sub_content_4))

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step2") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mentor_regist_step2")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_step, step2Fragment, "mentor_regist_step2").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mentor_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step3")!!).commit()
        }
    }

    fun goToStep3(){
        REGIST_STEP = 3

        binding.ivStep3.setImageResource(R.drawable.ic_step3_on)
        binding.tvStep3.setTextColor(resources.getColor(R.color.tuktalk_primary))

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step3") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mentor_regist_step3")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_step, step3Fragment, "mentor_regist_step3").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mentor_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mentor_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mentor_regist_step2")!!).commit()
        }
    }


    // 뒤로가기 버튼 커스텀
    override fun onBackPressed() {
        if(REGIST_STEP == 1) {
            super.onBackPressed()
        }
        if(REGIST_STEP == 2){
            goToStep1()
        }
        if(REGIST_STEP == 3){
            // 등록 완료 화면에서는 뒤로가기 작동x

        }
    }
}