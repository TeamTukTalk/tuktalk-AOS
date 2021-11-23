package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.databinding.FragmentMentorProfileStep2Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep2Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep2Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    private var SUB_SPECIALITY_NUM = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step 2 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step2, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentor profile step 2 fragment onViewCreated")

        // 전문분야 선택 시
        binding.cvSpeciality.setOnClickListener {

        }


        // 상세 분야 최대 3개 까지 추가 가능
        binding.tvAddSubSpeciality.setOnClickListener {
            if(SUB_SPECIALITY_NUM == 1){
                binding.cvSubSpeciality2.visibility = View.VISIBLE
                SUB_SPECIALITY_NUM = 2
            }
            else if(SUB_SPECIALITY_NUM == 2){
                binding.cvSubSpeciality3.visibility = View.VISIBLE
                SUB_SPECIALITY_NUM = 3
            }
            else{
                Toast.makeText(context, "상세분야는 최대 3개까지 등록할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }


    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step 2 fragment onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("AppTest", "mentor profile step 2 fragment onDestroyView")
    }
}