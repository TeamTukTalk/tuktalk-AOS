package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuktalk.common.utils.VerticalItemDecorator
import com.example.tuktalk.databinding.DialogRegistinfoBreakawayBinding
import com.example.tuktalk.databinding.DialogSelectSpecialityBinding
import com.example.tuktalk.domain.model.home.MentorListRVitem
import com.example.tuktalk.presentation.home.viewAll.adapter.ViewAllByTaskRVAdapter
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.adapter.SelectSpecialityRVadpater
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectSpecialityDialogFragment: DialogFragment() {

    private lateinit var binding : DialogSelectSpecialityBinding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    lateinit var rvAdapter: SelectSpecialityRVadpater
    private var testDataList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSelectSpecialityBinding.inflate(inflater, container, false)

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
        binding.clDialog.layoutParams.height = width * 102 / 360
        //////////////////////////////////////////////////////////////////////

        // 리사이클러뷰 설정
        rvAdapter = SelectSpecialityRVadpater(testDataList,
        selectSpeciality = { speciality, index ->
            viewModel.SPECIALITY = speciality  // 선택한 전문 분야
            viewModel.ld_speciality.value = speciality
            viewModel.ld_speciality_selected.value = true
            viewModel.speciality_num = index
            Log.e("AppTest", "선택한 전문분야 : ${ viewModel.SPECIALITY}, 전문분야 num : ${ viewModel.speciality_num}")


            viewModel.Step5Checked.value = false  // 전문분야 새로 선택 시를 대비

            // step5 추천 해시태그 전문분야 분기 처리 -> 디자인 분야 인지, it분야 인지
            if(viewModel.speciality_num == 0)
                viewModel.isSpecialityDesign.value = true // 디자인 분야
            else
                viewModel.isSpecialityDesign.value = false // it 분야야

           dismiss()  // 다이얼로그 종료 / 이 방식 말고 다른 것 있나 보기
        })
        binding.rvSelectSpeciality.layoutManager = LinearLayoutManager(context)
        binding.rvSelectSpeciality.adapter = rvAdapter

        testDataList.apply{
            add("디자인")
            add("IT/개발")
        }

        rvAdapter.updateList(testDataList)



    }
}