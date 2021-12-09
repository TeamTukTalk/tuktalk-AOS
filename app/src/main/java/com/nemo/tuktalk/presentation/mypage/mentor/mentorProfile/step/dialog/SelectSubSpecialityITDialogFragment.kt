package com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nemo.tuktalk.databinding.DialogSelectSubSpecialityBinding
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile.step.adapter.SelectSubSpecialityITRVadpater
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectSubSpecialityITDialogFragment: DialogFragment() {

    private lateinit var binding : DialogSelectSubSpecialityBinding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    lateinit var rvAdapter: SelectSubSpecialityITRVadpater
    private var testDataList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSelectSubSpecialityBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //다이얼로그 크기 설정
        // 스크린 크기
        var wm : WindowManager = getContext()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var screen : Display = wm.getDefaultDisplay()

        var size = Point()
        screen.getRealSize(size)
        var height = size.y
        var width = size.x

        //다이얼로그 크기
        binding.clDialog.layoutParams.width = width * 320 / 360
        binding.clDialog.layoutParams.height = width * 276 / 360
        //////////////////////////////////////////////////////////////////////

        // 리사이클러뷰 설정
        rvAdapter = SelectSubSpecialityITRVadpater(testDataList,
        selectSpeciality = { speciality, index ->
            viewModel.SUB_SPECIALITY = speciality  // 선택한 상세분야

            if(viewModel.subspeciality_cv_num == 1){
                if(viewModel.checkITSelected(1, index)){
                    viewModel.ld_sub_speciality_1.value = speciality
                }
                else{
                    Toast.makeText(context, "이미 선택된 상세분야 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else if(viewModel.subspeciality_cv_num == 2){
                if(viewModel.checkITSelected(2, index)){
                    viewModel.ld_sub_speciality_2.value = speciality
                }
                else{
                    Toast.makeText(context, "이미 선택된 상세분야 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else if(viewModel.subspeciality_cv_num == 3){
                if(viewModel.checkITSelected(3, index)){
                    viewModel.ld_sub_speciality_3.value = speciality
                }
                else{
                    Toast.makeText(context, "이미 선택된 상세분야 입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            viewModel.ld_sub_speciality_selected.value = true
            Log.e("AppTest", "선택한 상세분야 : ${viewModel.SUB_SPECIALITY}, " +
                    "현재 상세분야 cv num : ${ viewModel.subspeciality_cv_num}")

            dismiss()  // 다이얼로그 종료 / 이 방식 말고 다른 것 있나 보기
        })
        binding.rvSelectSpeciality.layoutManager = LinearLayoutManager(context)
        binding.rvSelectSpeciality.adapter = rvAdapter

        // 디자인 상세분야 항목 설정
        for(i in 0..viewModel.subspeciality_it_list.size-1){
            testDataList.add(viewModel.subspeciality_it_list[i])
        }

        rvAdapter.updateList(testDataList)



    }
}