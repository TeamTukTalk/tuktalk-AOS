package com.nemo.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.step.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.DialogRegistinfoBreakawayBinding
import com.nemo.tuktalk.databinding.DialogTipRegistPortfolioBinding

class TipDialogFragment: DialogFragment() {

    private lateinit var binding: DialogTipRegistPortfolioBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "TipDialogFragment/ onCreateView")

        binding = DialogTipRegistPortfolioBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("AppTest", "TipDialogFragment/ onViewCreated")


        //다이얼로그 크기 설정
        // 스크린 크기
        var wm : WindowManager = getContext()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var screen : Display = wm.getDefaultDisplay()

        var size = Point()
        screen.getRealSize(size)
        var height = size.y
        var width = size.x

        //다이얼로그 크기 - figma 비율
        binding.clDialog.layoutParams.width = width * 320 / 360
        binding.clDialog.layoutParams.height = width * 485 / 360

        ///////////////////////////////////////////////////////////////////////////////////////////////////////


        //
        binding.tvCl1.text = resources.getString(R.string.tv_tip_dialog_1).replace(" ", "\u00A0")


        // x 버튼 누르면 다이얼로그 사라지기
        binding.ivDialogClose.setOnClickListener {
            dismiss()
        }

    }


}