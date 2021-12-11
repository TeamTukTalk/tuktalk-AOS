package com.nemo.tuktalk.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMypageMentorLaunchingVerBinding
import com.nemo.tuktalk.presentation.mypage.account.AccountOptionActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.MentorServiceActivity

class MyPageMentorFragment: Fragment() {

    //private lateinit var binding : FragmentMypageMentorBinding
    // 런칭 버전
    private lateinit var binding : FragmentMypageMentorLaunchingVerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.e("AppTest", "mentor fragment onCreateView")

        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_mentor, container, false)
        // 런칭 버전
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_mentor_launching_ver, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentor fragment onViewCreated")

        // 모드 전환 액티비티로 이동
       /* binding.btnMentor.setOnClickListener {
            Log.e("AppTest","go to test2 activity")
            val intent = Intent(context, Test2Activity::class.java)
            startActivity(intent)
        }*/

        // 닉네임 받아오고 상단 닉네임 텍스트에 할당
        binding.tvName.text = Constants.USER_NICKNAME + "님"

        // 프로필 배경 & 닉네임 첫 글자 설정
        binding.tvProfileFirstLetter.text = Constants.USER_FIRST_LETTER
        setProfileBackGroundColor()


        // 멘토 등록 액티비티 이동
       binding.cvGotoMentorRegist.setOnClickListener {
            Log.e("AppTest","go to mentor regist activity")
            val intent = Intent(context, MentorRegistActivity::class.java)
            startActivity(intent)
        }

        // toolbar 설정!!
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기


        /// 프로필 이미지, 편집 이미지 클릭 시 -> 프로필 등록/수정 이동
        binding.clProfile.setOnClickListener {

            if(Constants.IS_CERTIFIED_MENTOR){
                Log.e("AppTest","MyPageMentorFragment/ go to mentor profile activity")
                val intent = Intent(context, MentorProfileActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "멘토 기업 이메일 인증을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivProfileEdit.setOnClickListener {
            if(Constants.IS_CERTIFIED_MENTOR){
                Log.e("AppTest","MyPageMentorFragment/ go to mentor profile activity")
                val intent = Intent(context, MentorProfileActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "멘토 기업 이메일 인증을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        //////////////////////////////////////////////////////

        // 나의 서비스 로 이동
        binding.llGotoMyservice.setOnClickListener {
            Log.e("AppTest","MyPageMentorFragment/ go to mentor service activity")
            val intent = Intent(context, MentorServiceActivity::class.java)
            startActivity(intent)
        }
        binding.ivGotoMyservice.setOnClickListener {
            Log.e("AppTest","MyPageMentorFragment/ go to mentor service activity")
            val intent = Intent(context, MentorServiceActivity::class.java)
            startActivity(intent)
        }
        ///////////////////////////////////////////////////////////

        // 계정 설정 이동
        val llGotoAccountOtion : LinearLayout = view.findViewById(R.id.ll_goto_account_option)
        llGotoAccountOtion.setOnClickListener {
            Log.e("AppTest","MyPageMentorFragment/ go to account option activity")
            val intent = Intent(context, AccountOptionActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor fragment onResume")
        Log.e("AppTest", "mentor certified : ${Constants.IS_CERTIFIED_MENTOR}")

        if(Constants.IS_CERTIFIED_MENTOR){   // 멘토 기업인증 했으면
            binding.cvGotoMentorRegist.visibility = View.GONE
            binding.view6.visibility = View.GONE
            binding.view7.visibility = View.GONE
        }
        else{  // 기업인증 아직 하지않은 상태라면
            binding.view6.visibility = View.VISIBLE
            binding.cvGotoMentorRegist.visibility = View.VISIBLE
            binding.view7.visibility = View.VISIBLE
        }

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