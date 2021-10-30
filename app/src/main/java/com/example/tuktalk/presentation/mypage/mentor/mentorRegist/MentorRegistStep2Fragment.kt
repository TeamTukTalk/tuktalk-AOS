package com.example.tuktalk.presentation.mypage.mentor.mentorRegist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorRegistStep1Binding
import com.example.tuktalk.databinding.FragmentMentorRegistStep2Binding

class MentorRegistStep2Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistStep2Binding

    // activity의 뷰모델 가져오기
    private val viewModel : MentorRegistViewModel by activityViewModels()

    private var Mentor_Email = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor regist step2 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_step2, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 회사이름, 부서 입력 시 edittext 색상 변경
        binding.etEmail.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et email focused")
                binding.etEmail.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et email focus x")
                binding.etEmail.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_gray1)
            }
        })
        /////////
        binding.etEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var emailText = binding.etEmail.text
                var emailPattern = android.util.Patterns.EMAIL_ADDRESS

                if(emailPattern.matcher(emailText).matches()){
                    binding.btnNextActive.visibility = View.VISIBLE
                    binding.btnNextInactive.visibility = View.INVISIBLE
                }
                else{
                    binding.btnNextActive.visibility = View.INVISIBLE
                    binding.btnNextInactive.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //////////

        binding.btnNextActive.setOnClickListener {
            Mentor_Email = binding.etEmail.text.toString()
            // 발송 클릭
            binding.btnNextActive.visibility = View.INVISIBLE
            binding.btnNextResend.visibility = View.VISIBLE

            viewModel.sendMentorEmail() // 이메일 보내기
        }

        // 인증 통신 성공
        viewModel.isEmailChecked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnNextResend.visibility = View.INVISIBLE
                binding.btnNextCertificationComplete.visibility = View.VISIBLE
                binding.etEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
            }
        })

        binding.btnNextCertificationComplete.setOnClickListener {
            (activity as MentorRegistActivity).goToStep3()
        }
    }
}