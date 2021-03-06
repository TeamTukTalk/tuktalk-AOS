package com.nemo.tuktalk.presentation.mypage.mentor.mentorRegist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.databinding.FragmentMentorRegistStep2Binding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorRegistStep2Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistStep2Binding

    // activity의 뷰모델 가져오기
    private val viewModel : MentorRegistViewModel by sharedViewModel()

    private var Mentor_Email = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor regist step2 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_step2, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentor regist step2 fragment onViewCreated")

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

                    viewModel.MENTOR_EMAIL = emailText.toString() // 인증할 이메일
                }
                else{
                    binding.btnNextActive.visibility = View.INVISIBLE
                    binding.btnNextInactive.visibility = View.VISIBLE

                    viewModel.MENTOR_EMAIL = ""
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
            binding.btnCertificationCompleteCheck.visibility = View.VISIBLE

            Log.e("AppTest", "인증 메일 발송,  발송 메일주소 : ${viewModel.MENTOR_EMAIL}")
            viewModel.sendMentorEmail() // 이메일 보내기
        }

        // 인증 통신 성공
        /*viewModel.isEmailChecked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnNextResend.visibility = View.INVISIBLE
                binding.btnNextCertificationComplete.visibility = View.VISIBLE
                binding.etEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_icon_correct,0)
            }
        })*/

        // 재발송 클릭 시
        binding.btnNextResend.setOnClickListener {
            Log.e("AppTest", "메일 인증 재발송")


        }

        // 메일 성공적으로 전송되면 알림
        viewModel.Is_mail_send.observe(viewLifecycleOwner,{
            if(it){
                Log.e("AppTest", "메일 전송 성공")
                Toast.makeText(context, "인증 메일 전송 성공. 메일을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                Log.e("AppTest", "메일 전송 실패")
                Toast.makeText(context, "인증 메일 전송 실패. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })



        ///////////////
        binding.btnCertificationCompleteCheck.setOnClickListener {
            // 여기서 이메일 체크하고 성공 시 step3 가기!!!
            viewModel.checkMentorEmailCertification()
        }

        ////////////////
        viewModel.Is_Certified.observe(viewLifecycleOwner, {
            if(it){
                 Log.e("AppTest", "기업 메일 인증여부 확인 성공")
                if(!Constants.IS_CERTIFIED_MENTOR){
                    Toast.makeText(context, "이메일 인증을 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "기업 이메일 인증 성공.", Toast.LENGTH_SHORT).show()
                    (activity as MentorRegistActivity).goToStep3()
                }
            }
            else{
                Log.e("AppTest", "기업 메일 인증 통신 오류")
                Toast.makeText(context, "이메일 인증을 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        //////////////

        // 로딩 프로그레스바
        viewModel.progressBarVisibility.observe(viewLifecycleOwner, {
            if (it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })
    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor regist step2 fragment onResume")
    }
}