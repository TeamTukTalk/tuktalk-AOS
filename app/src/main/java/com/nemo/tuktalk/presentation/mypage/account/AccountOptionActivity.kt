package com.nemo.tuktalk.presentation.mypage.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityAccountOptionBinding
import com.nemo.tuktalk.databinding.ActivityMentorServiceBinding
import com.nemo.tuktalk.presentation.login.LoginActivity
import com.nemo.tuktalk.presentation.main.MainActivity
import com.nemo.tuktalk.presentation.mypage.account.basicinfo.ActivityUserBasicInfo

class AccountOptionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "AccountOptionActivity/ mentor service activity onCreate")

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
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)  // 로그인 화면 이동
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)  //
            startActivity(intent)
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

}