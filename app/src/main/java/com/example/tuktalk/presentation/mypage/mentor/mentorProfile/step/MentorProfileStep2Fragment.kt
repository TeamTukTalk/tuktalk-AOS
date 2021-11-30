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
import com.example.tuktalk.databinding.FragmentMentorProfileStep2Binding
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog.SelectSpecialityDialogFragment
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog.SelectSubSpecialityDesignDialogFragment
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog.SelectSubSpecialityITDialogFragment
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

        // 전문분야 선택 시 다이얼로그 나타나기
        binding.cvSpeciality.setOnClickListener {
            var dialogVeiw = SelectSpecialityDialogFragment()
            dialogVeiw.show(childFragmentManager, "AppTest")
        }

        // 전문분야 업데이트
        viewModel.ld_speciality.observe(viewLifecycleOwner, {
            binding.etSpeciality.setText(it)

            // 활성화 되어 있는 상세분야 초기화
            if(binding.cvSubSpeciality1.visibility == View.VISIBLE){
                viewModel.ld_sub_speciality_1.value = null
                viewModel.ld_sub_speciality_selected.value = false
            }
            if(binding.cvSubSpeciality2.visibility == View.VISIBLE){ // 안보이게 다시 바꾸기
                viewModel.ld_sub_speciality_2.value = null
                viewModel.ld_sub_speciality_selected.value = false

                binding.cvSubSpeciality2.visibility = View.INVISIBLE
            }
            if(binding.cvSubSpeciality3.visibility == View.VISIBLE){ // 안보이게 다시 바꾸기
                viewModel.ld_sub_speciality_3.value = null
                viewModel.ld_sub_speciality_selected.value = false

                binding.cvSubSpeciality3.visibility = View.INVISIBLE
            }

            viewModel.clearSubSpecailitySelected() // 상세분야 선택여부 모두 초기화

        })

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

        // test
        /*binding.cvSubSpeciality1.setOnClickListener {
            binding.etSpeciality.setText(null)
        }*/
        binding.cvSubSpeciality1.setOnClickListener {
            if(viewModel.ld_speciality_selected.value == true){  // 전문분야 선택되었다면
                if(viewModel.speciality_num == 0){ // 전문분야 : 디자인
                    var dialogVeiw = SelectSubSpecialityDesignDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
                else if(viewModel.speciality_num == 1){ // 전문분야 : IT/개발
                    var dialogVeiw = SelectSubSpecialityITDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
            }
            else{
                Toast.makeText(context, "전문분야를 먼저 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            viewModel.subspeciality_cv_num = 1
        }

        binding.cvSubSpeciality2.setOnClickListener {
            if(viewModel.ld_speciality_selected.value == true){  // 전문분야 선택되었다면
                if(viewModel.speciality_num == 0){ // 전문분야 : 디자인
                    var dialogVeiw = SelectSubSpecialityDesignDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
                else if(viewModel.speciality_num == 1){ // 전문분야 : IT/개발
                    var dialogVeiw = SelectSubSpecialityITDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
            }
            else{
                Toast.makeText(context, "전문분야를 먼저 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            viewModel.subspeciality_cv_num = 2
        }

        binding.cvSubSpeciality3.setOnClickListener {
            if(viewModel.ld_speciality_selected.value == true){  // 전문분야 선택되었다면
                if(viewModel.speciality_num == 0){ // 전문분야 : 디자인
                    var dialogVeiw = SelectSubSpecialityDesignDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
                else if(viewModel.speciality_num == 1){ // 전문분야 : IT/개발
                    var dialogVeiw = SelectSubSpecialityITDialogFragment()
                    dialogVeiw.show(childFragmentManager, "AppTest")
                }
            }
            else{
                Toast.makeText(context, "전문분야를 먼저 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            viewModel.subspeciality_cv_num = 3
        }


        // 상세 분야 업데이트 1, 2, 3
        viewModel.ld_sub_speciality_1.observe(viewLifecycleOwner, {
            binding.etSubspeciality1.setText(it)
        })
        viewModel.ld_sub_speciality_2.observe(viewLifecycleOwner, {
            binding.etSubspeciality2.setText(it)
        })
        viewModel.ld_sub_speciality_3.observe(viewLifecycleOwner, {
            binding.etSubspeciality3.setText(it)
        })

        // 버튼 활성화
        viewModel.ld_sub_speciality_selected.observe(viewLifecycleOwner, {
            if(it){
                if(viewModel.ld_speciality_selected.value == true){
                    binding.btnGotoStep3Active.visibility = View.VISIBLE
                    binding.btnGotoStep3Unactive.visibility = View.INVISIBLE
                }
            }
            else{
                binding.btnGotoStep3Active.visibility = View.INVISIBLE
                binding.btnGotoStep3Unactive.visibility = View.VISIBLE
            }
        })
        viewModel.ld_speciality_selected.observe(viewLifecycleOwner, {
            if(it){
                if(viewModel.ld_sub_speciality_selected.value == true){
                    binding.btnGotoStep3Active.visibility = View.VISIBLE
                    binding.btnGotoStep3Unactive.visibility = View.INVISIBLE
                }
            }
            else{
                binding.btnGotoStep3Active.visibility = View.INVISIBLE
                binding.btnGotoStep3Unactive.visibility = View.VISIBLE
            }
        })


        ///////////////////////
        // 다음 버튼 클릭 시
        binding.btnGotoStep3Active.setOnClickListener {
            Log.e("AppTest", "다음 버튼 클릭, speciality:${viewModel.ld_speciality.value} " +
                    "sub1 : ${viewModel.ld_sub_speciality_1.value}," +
                    " sub2 : ${viewModel.ld_sub_speciality_2.value}, sub3 : ${viewModel.ld_sub_speciality_3.value}")
            viewModel.setSubSpecialityList() // 상세분야 리스트 생성
            Log.e("AppTest", "subSpeicalityList : ${viewModel.SUBSPECIALITY_LIST}")

            // step3로 이동하기
            (activity as MentorProfileActivity).goToStep3()
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