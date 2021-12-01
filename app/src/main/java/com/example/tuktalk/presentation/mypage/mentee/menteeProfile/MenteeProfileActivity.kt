package com.example.tuktalk.presentation.mypage.mentee.menteeProfile

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.ActivityMenteeProfileBinding
import com.example.tuktalk.databinding.ActivityMentorProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MenteeProfileActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMenteeProfileBinding
    private val viewModel : MenteeProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","mentee profile activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMenteeProfileBinding>(this, R.layout.activity_mentee_profile)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("프로필 등록/수정")
        /////////////////////


        // 프로필 배경 & 닉네임 첫 글자 설정
        binding.tvProfileFirstLetter.text = Constants.USER_FIRST_LETTER
        setProfileBackGroundColor()


        // 닉네임 수정 시 card view 테두리 색상
        binding.etMenteeNickname.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et one introduce focused")
                binding.cvMenteeNicknam.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvMenteeNicknam.invalidate()
            }
            else{
                Log.e("AppTest","et one introduce  focus x")
                binding.cvMenteeNicknam.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvMenteeNicknam.invalidate()
            }
        })


    }

    // toolbar -> 현재 뒤로가기만 활성화
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // "profileBlue","profileRed", "profileYellow", "profileGray", "profileGreen"
    fun setProfileBackGroundColor(){
        when(Constants.USER_PROFILE_IMAGE_COLOR){
            "profileBlue" -> {
                binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_blue)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileBlue_text))
            }
            "profileRed"->{
                binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_red)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileRed_text))
            }
            "profileYellow"->{
                binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_yellow)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileYellow_text))
            }
            "profileGray"->{
                binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_gray)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileGray_text))
            }
            "profileGreen"->{
                binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_green)
                binding.tvProfileFirstLetter.setTextColor(resources.getColor(R.color.tuktalk_profileGreen_text))
            }
        }
    }
}