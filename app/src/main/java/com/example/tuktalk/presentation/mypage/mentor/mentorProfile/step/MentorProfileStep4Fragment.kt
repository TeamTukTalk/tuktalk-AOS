package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.databinding.FragmentMentorProfileStep4Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep4Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep4Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step 4 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step4, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 경력/경험 내용 채울 시  버튼 텍스트  '건너뛰기' -> '다음'
        binding.etCareerDescription.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etCareerDescription.text.toString()
                if(userinput.length > 0) {
                    binding.btnGotoStep5.text = "다음"
                    viewModel.CAREER_DESCRIPTION = userinput
                }
                else {
                    binding.btnGotoStep5.text = "건너뛰기"
                    viewModel.CAREER_DESCRIPTION = ""
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        /////////////////////////////

        // step 5 이동
        binding.btnGotoStep5.setOnClickListener {
            Log.e("AppTest", "goto step5 clicked,  Career Description : ${viewModel.CAREER_DESCRIPTION} .")

            // step4로 이동하기
            (activity as MentorProfileActivity).goToStep5()
        }

    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step4 fragment onResume")
    }
}