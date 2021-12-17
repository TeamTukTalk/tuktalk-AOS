package com.nemo.tuktalk.presentation.mypage.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityAccountOptionBinding
import com.nemo.tuktalk.databinding.ActivityMentorServiceBinding
import com.nemo.tuktalk.presentation.login.LoginActivity
import com.nemo.tuktalk.presentation.main.MainActivity
import com.nemo.tuktalk.presentation.main.MainActivityViewModel
import com.nemo.tuktalk.presentation.mypage.account.basicinfo.ActivityUserBasicInfo
import com.nemo.tuktalk.presentation.mypage.account.withdrawal.ActivityWithdrawal
import com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import org.koin.android.viewmodel.ext.android.viewModel

class AccountOptionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountOptionBinding
    private val viewModel : AccountOptionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "AccountOptionActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityAccountOptionBinding>(this, R.layout.activity_account_option)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) //
        supportActionBar!!.setTitle("계정 설정")
        ///////////////////////////////

        // 현재 유저 모드에 따라 텍스트 변경
        if(Constants.USER_MODE == 0){
            binding.tvUserModeChange.text = "멘티로 전환하기"
        }
        else{
            binding.tvUserModeChange.text = "멘토로 전환하기"
        }

        // 기본 정보 이동
        binding.llBasicInfo.setOnClickListener {
            val intent = Intent(this, ActivityUserBasicInfo::class.java)  // 기본 정보 화면 이동
            startActivity(intent)
        }

        // 로그아웃 처리
        binding.llLogout.setOnClickListener {

            Constants.USER_MENTOR_ID = 0  // 로그아웃 시 초기화 해주기!!!!
            Constants.USER_MENTEE_ID = 0
            Constants.CURRENT_MENTOR_ID = 0


            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)  // 로그인 화면 이동
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)  //
            startActivity(intent)
        }
        
        // 회원 탈퇴 이동
        binding.llWithdrawal.setOnClickListener {
            val intent = Intent(this, ActivityWithdrawal::class.java)  // 회원 탈퇴 화면 이동
            startActivity(intent)
        }


        // 역할 전환 시
        binding.llChangeMode.setOnClickListener {

            if(Constants.USER_MODE == 0){ // 멘토 -> 멘티
                Log.e("AppTest", "AccountOptionActivity/ 멘토 -> 멘티 전환")
                Toast.makeText(this, "멘티 화면으로 전환되었습니다.", Toast.LENGTH_SHORT).show()
                Constants.USER_MODE = 1  // 유저 모드 값 변경
                finish()
            }
            else{ // 멘티 -> 멘토

                // 기업 메일 인증 되었는지 먼저 확인하기 !!
                viewModel.checkMentorCertified()
            }

        }


        ////// 인증 여부 확인 통신 성공 시
        viewModel.isCertificationCheckSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "AccountOptionActivity/ 인증 여부 확인 성공")

                if(Constants.IS_CERTIFIED_MENTOR){  // 인증 상태 : 멘티 -> 멘토 변경하기

                    // 멘티 -> 멘토 api 호출
                    viewModel.menteeToMentor()

                }
                else{ // 미인증 상태 -> 기업 메일 인증 화면으로 이동
                    Toast.makeText(this, "기업 메일 인증이 필요합니다.", Toast.LENGTH_SHORT).show()

                    Log.e("AppTest","AccountOptionActivity/ go to mentor regist activity")
                    val intent = Intent(this, MentorRegistActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            else{
                Log.e("AppTest", "AccountOptionActivity/ 인증 여부 확인 실패")
                Toast.makeText(this, "기업메일 인증 여부 확인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })


        ///////////////////////////////////

        // 멘티 -> 멘토 api 결과
        viewModel.isMenteeToMentorSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "AccountOptionActivity/ 멘티 -> 멘토 전환 성공")
                Toast.makeText(this, "멘토 화면으로 전환되었습니다.", Toast.LENGTH_SHORT).show()
                Constants.USER_MODE = 0  // 유저 모드 값 변경
                finish()
            }
            else{
                Log.e("AppTest", "AccountOptionActivity/ 멘티 -> 멘토 전환 실패")
            }
        })


        viewModel.progressBarVisibility.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        viewModel.progressBarVisibility_change.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })


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

}