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
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentMentorRegistStep1Binding

class MentorRegistStep1Fragment: Fragment() {

    private lateinit var binding : FragmentMentorRegistStep1Binding

    var isCompanyFilled = false
    var isDeparmentFilled = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor regist step1 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_regist_step1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mentor regist step1 fragment onViewCreated")

        // 회사이름, 부서 입력 시 edittext 색상 변경
        binding.etCompanyName.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et company focused")
                binding.etCompanyName.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et company focus x")
                binding.etCompanyName.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_gray1)
            }
        })

        binding.etDepartmentName.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et department focused")
                binding.etDepartmentName.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_primary)
                //binding.etId.error = "error test"
            }
            else{
                Log.e("AppTest","et department focus x")
                binding.etDepartmentName.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.tuktalk_gray1)
            }
        })

        ///////////////////////

        // 두 항목 모두 채워졌을때 다음 버튼 활성화
        binding.etCompanyName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var companyText = binding.etCompanyName.text
                if(companyText.length > 0){
                    isCompanyFilled = true
                    fillCheck()
                }
                else{
                    isCompanyFilled = false
                    fillCheck()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.etDepartmentName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var departmentText = binding.etDepartmentName.text
                if(departmentText.length > 0){
                    isDeparmentFilled = true
                    fillCheck()
                }
                else{
                    isDeparmentFilled = false
                    fillCheck()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        ////////////
        // 다음 버튼 클릭 시 step2 로 가야함
        binding.btnNextActive.setOnClickListener {
            (activity as MentorRegistActivity).goToStep2()
        }


    }

    fun fillCheck(){
        if(isCompanyFilled && isDeparmentFilled){  // 다음 버튼 활성화
            binding.btnNextActive.visibility = View.VISIBLE
            binding.btnNextInactive.visibility = View.INVISIBLE
        }
        else{
            binding.btnNextActive.visibility = View.INVISIBLE
            binding.btnNextInactive.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor regist step1 fragment onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("AppTest", "mentor regist step1 fragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("AppTest", "mentor regist step1 fragment onDestroy")
    }
}