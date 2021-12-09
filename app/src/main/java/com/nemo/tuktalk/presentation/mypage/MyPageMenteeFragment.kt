package com.nemo.tuktalk.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMypageMenteeLaunchingVerBinding
import com.nemo.tuktalk.presentation.mypage.mentee.menteeProfile.MenteeProfileActivity
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.MenteeRecentPortfolioActivity

class MyPageMenteeFragment: Fragment() {

    private lateinit var binding : FragmentMypageMenteeLaunchingVerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentee fragment launching ver onCreateView")

        // 런칭 ver
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_mentee_launching_ver, container, false)

        setHasOptionsMenu(true)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentee fragment launching ver onViewCreated")

        // 닉네임 받아오고 상단 닉네임 텍스트에 할당
        binding.tvName.text = Constants.USER_NICKNAME + "님"

        // 프로필 배경 & 닉네임 첫 글자 설정
        binding.tvProfileFirstLetter.text = Constants.USER_FIRST_LETTER
        setProfileBackGroundColor()

        // toolbar 설정!!
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기


        // 멘티 프로필 등록/수정 이동
        binding.clProfile.setOnClickListener {
            Log.e("AppTest","MyPageMenteeFragment/ go to mentee profile activity")
            val intent = Intent(context, MenteeProfileActivity::class.java)
            startActivity(intent)
        }
        binding.ivProfileEdit.setOnClickListener {
            Log.e("AppTest","MyPageMenteeFragment/ go to mentee profile activity")
            val intent = Intent(context, MenteeProfileActivity::class.java)
            startActivity(intent)
        }


        // 최근 본 포트폴리오로 이동
        binding.llGotoRecentPortfolio.setOnClickListener {
            Log.e("AppTest","MyPageMenteeFragment/ go to mentee recent portfolio activity")
            val intent = Intent(context, MenteeRecentPortfolioActivity::class.java)
            startActivity(intent)
        }
        binding.ivGotoRecentPortfolio.setOnClickListener {
            Log.e("AppTest","MyPageMenteeFragment/ go to mentee recent portfolio activity")
            val intent = Intent(context, MenteeRecentPortfolioActivity::class.java)
            startActivity(intent)
        }
        ///////////////////////////////////////////////////////////


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu2, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_option) {
            Log.e("AppTest","fragment actionbar option icon clicked")
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