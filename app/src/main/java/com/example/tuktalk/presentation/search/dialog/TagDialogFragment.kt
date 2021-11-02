package com.example.tuktalk.presentation.search.dialog

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.*
import com.example.tuktalk.R
import com.example.tuktalk.databinding.DialogRegistinfoBreakawayBinding
import com.example.tuktalk.databinding.DialogSearchTagBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TagDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding : DialogSearchTagBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View? {
        binding = DialogSearchTagBinding.inflate(inflater, container, false)


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

        binding.clDialog.layoutParams.height = width * 548 / 360
    }
}