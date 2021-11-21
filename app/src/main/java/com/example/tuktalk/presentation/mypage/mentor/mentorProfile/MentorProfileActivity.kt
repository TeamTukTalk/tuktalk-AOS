package com.example.tuktalk.presentation.mypage.mentor.mentorProfile

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ActivityMentorProfileBinding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.*
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistStep1Fragment
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistStep2Fragment
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistStep3Fragment

// 멘토 프로필 등록 activity
class MentorProfileActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMentorProfileBinding

    var PROFILE_STEP = 1

    val step1Fragment = MentorProfileStep1Fragment()
    val step2Fragment = MentorProfileStep2Fragment()
    val step3Fragment = MentorProfileStep3Fragment()
    val step4Fragment = MentorProfileStep4Fragment()
    val step5Fragment = MentorProfileStep5Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","mentor profile activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMentorProfileBinding>(this, R.layout.activity_mentor_profile)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("프로필 등록/수정")
        /////////////////////


        // 첫 시작은 step1 !!
        var fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentByTag("mp_step1") != null) {
            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("mp_step1")!!).commit()
        } else {
            fragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step1Fragment, "mp_step1").commit()
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


    /// step 별 이동
    fun goToStep1(){
        if (supportFragmentManager.findFragmentByTag("mp_step1") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mp_step1")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step1Fragment, "mp_step1").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mp_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step5")!!).commit()
        }

        PROFILE_STEP = 1
    }

    fun goToStep2(){
        if (supportFragmentManager.findFragmentByTag("mp_step2") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mp_step2")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step2Fragment, "mp_step2").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mp_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step5")!!).commit()
        }

        PROFILE_STEP = 2
    }

    fun goToStep3(){
        if (supportFragmentManager.findFragmentByTag("mp_step3") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mp_step3")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step3Fragment, "mp_step3").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mp_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step5")!!).commit()
        }

        PROFILE_STEP = 3
    }

    fun goToStep4(){
        if (supportFragmentManager.findFragmentByTag("mp_step4") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mp_step4")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step4Fragment, "mp_step4").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mp_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step5")!!).commit()
        }

        PROFILE_STEP = 4
    }

    fun goToStep5(){
        if (supportFragmentManager.findFragmentByTag("mp_step5") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("mp_step5")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_profile_regist, step5Fragment, "mp_step5").commit()
        }

        if (supportFragmentManager.findFragmentByTag("mp_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("mp_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("mp_step4")!!).commit()
        }

        PROFILE_STEP = 5
    }
}