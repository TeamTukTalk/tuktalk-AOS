package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.tuktalk.databinding.DialogRegistinfoBreakawayBinding
import com.example.tuktalk.databinding.DialogSelectSpecialityBinding

class SelectSpecialityDialog: DialogFragment() {

    private lateinit var binding : DialogSelectSpecialityBinding

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

    }
}