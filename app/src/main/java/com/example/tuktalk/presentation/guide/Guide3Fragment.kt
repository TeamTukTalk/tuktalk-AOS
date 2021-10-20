package com.example.tuktalk.presentation.guide

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuktalk.databinding.FragmentGuide3Binding

class Guide3Fragment: Fragment() {

    private var guide3binding : FragmentGuide3Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {

            val binding = FragmentGuide3Binding.inflate(inflater, container, false)
            guide3binding = binding

            return guide3binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        val textData1 = guide3binding!!.tvGuide31.text
        val builder1 = SpannableStringBuilder(textData1)

        val fontColorSpan1 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder1.setSpan(fontColorSpan1,6,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide3binding!!.tvGuide31.text = builder1
        //

        //
        val textData2 = guide3binding!!.tvGuide32.text
        val builder2 = SpannableStringBuilder(textData2)

        val fontColorSpan2 = ForegroundColorSpan(Color.parseColor("#828282"))
        builder2.setSpan(fontColorSpan2,7,9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        guide3binding!!.tvGuide32.text = builder2
        //
    }
}