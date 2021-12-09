package com.nemo.tuktalk.presentation.guide

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nemo.tuktalk.databinding.FragmentGuide2Binding

class Guide2Fragment: Fragment() {

    private var guide2binding : FragmentGuide2Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide2Binding.inflate(inflater, container, false)
            guide2binding = binding

            return guide2binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        val textData1 = guide2binding!!.tvGuide21.text
        val builder1 = SpannableStringBuilder(textData1)

        val fontColorSpan1 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder1.setSpan(fontColorSpan1,9,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide2binding!!.tvGuide21.text = builder1
        //

        //
        val textData2 = guide2binding!!.tvGuide22.text
        val builder2 = SpannableStringBuilder(textData2)

        val fontColorSpan2 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder2.setSpan(fontColorSpan2,8,9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide2binding!!.tvGuide22.text = builder2
        //
    }
}