package com.example.tuktalk.presentation.search.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.DialogSearchTagBinding
import com.example.tuktalk.presentation.search.SearchDesignFragment
import com.example.tuktalk.presentation.search.SearchITFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView


class TagDialogFragment_IT : BottomSheetDialogFragment() {  //  IT/개발 뷰에서 사용하는 태그 dialog

    private lateinit var binding : DialogSearchTagBinding

    private var isCompanySelected = Array<Boolean>(5) { false }
    private var isCareerSelected = Array<Boolean>(5) { false }

    private var companyCvList = arrayOfNulls<MaterialCardView>(5)
    private var careerCvList = arrayOfNulls<MaterialCardView>(5)

    private var companyTvList = arrayOfNulls<TextView>(5)
    private var careerTvList = arrayOfNulls<TextView>(5)

    private var companyTagValue = arrayOfNulls<String>(5)
    private var careerTagValue = arrayOfNulls<String>(5)
    private var careerTagIntValue = arrayOf(1,3,5,7,9)

    private var COMPANY_SIZE = ""
    private var CAREER = ""
    private var START_YEAR = 0

    private var Index_company = -1
    private var Index_career = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares

        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 560 / 760
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View? {
        val view = inflater.inflate(R.layout.dialog_search_tag, container, false)
        binding = DialogSearchTagBinding.bind(view)

        /*view.findViewById<ConstraintLayout>(R.id.cl_dialog).maxHeight =
                (resources.displayMetrics.heightPixels * 0.5).toInt()*/
        Log.e("AppTest", "TagDialogFragment_IT/ search tag dialog fragment onCreateView")
        Log.e("AppTest", "TagDialogFragment_IT/ company : ${COMPANY_SIZE}, career : ${CAREER}")

        var bundle = arguments
        Index_company = bundle!!.getInt("index_company", -1)
        Index_career = bundle!!.getInt("index_career", -1)
        Log.e("AppTest", "TagDialogFragment_IT/ company index : ${Index_company}, career index : ${Index_career}")

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search tag dialog fragment onViewCreated")

        companyTagValue[0] = "대기업"
        companyTagValue[1] = "중견기업"
        companyTagValue[2] = "중소기업"
        companyTagValue[3] = "스타트업"
        companyTagValue[4] = "프리랜서"

        careerTagValue[0] = "1~2년"
        careerTagValue[1] = "3~4년"
        careerTagValue[2] = "5~6년"
        careerTagValue[3] = "7~8년"
        careerTagValue[4] = "9년 이상"

        //기업 태그 cardview & textview
        companyCvList[0] = binding.cvCompany1
        companyCvList[1] = binding.cvCompany2
        companyCvList[2] = binding.cvCompany3
        companyCvList[3] = binding.cvCompany4
        companyCvList[4] = binding.cvCompany5

        companyTvList[0] = binding.tvCompany1
        companyTvList[1] = binding.tvCompany2
        companyTvList[2] = binding.tvCompany3
        companyTvList[3] = binding.tvCompany4
        companyTvList[4] = binding.tvCompany5

        // 경력 태그 cardview & textview
        careerCvList[0] = binding.cvCareer1
        careerCvList[1] = binding.cvCareer2
        careerCvList[2] = binding.cvCareer3
        careerCvList[3] = binding.cvCareer4
        careerCvList[4] = binding.cvCareer5

        careerTvList[0] = binding.tvCareer1
        careerTvList[1] = binding.tvCareer2
        careerTvList[2] = binding.tvCareer3
        careerTvList[3] = binding.tvCareer4
        careerTvList[4] = binding.tvCareer5

        // 이전에 선택한 값으로 뷰 보여줘야 함
        if(Index_company >= 0)
            selectCompanyTag(Index_company)
        if(Index_career >= 0)
            selectCareerTag(Index_career)


        // 태그 클릭 시 동작
        for(index in 0..4){
            companyCvList[index]!!.setOnClickListener {
                Log.e("AppTest","TagDialogFragment_IT/ company tag ${index} selected")
                selectCompanyTag(index)
            }

            careerCvList[index]!!.setOnClickListener {
                Log.e("AppTest","TagDialogFragment_IT/ career tag ${index} selected")
                selectCareerTag(index)
            }
        }


        ///////////////////
        // 초기화 텍스트 선택 시
        binding.tvTagReset.setOnClickListener {
            resetTag()
        }

        ///////
        // 적용하기
        binding.btnTagApply.setOnClickListener {
            (parentFragment as SearchITFragment).tagSelected(COMPANY_SIZE, CAREER, Index_company, Index_career, START_YEAR)
            closeDialog()
        }


        // 다이얼로그 닫힘 버튼
        binding.ivDialogClose.setOnClickListener {
            closeDialog()
        }

        /*binding.tvTagDes.setOnClickListener {
            Log.e("AppTest", "tvtagdes clicked")
            companyCvList[0]!!.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.tuktalk_gray4))
            companyCvList[0]!!.invalidate()

        }*/



    }

    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "TagDialogFragment_IT/ dialog fragment onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("AppTest", "TagDialogFragment_IT/ dialog fragment onDestroyView")
    }

    // 기업 태그 선택 시
    fun selectCompanyTag(index: Int){
        resetCompany() // 일단 모두 초기화
        isCompanySelected[index] = true // 선택 한 태그만 true
        companyCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
        companyTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))
        COMPANY_SIZE = companyTagValue[index]!! // 선택한 태그 값
        Index_company = index
        Log.e("AppTest", "TagDialogFragment_IT/ 선택한 companySize 값 : ${COMPANY_SIZE}")
    }

    // 경력 태그 선택 시
    fun selectCareerTag(index: Int){
        resetCareer() // 일단 모두 초기화
        isCareerSelected[index] = true // 선택 한 태그만 true
        careerCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
       // careerCvList[index]!!.strokeWidth = 2
        careerTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))
        CAREER = careerTagValue[index]!! // 선택한 태그 값
        Index_career = index
        START_YEAR = careerTagIntValue[index]
        Log.e("AppTest", "TagDialogFragment_IT/ 선택한 startYear 값 : ${START_YEAR}")
    }

    fun resetCompany(){
        for(i in 0..4){
            companyCvList[i]!!.strokeColor = resources.getColor(R.color.tuktalk_gray4)
            companyTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_gray2))
            companyCvList[i]!!.invalidate() // 이걸 안해주니 색이 변경되지 않았음 -> why??  // 카테고리 선택에서는 이거 없어도 잘 동작..

            isCompanySelected[i] = false
        }

        COMPANY_SIZE = ""
    }

    fun resetCareer(){
        for(i in 0..4){
            careerCvList[i]!!.strokeColor = resources.getColor(R.color.tuktalk_gray4)
            careerTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_gray2))
            //careerCvList[i]!!.strokeWidth = 1
            careerCvList[i]!!.invalidate()

            isCareerSelected[i] = false
        }
        CAREER = ""
        START_YEAR = 0
    }


    // 두 태그의 선택 초기화
    fun resetTag(){
        for(i in 0..4){
            companyCvList[i]!!.setStrokeColor(resources.getColor(R.color.tuktalk_gray4))
            companyTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_gray2))
            companyCvList[i]!!.invalidate()

            careerCvList[i]!!.setStrokeColor(resources.getColor(R.color.tuktalk_gray4))
            careerTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_gray2))
            careerCvList[i]!!.invalidate()

            isCompanySelected[i] = false
            isCareerSelected[i] = false
        }

        COMPANY_SIZE = ""
        CAREER = ""
        Index_company = -1
        Index_career = -1
        START_YEAR = 0
    }


    // dialog 닫힘
    fun closeDialog(){
            dialog?.dismiss()
    }
}