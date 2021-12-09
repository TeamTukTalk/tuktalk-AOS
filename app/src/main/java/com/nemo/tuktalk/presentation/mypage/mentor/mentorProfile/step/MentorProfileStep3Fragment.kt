package com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.FragmentMentorProfileStep3Binding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep3Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep3Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step 3 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step3, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 회사명 가져오기
        viewModel.getMentorCompnayName()

        // 회사명으로 업데이트
        viewModel.IsGetCompanyNameSuccess.observe(viewLifecycleOwner, {
            if(it){ // 회사명 가져오기 성공 시
                Log.e("AppTest", "MentorProfileStep3Fragment/ 회사명 업데이트! : ${viewModel.COMPANY_NAME}")

                binding.etCompanyName.setText(viewModel.COMPANY_NAME)
                binding.etCompanyName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_icon_correct, 0)
                binding.tvCompanyCertificationDes.visibility = View.VISIBLE
            }
            else{
                Toast.makeText(context, "회사명 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        // 로딩 프로그레스 바
        viewModel.progressBarStep3Visibility.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 부서명 입력 시 개행 방지
        binding.etTaskName.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(p0: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_ENTER)
                    return true
                return false
            }
        })

        // 부서명 edit text 활성화 시 cardview 테두리 색상 변경하기
        binding.etTaskName.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et position focused")
                binding.cvTaskName.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvTaskName.invalidate()
            }
            else{
                Log.e("AppTest","et position focus x")
                binding.cvTaskName.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvTaskName.invalidate()
            }
        })

         // 부서명 edit text 내용 채워졌는지 확인
        binding.etTaskName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etTaskName.text.toString()
                if(userinput.length > 0){
                    viewModel.fillDepartment(true)
                    viewModel.DEPARTMENT = userinput
                }
                else{
                    viewModel.fillDepartment(false)
                    viewModel.DEPARTMENT = ""
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })




        // 직위 입력 시 개행 방지
        binding.etPositionName.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(p0: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_ENTER)
                    return true
                return false
            }
        })

        // 직위 edit text 활성화 시 cardview 테두리 색상 변경하기
        binding.etPositionName.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et position focused")
                binding.cvPositionName.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvPositionName.invalidate()
            }
            else{
                Log.e("AppTest","et position focus x")
                binding.cvPositionName.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvPositionName.invalidate()
            }
        })

        // 직위 edit text 내용 채워졌는지 확인
        binding.etPositionName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etPositionName.text.toString()
                if(userinput.length > 0){
                    viewModel.fillPosition(true)
                    viewModel.POSITION = userinput
                }
                else{
                    viewModel.fillPosition(false)
                    viewModel.POSITION = ""
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        //////////////////////////////////////////////////////////////////
        // 근무기간 년, 월 edit text 활성화 시 cardview 테두리 색상 변경하기
        binding.etCareerYear.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et position focused")
                binding.cvCareerYear.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvCareerYear.invalidate()
            }
            else{
                Log.e("AppTest","et position focus x")
                binding.cvCareerYear.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvCareerYear.invalidate()
            }
        })

        binding.etCareerMonth.setOnFocusChangeListener(View.OnFocusChangeListener { view, focused ->
            if(focused){
                Log.e("AppTest","et position focused")
                binding.cvCareerMonth.strokeColor = resources.getColor(R.color.tuktalk_primary)
                binding.cvCareerMonth.invalidate()
            }
            else{
                Log.e("AppTest","et position focus x")
                binding.cvCareerMonth.strokeColor = resources.getColor(R.color.tuktalk_gray_1)
                binding.cvCareerMonth.invalidate()
            }
        })
        ///////////////////////////////////////////////////
        // 근무기간 월 입력 시
        var month = 0
        binding.etCareerMonth.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etCareerMonth.text.toString()
                if(userinput.length > 0){
                    month = userinput.toInt()
                    viewModel.fillMonth(true, month)
                }
                else {
                    month = 0
                    viewModel.fillMonth(false, month)
                }

                if(month > 11){ // 개월 값은 11까지 가능
                    binding.tvMonthError.visibility = View.VISIBLE
                    binding.cvCareerMonth.strokeColor = resources.getColor(R.color.tuktalk_error)
                    viewModel.monthUnder12Check(false)
                }
                else{
                    binding.tvMonthError.visibility = View.INVISIBLE
                    binding.cvCareerMonth.strokeColor = resources.getColor(R.color.tuktalk_primary)

                    if(userinput.length > 0){
                        viewModel.monthUnder12Check(true)
                    }
                    else
                        viewModel.monthUnder12Check(false)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        // 년 입력 시
        var year = 0
        binding.etCareerYear.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etCareerYear.text.toString()
                if(userinput.length > 0) {
                    year = userinput.toInt()
                    viewModel.fillYear(true, year)
                }
                else {
                    year = 0
                    viewModel.fillYear(false, year)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        ////////
        // 다음 버튼
        viewModel.Step3Checked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnGotoStep4Active.visibility = View.VISIBLE
                binding.btnGotoStep4Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep4Active.visibility = View.INVISIBLE
                binding.btnGotoStep4Unactive.visibility = View.VISIBLE
            }
        })


        // 버튼 클릭 시
        binding.btnGotoStep4Active.setOnClickListener {
            Log.e("AppTest", "btn goto step4 clicked")
            viewModel.setCareer()

            Log.e("AppTest", "department : ${viewModel.DEPARTMENT}  position : ${viewModel.POSITION},\n" +
                    " 근무 기간 : ${viewModel.CAREER.years}년 ${viewModel.CAREER.months}개월")

            // step4로 이동하기
            (activity as MentorProfileActivity).goToStep4()
        }



    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step 3 fragment onResume")
    }
}