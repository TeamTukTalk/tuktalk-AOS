package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorRegist.MentorRegistViewModel
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
            (activity as MentorProfileActivity).goToStep2()
        }



    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step1 fragment onResume")
    }


}