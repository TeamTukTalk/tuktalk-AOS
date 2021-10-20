package com.example.tuktalk.presentation.guide

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.databinding.FragmentGuide1Binding

class Guide1Fragment: Fragment() {

    private var guide1binding :FragmentGuide1Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide1Binding.inflate(inflater, container, false)
            guide1binding = binding

            return guide1binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 텍스트뷰 색상 일부 적용하기
        val textData1 = guide1binding!!.tvGuide11.text

        val builder1 = SpannableStringBuilder(textData1)

        val fontColorSpan1 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder1.setSpan(fontColorSpan1,5,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide1binding!!.tvGuide11.text = builder1

        //
        val textData2 = guide1binding!!.tvGuide12.text

        val builder2 = SpannableStringBuilder(textData2)

        val fontColorSpan2 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder2.setSpan(fontColorSpan2,3,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide1binding!!.tvGuide12.text = builder2
        //


    }
}