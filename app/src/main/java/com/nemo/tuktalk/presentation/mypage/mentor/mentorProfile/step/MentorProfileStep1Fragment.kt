package com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep1Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep1Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step1 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로필 배경색 & 닉네임 첫 글자 설정하기
        binding.tvProfileFirstLetter.text = Constants.USER_FIRST_LETTER
        setProfileBackGroundColor()
        //////////////////////////////////////////////////////////////

        // 닉네임은 가져오기
        binding.etNickname.setText(Constants.USER_NICKNAME)  // edittext의 경우 text= 형태가 아닌 setText로 텍스트 값 할당!

        // 한 줄 소개 edit text 활성화 시 cardview 테두리 색상 변경하기
        binding.etOneIntroduce.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et one introduce focused")
                binding.cvOneIntroduce.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvOneIntroduce.invalidate()
            }
            else{
                Log.e("AppTest","et one introduce  focus x")
                binding.cvOneIntroduce.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvOneIntroduce.invalidate()
            }
        })

        // 한 줄 소개 개행 방지
        binding.etOneIntroduce.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(p0: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
                if(keyCode ==  KEYCODE_ENTER)
                    return true
                return false
            }
        })

        // 한 줄 소개 글자수 세기  max:50
        binding.etOneIntroduce.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etOneIntroduce.text.toString()
                binding.tvIntroduceCount.text = userinput.length.toString() + "/50"

                if(userinput.length > 0)
                    viewModel.fillSimpleIntroduce(true)
                else
                    viewModel.fillSimpleIntroduce(false)

                viewModel.SIMPLE_INTRODUCTION = userinput
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        ///////////////////////////////////////////////////////////////

        // 상세소개 edittext 활성화 시
        binding.etIntroduceDetail.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et introduce detail focused")
                binding.cvIntroduceDetail.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvIntroduceDetail.invalidate()
            }
            else{
                Log.e("AppTest","et introduce detail  focus x")
                binding.cvIntroduceDetail.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvIntroduceDetail.invalidate()
            }
        })

        binding.etIntroduceDetail.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etIntroduceDetail.text.toString()
                if(userinput.length > 0)
                    viewModel.fillDeatailedIntroduce(true)
                else
                    viewModel.fillDeatailedIntroduce(false)

                viewModel.DETAILED_INTRODUCTION = userinput
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        /////////////
        // 다음 버튼 활성화
        viewModel.step1Checked.observe(viewLifecycleOwner, {
            if(it){
               binding.btnGotoStep2Active.visibility = View.VISIBLE
               binding.btnGotoStep2Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep2Active.visibility = View.INVISIBLE
                binding.btnGotoStep2Unactive.visibility = View.VISIBLE
            }
        })

        // 다음 버튼 터치 시
        binding.btnGotoStep2Active.setOnClickListener {
            Log.e("AppTest", "btn clicked : step1 -> step2")
            Log.e("AppTest", "simple introduce : ${viewModel.SIMPLE_INTRODUCTION}, " +
                    "detailed introduce : ${viewModel.DETAILED_INTRODUCTION}")
            (activity as MentorProfileActivity).goToStep2()
        }



    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step1 fragment onResume")
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