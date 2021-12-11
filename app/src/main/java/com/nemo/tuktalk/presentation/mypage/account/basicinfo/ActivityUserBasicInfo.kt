package com.nemo.tuktalk.presentation.mypage.account.basicinfo

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityAccountOptionBinding
import com.nemo.tuktalk.databinding.ActivityUserBasicInfoBinding

class ActivityUserBasicInfo: AppCompatActivity() {

    private lateinit var binding : ActivityUserBasicInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "ActivityUserBasicInfo/ mentor service activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityUserBasicInfoBinding>(this, R.layout.activity_user_basic_info)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) //
        supportActionBar!!.setTitle("기본정보")
        ///////////////////////////////

        // 유저 닉네임 가져오기
        binding.etNickname.setText(Constants.USER_NICKNAME)

        // 유저 이메일 가져오기
        binding.etEmail.setText(Constants.USER_EMAIL)

        // 저장 버튼 누를 시
        binding.btnSaveActive.setOnClickListener {
            Log.e("AppTest", "ActivityUserBasicInfo/ save btn clicked")

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