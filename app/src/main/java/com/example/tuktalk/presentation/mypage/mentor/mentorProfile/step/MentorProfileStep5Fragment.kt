package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tuktalk.R
import com.example.tuktalk.common.Constants
import com.example.tuktalk.databinding.FragmentMentorProfileStep1Binding
import com.example.tuktalk.databinding.FragmentMentorProfileStep5Binding
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileActivity
import com.example.tuktalk.presentation.mypage.mentor.mentorProfile.MentorProfileViewModel
import com.google.android.material.card.MaterialCardView
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MentorProfileStep5Fragment: Fragment(){

    private lateinit var binding : FragmentMentorProfileStep5Binding
    private val viewModel : MentorProfileViewModel by sharedViewModel()

    // company size
    private var isCompanySizeSelected = Array<Boolean>(5) { false }
    private var companySizeList = arrayOf("대기업", "중견기업", "중소기업", "스타트업", "프리랜서")
    private var companySizeCvList = arrayOfNulls<MaterialCardView>(5)
    private var companySizeTvList = arrayOfNulls<TextView>(5)

    // 디자인 추천 해시태그
    private var isDesignHashTagSelected = Array<Boolean>(17) { false }
    private var designHashTagList = arrayOf("이직", "외국계", "주니어", "시니어",
            "합격포트폴리오", "비전공자", "실무", "피드백", "GUI", "UX", "기업문화",
            "진로고민", "노하우", "업무체계", "상담", "프로덕트디자인", "캐릭터")
    private var designHashTagCvList = arrayOfNulls<MaterialCardView>(17)
    private var designHashTagTvList = arrayOfNulls<TextView>(17)
    private var SELECTED_DESIGN_HASHTAG_NUM = 0


    // IT/개발 추천 해시태그
    private var isItHashTagSelected = Array<Boolean>(19) { false }
    private var itHashTagList = arrayOf("이직", "외국계", "주니어", "시니어", "합격포트폴리오", "비전공자", "실무",
            "진로고민", "노하우",
            "코딩테스트", "채용담당", "기업문화", "프로젝트", "면접", "공채", "수시채용", "CTO", "알고리즘", "풀스택")
    private var itHashTagCvList = arrayOfNulls<MaterialCardView>(19)
    private var itHashTagTvList = arrayOfNulls<TextView>(19)
    private var SELECTED_IT_HASHTAG_NUM = 0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AppTest", "mentor profile step 5 fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mentor_profile_step5, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전문분야에 따라 추천 해시태그 변경  1.디자인  2. IT/개발
        if(viewModel.ld_speciality.value == "디자인"){
            binding.clHashtagDesign.visibility = View.VISIBLE
            binding.clHashtagIt.visibility = View.INVISIBLE
        }
        else{
            binding.clHashtagDesign.visibility = View.INVISIBLE
            binding.clHashtagIt.visibility = View.VISIBLE
        }

        viewModel.isSpecialityDesign.observe(viewLifecycleOwner, {
            if(it){
                binding.clHashtagDesign.visibility = View.VISIBLE
                binding.clHashtagIt.visibility = View.INVISIBLE
                if(viewModel.isCompanySizeSelected && viewModel.isDesignHashTagSuggestSelected){
                    viewModel.Step5Checked.value = true
                }
            }
            else{
                binding.clHashtagDesign.visibility = View.INVISIBLE
                binding.clHashtagIt.visibility = View.VISIBLE
                if(viewModel.isCompanySizeSelected && viewModel.isItHashTagSuggestSelected){
                    viewModel.Step5Checked.value = true
                }
            }
        })
        /////////////////////////////////////////////////////

        // 기업 선택
        setCompanySizeCardView()
        setCompanySizeTextView()

        for(index in 0..companySizeList.size-1){
            companySizeCvList[index]!!.setOnClickListener {
                if(!isCompanySizeSelected[index]) {
                    clearCompanySelect()
                    isCompanySizeSelected[index] = !isCompanySizeSelected[index]

                    companySizeCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
                    companySizeCvList[index]!!.invalidate()
                    companySizeTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))

                    viewModel.selectCompanySize(companySizeList[index])
                    Log.e("AppTest", "compnaysize : ${viewModel.COMPANY_SIZE}")
                }
            }
        }
        
        ////////////////////////////////////////////////////////////////
        
        // 디자인 추천 해시태그 선택
        setDesignHashTagCardView()
        setDesignHashTagTextView()

        for(index in 0..designHashTagList.size-1){
            designHashTagCvList[index]!!.setOnClickListener {
                if(isDesignHashTagSelected[index] == true){  // 선택 취소
                    isDesignHashTagSelected[index] = false
                    designHashTagCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_gray_3)
                    designHashTagCvList[index]!!.invalidate()
                    designHashTagTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
                    SELECTED_DESIGN_HASHTAG_NUM--
                }
                else{
                    if(SELECTED_DESIGN_HASHTAG_NUM <= 6){  // 추천 해시태그는 7개 까지 선택 가능!!
                        SELECTED_DESIGN_HASHTAG_NUM++
                        isDesignHashTagSelected[index] = true
                        designHashTagCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
                        designHashTagCvList[index]!!.invalidate()
                        designHashTagTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))
                    }
                }

                if(SELECTED_DESIGN_HASHTAG_NUM == 0)
                    viewModel.selectDesignHashTag(false)
                else
                    viewModel.selectDesignHashTag(true)
            }
        }

        /////////////////////////
        
        // IT/개발 추천 해시태그 선택
        setItHashTagCardView()
        setItHashTagTextView()

        for(index in 0..itHashTagList.size-1){
            itHashTagCvList[index]!!.setOnClickListener {
                if(isItHashTagSelected[index] == true){  // 선택 취소
                    isItHashTagSelected[index] = false
                    itHashTagCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_gray_3)
                    itHashTagCvList[index]!!.invalidate()
                    itHashTagTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
                    SELECTED_IT_HASHTAG_NUM--
                }
                else{
                    if(SELECTED_IT_HASHTAG_NUM <= 6){  // 추천 해시태그는 7개 까지 선택 가능!!
                        SELECTED_IT_HASHTAG_NUM++
                        isItHashTagSelected[index] = true
                        itHashTagCvList[index]!!.strokeColor = resources.getColor(R.color.tuktalk_primary)
                        itHashTagCvList[index]!!.invalidate()
                        itHashTagTvList[index]!!.setTextColor(resources.getColor(R.color.tuktalk_primary))
                    }
                }

                if(SELECTED_IT_HASHTAG_NUM <= 0)
                    viewModel.selectItHashTag(false)
                else
                    viewModel.selectItHashTag(true)
            }
        }
        //////////////////////////////////////////////////////////////////////
        // 등록 버튼
        viewModel.Step5Checked.observe(viewLifecycleOwner, {
            if(it){
                binding.btnGotoStep6Active.visibility = View.VISIBLE
                binding.btnGotoStep6Unactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnGotoStep6Active.visibility = View.INVISIBLE
                binding.btnGotoStep6Unactive.visibility = View.VISIBLE
            }
        })

        binding.btnGotoStep6Active.setOnClickListener {

            // 선택된 해시태그 정리
            setHashTagList()

            Log.e("AppTest", "멘토 프로필 등록 최종 데이터 확인\n" +
                    "nickname : ${Constants.USER_NICKNAME}\n" +
                    "simpleIntroduction : ${viewModel.SIMPLE_INTRODUCTION}\n" +
                    "detailedIntroduction : ${viewModel.DETAILED_INTRODUCTION}\n" +
                    "specialty : ${viewModel.SPECIALITY}\n" +
                    "subSpecialties : ${viewModel.SUBSPECIALITY_LIST}\n" +
                    "companyName : ${viewModel.COMPANY_NAME} \n" +
                    "department : ${viewModel.DEPARTMENT} \n" +
                    "position : ${viewModel.POSITION}\n" +
                    "career : ${viewModel.CAREER}\n" +
                    "careerDescription : ${viewModel.CAREER_DESCRIPTION}\n" +
                    "companySize : ${viewModel.COMPANY_SIZE}\n" +
                    "hashTags : ${viewModel.HASHTAGS_LIST}")

            // step6로 이동하기 -> 멘토 프로필 통신 후 결과 성공 시 step6 이동으로 변경하기
            //(activity as MentorProfileActivity).goToStep6()

            // 멘토 프로필 등록 통신 시작
            viewModel.registMentorProfile()
        }

        ////////////
        // 멘토 프로필 등록 성공 감지
        viewModel.Regist_Mentor_Profile_Success.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "멘토 프로필등록 성공 후 완료화면으로 이동!")
                (activity as MentorProfileActivity).goToStep6()  // step6 로 이동
            }
            else{
                Log.e("AppTest", "멘토 프로필 등록 실패")
                Toast.makeText(context, "프로필 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        // 통신 로딩 프로그레스 바
        viewModel.progressBarStep5Visibility.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

    }


    override fun onResume() {
        super.onResume()
        Log.e("AppTest", "mentor profile step 5 fragment onResume")
    }


    fun setCompanySizeCardView(){
        companySizeCvList[0] = binding.cvCompanySize1
        companySizeCvList[1] = binding.cvCompanySize2
        companySizeCvList[2] = binding.cvCompanySize3
        companySizeCvList[3] = binding.cvCompanySize4
        companySizeCvList[4] = binding.cvCompanySize5
    }
    fun setCompanySizeTextView(){
        companySizeTvList[0] = binding.tvCompanySize1
        companySizeTvList[1] = binding.tvCompanySize2
        companySizeTvList[2] = binding.tvCompanySize3
        companySizeTvList[3] = binding.tvCompanySize4
        companySizeTvList[4] = binding.tvCompanySize5
    }
    fun clearCompanySelect(){
        for(i in 0..companySizeList.size-1){
            isCompanySizeSelected[i] = false
            companySizeCvList[i]!!.strokeColor = resources.getColor(R.color.tuktalk_gray_3)
            companySizeCvList[i]!!.invalidate()
            companySizeTvList[i]!!.setTextColor(resources.getColor(R.color.tuktalk_sub_content_2))
        }
    }

    ////////////////

    fun setDesignHashTagCardView(){
        designHashTagCvList[0] = binding.cvHashtagDesgin1
        designHashTagCvList[1] = binding.cvHashtagDesgin2
        designHashTagCvList[2] = binding.cvHashtagDesgin3
        designHashTagCvList[3] = binding.cvHashtagDesgin4
        designHashTagCvList[4] = binding.cvHashtagDesgin5
        designHashTagCvList[5] = binding.cvHashtagDesgin6
        designHashTagCvList[6] = binding.cvHashtagDesgin7
        designHashTagCvList[7] = binding.cvHashtagDesgin8
        designHashTagCvList[8] = binding.cvHashtagDesgin9
        designHashTagCvList[9] = binding.cvHashtagDesgin10
        designHashTagCvList[10] = binding.cvHashtagDesgin11
        designHashTagCvList[11] = binding.cvHashtagDesgin12
        designHashTagCvList[12] = binding.cvHashtagDesgin13
        designHashTagCvList[13] = binding.cvHashtagDesgin14
        designHashTagCvList[14] = binding.cvHashtagDesgin15
        designHashTagCvList[15] = binding.cvHashtagDesgin16
        designHashTagCvList[16] = binding.cvHashtagDesgin17
    }
    fun setDesignHashTagTextView(){
        designHashTagTvList[0] = binding.tvHashtagDesign1
        designHashTagTvList[1] = binding.tvHashtagDesign2
        designHashTagTvList[2] = binding.tvHashtagDesign3
        designHashTagTvList[3] = binding.tvHashtagDesign4
        designHashTagTvList[4] = binding.tvHashtagDesign5
        designHashTagTvList[5] = binding.tvHashtagDesign6
        designHashTagTvList[6] = binding.tvHashtagDesign7
        designHashTagTvList[7] = binding.tvHashtagDesign8
        designHashTagTvList[8] = binding.tvHashtagDesign9
        designHashTagTvList[9] = binding.tvHashtagDesign10
        designHashTagTvList[10] = binding.tvHashtagDesign11
        designHashTagTvList[11] = binding.tvHashtagDesign12
        designHashTagTvList[12] = binding.tvHashtagDesign13
        designHashTagTvList[13] = binding.tvHashtagDesign14
        designHashTagTvList[14] = binding.tvHashtagDesign15
        designHashTagTvList[15] = binding.tvHashtagDesign16
        designHashTagTvList[16] = binding.tvHashtagDesign17
    }

    ////////////////////

    fun setItHashTagCardView(){
        itHashTagCvList[0] = binding.cvHashtagIt1
        itHashTagCvList[1] = binding.cvHashtagIt2
        itHashTagCvList[2] = binding.cvHashtagIt3
        itHashTagCvList[3] = binding.cvHashtagIt4
        itHashTagCvList[4] = binding.cvHashtagIt5
        itHashTagCvList[5] = binding.cvHashtagIt6
        itHashTagCvList[6] = binding.cvHashtagIt7
        itHashTagCvList[7] = binding.cvHashtagIt8
        itHashTagCvList[8] = binding.cvHashtagIt9
        itHashTagCvList[9] = binding.cvHashtagIt10
        itHashTagCvList[10] = binding.cvHashtagIt11
        itHashTagCvList[11] = binding.cvHashtagIt12
        itHashTagCvList[12] = binding.cvHashtagIt13
        itHashTagCvList[13] = binding.cvHashtagIt14
        itHashTagCvList[14] = binding.cvHashtagIt15
        itHashTagCvList[15] = binding.cvHashtagIt16
        itHashTagCvList[16] = binding.cvHashtagIt17
        itHashTagCvList[17] = binding.cvHashtagIt18
        itHashTagCvList[18] = binding.cvHashtagIt19
    }
    fun setItHashTagTextView(){
        itHashTagTvList[0] = binding.tvHashtagIt1
        itHashTagTvList[1] = binding.tvHashtagIt2
        itHashTagTvList[2] = binding.tvHashtagIt3
        itHashTagTvList[3] = binding.tvHashtagIt4
        itHashTagTvList[4] = binding.tvHashtagIt5
        itHashTagTvList[5] = binding.tvHashtagIt6
        itHashTagTvList[6] = binding.tvHashtagIt7
        itHashTagTvList[7] = binding.tvHashtagIt8
        itHashTagTvList[8] = binding.tvHashtagIt9
        itHashTagTvList[9] = binding.tvHashtagIt10
        itHashTagTvList[10] = binding.tvHashtagIt11
        itHashTagTvList[11] = binding.tvHashtagIt12
        itHashTagTvList[12] = binding.tvHashtagIt13
        itHashTagTvList[13] = binding.tvHashtagIt14
        itHashTagTvList[14] = binding.tvHashtagIt15
        itHashTagTvList[15] = binding.tvHashtagIt16
        itHashTagTvList[16] = binding.tvHashtagIt17
        itHashTagTvList[17] = binding.tvHashtagIt18
        itHashTagTvList[18] = binding.tvHashtagIt19
    }

    // 선택한 추천 해시태그 저장
    fun setHashTagList(){
        viewModel.clearHashTagList()

        if(viewModel.isSpecialityDesign.value == true){  // 전문분야 = 디자인
            for(i in 0..designHashTagList.size-1){
                if(isDesignHashTagSelected[i] == true){
                    var hashTag = HashTag(designHashTagList[i])
                    viewModel.setHashTagList(hashTag)
                }
            }
        }
        else{
            for(i in 0..itHashTagList.size-1){
                if(isItHashTagSelected[i] == true){
                    var hashTag = HashTag(itHashTagList[i])
                    viewModel.setHashTagList(hashTag)
                }
            }
        }
    }

}