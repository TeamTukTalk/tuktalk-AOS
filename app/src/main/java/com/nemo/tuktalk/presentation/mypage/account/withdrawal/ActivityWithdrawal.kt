package com.nemo.tuktalk.presentation.mypage.account.withdrawal

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.ActivityAccountOptionBinding
import com.nemo.tuktalk.databinding.ActivityUserBasicInfoBinding
import com.nemo.tuktalk.databinding.ActivityWithdrawalBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.MentorInfoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ActivityWithdrawal: AppCompatActivity() {

    private lateinit var binding : ActivityWithdrawalBinding
    private val viewModel : WithdrawalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "ActivityWithdrawal/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityWithdrawalBinding>(this, R.layout.activity_withdrawal)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) //
        supportActionBar!!.setTitle("회원 탈퇴")
        ///////////////////////////////

        // 액티비티 생성 시 유의사항 체크 = false
        viewModel.IsChecked.value = false

        // 닉네임 가져와서 상단 질문문구 설정하기
        binding.tvQuestionDes.text = Constants.USER_NICKNAME + "님\n정말 탈퇴하시겠어요?"


        // 멘토 / 멘티에 따라 텍스트 문구 변경
        if(Constants.USER_MODE == 0){  // 멘토 모드인 경우
            binding.tvDes1.text = getString(R.string.tv_withdrawal_mentor_1)
        }
        else{ // 멘티 모드인 경우
            binding.tvDes1.text = getString(R.string.tv_withdrawal_mentee_1)
        }

        // 이용약관 체크 시
        binding.ivCheck.setOnClickListener {
            // 체크 시
            if(viewModel.IsChecked.value == true)
                viewModel.IsChecked.value = false
            else
                viewModel.IsChecked.value = true
        }

        viewModel.IsChecked.observe(this, {
            if(it){
                binding.btnWithdrawalActive.visibility = View.VISIBLE
                binding.btnWithdrawalUnactive.visibility = View.INVISIBLE
                binding.ivCheck.setImageResource(R.drawable.ic_checkbox_on)
            }
            else{
                binding.btnWithdrawalActive.visibility = View.INVISIBLE
                binding.btnWithdrawalUnactive.visibility = View.VISIBLE
                binding.ivCheck.setImageResource(R.drawable.ic_checkbox_off)
            }
        })

        /////////////
        // 탈퇴 버튼 누를 시 -> 탈퇴 api 연동하기
        binding.btnWithdrawalActive.setOnClickListener {
            Log.e("AppTest", "ActivityWithdrawal/ click withdrawal button")



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