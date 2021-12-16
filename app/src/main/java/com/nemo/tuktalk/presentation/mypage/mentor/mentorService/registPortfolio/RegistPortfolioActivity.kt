package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ActivityMentorRegistPortfolioBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step.*
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistPortfolioActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMentorRegistPortfolioBinding
    private val viewModel : RegistPortfolioViewModel by viewModel()

    var PORTFOLIO_STEP = 1
    private var progressPosition = 1

    val step1Fragment = RegistPortfolioStep1Fragment()
    val step2Fragment = RegistPortfolioStep2Fragment()
    val step3Fragment = RegistPortfolioStep3Fragment()
    val step4Fragment = RegistPortfolioStep4Fragment()
    val step5Fragment = RegistPortfolioStep5Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMentorRegistPortfolioBinding>(this, R.layout.activity_mentor_regist_portfolio)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("포트폴리오 등록")
        /////////////////////


        // 첫 시작은 step1 !!
        var fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        } else {
            fragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step1Fragment, "portfolio_regist_step1").commit()
        }



    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 좌측 상단 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //////////////////// step 이동 ///////////////////////
    /// step 별 이동
    fun goToStep1(){
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step1Fragment, "portfolio_regist_step1").commit()
        }

        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step5")!!).commit()
        }


        PORTFOLIO_STEP = 1
        animateProgress(1)
    }

    fun goToStep2(){
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step2") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("portfolio_regist_step2")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step2Fragment, "portfolio_regist_step2").commit()
        }

        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step5")!!).commit()
        }

        PORTFOLIO_STEP = 2
        animateProgress(2)
    }

    fun goToStep3(){
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step3") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("portfolio_regist_step3")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step3Fragment, "portfolio_regist_step3").commit()
        }

        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step4")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step5")!!).commit()
        }


        PORTFOLIO_STEP = 3
        animateProgress(3)
    }

    fun goToStep4(){
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step4") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("portfolio_regist_step4")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step4Fragment, "portfolio_regist_step4").commit()
        }

        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step5") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step5")!!).commit()
        }

        binding.llProgress.visibility = View.VISIBLE
        PORTFOLIO_STEP = 4
        animateProgress(4)
    }

    fun goToStep5(){
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step5") != null) {
            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("portfolio_regist_step5")!!).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.framelayout_mentor_regist_portfolio, step5Fragment, "portfolio_regist_step5").commit()
        }

        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step1") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step1")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step2") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step2")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step3") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step3")!!).commit()
        }
        if (supportFragmentManager.findFragmentByTag("portfolio_regist_step4") != null) {
            supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("portfolio_regist_step4")!!).commit()
        }

        binding.llProgress.visibility = View.INVISIBLE
        PORTFOLIO_STEP = 5

    }

    // 상단의 프로필 등록 step 별 진행도 애니메이션
    fun animateProgress(position: Int) {
        val coloredWidthProgressAnimator: ValueAnimator = ValueAnimator.ofFloat(
                (PROGRESS_WEIGHT_STEP * progressPosition),
                (PROGRESS_WEIGHT_STEP * (position))
        ).apply {
            duration = 300
            addUpdateListener {
                val value: Float = it.animatedValue as Float
                (binding.viewProgressColored.layoutParams as (LinearLayout.LayoutParams)).weight = value
                binding.viewProgressColored.requestLayout()
            }
        }
        coloredWidthProgressAnimator.start()
        progressPosition = (position)
    }

    companion object {
        const val PROGRESS_WEIGHT_STEP = 0.1f
    }


    override fun onBackPressed() {

        if(PORTFOLIO_STEP == 1) {
            super.onBackPressed()
        }
        if(PORTFOLIO_STEP == 2){
            goToStep1()
        }
        if(PORTFOLIO_STEP == 3){
            goToStep2()
        }
        if(PORTFOLIO_STEP == 4){
            goToStep3()
        }
        if(PORTFOLIO_STEP == 5){  // 뒤로가기 막기
            //goToStep4()
        }

    }


}