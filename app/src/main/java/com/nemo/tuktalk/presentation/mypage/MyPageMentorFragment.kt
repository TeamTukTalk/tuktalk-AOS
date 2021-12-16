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
import com.nemo.tuktalk.presentation.home.HomeViewModel
import com.nemo.tuktalk.presentation.mypage.account.AccountOptionActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorService.MentorServiceActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MyPageMentorFragment: Fragment() {

    //private lateinit var binding : FragmentMypageMentorBinding
    // 런칭 버전
    private lateinit var binding : FragmentMypageMentorLaunchingVerBinding
    private val viewModel: MyPageMentorViewModel by viewModel()

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
        binding.tvName.text = Constants.USER_NICKNAME + "님".replace(" ", "\u00A0")

        // 프로필 배경 & 닉네임 첫 글자 설정
        binding.tvProfileFirstLetter.text = Constants.USER_FIRST_LETTER
        setProfileBackGroundColor()


        // 멘토 등록 액티비티 이동
       binding.cvGotoMentorRegist.setOnClickListener {
            Log.e("AppTest","go to mentor regist activity")
            val intent = Intent(context, MentorRegistActivity::class.java)
            startActivity(intent)
        }

        // 멘토 / 회사 인증,   = 멘토 등록 액티비티 이동
        val llGotoMentorResgist : LinearLayout = view.findViewById(R.id.ll_goto_certificate_company)
        llGotoMentorResgist.setOnClickListener {
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


        //////////////////////////////////////////////////////

        // 등록된 포트폴리오 있는지
        viewModel.IsGetMentorPortfolioInfoSuccess.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "MyPageMentorFragment/ 포트폴리오 정보 조회 성공")
                if(viewModel.IsPortfolioRegistered){
                    Log.e("AppTest", "MyPageMentorFragment/ 포트폴리오 등록 상태입니다")

                    binding.clNoPortfolio.visibility = View.GONE
                    binding.clYesPorfolio.visibility = View.VISIBLE

                    binding.tvDate.text = viewModel.PF_CREATED_TIME
                    binding.tvPortfolioDescription.text = viewModel.PF_DESCRIPTION

                }
                else{
                    Log.e("AppTest", "MyPageMentorFragment/ 포트폴리오 미등록 상태입니다")
                    binding.clNoPortfolio.visibility = View.VISIBLE
                    binding.clYesPorfolio.visibility = View.GONE

                }
            }
            else{
                Log.e("AppTest", "MyPageMentorFragment/ 포트폴리오 정보 조회 실패")
            }
        })

        viewModel.ProgressBarVisibility_info.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarInfo.visibility = View.VISIBLE
            else
                binding.loadingProgressBarInfo.visibility = View.INVISIBLE
        })

        ////////////////////////////////////////////////////////////////////////


    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "MyPageMentorFragment/ mentor fragment onResume")
        Log.e("AppTest", "MyPageMentorFragment/ mentor certified : ${Constants.IS_CERTIFIED_MENTOR}")

        if(Constants.IS_CERTIFIED_MENTOR){   // 멘토 기업인증 했으면 기업인증 하라는 문구 없어지고, 등록한 포폴 있는지 조회하기
            binding.cvGotoMentorRegist.visibility = View.GONE
            binding.view6.visibility = View.GONE
            binding.view7.visibility = View.GONE

            // 인증 마크 배경 & 체크 이미지 활성화
            binding.ivMentorCertification.visibility = View.VISIBLE
            binding.ivMentorCertificationCheck.visibility = View.VISIBLE


            // 등록한 포폴있는지 조회
            viewModel.getMentorPortfolioInfo()

        }
        else{  // 기업인증 아직 하지않은 상태라면
            binding.view6.visibility = View.VISIBLE
            binding.cvGotoMentorRegist.visibility = View.VISIBLE
            binding.view7.visibility = View.VISIBLE

            // 인증 마크 배경 & 체크 이미지 비활성화
            binding.ivMentorCertification.visibility = View.INVISIBLE
            binding.ivMentorCertificationCheck.visibility = View.INVISIBLE
            
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu2, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {  // 우측 상단 설정 아이콘 누를 시
        if (item.itemId == R.id.action_option) {
            Log.e("AppTest","fragment actionbar option icon clicked")
            Log.e("AppTest","MyPageMentorFragment/ go to account option activity")
            val intent = Intent(context, AccountOptionActivity::class.java)
            startActivity(intent)
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