package com.example.tuktalk.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMypageMenteeBinding
import com.example.tuktalk.databinding.FragmentMypageMentorBinding
import com.example.tuktalk.databinding.FragmentMypageMentorLaunchingVerBinding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.example.tuktalk.presentation.signup.SelectRoleActivity

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

        // 닉네임 받아오기
        binding.tvName.text = Constants.USER_NICKNAME + "님"

        // 멘토 등록 액티비티 이동
       binding.cvGotoMentorRegist.setOnClickListener {
            Log.e("AppTest","go to mentor regist activity")
            val intent = Intent(context, MentorRegistActivity::class.java)
            startActivity(intent)
        }

        // toolbar 설정!!
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기
        
        /// 프로필 이미지 클릭 시
        binding.ivProfile.setOnClickListener {
            Log.e("AppTest","go to mentor profile activity")
            val intent = Intent(context, MentorProfileActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()

        Log.e("AppTest", "mentor fragment onResume")
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
}