package com.nemo.tuktalk.presentation.mypage.mentor.mentorProfile

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemo.tuktalk.common.Constants
import com.nemo.tuktalk.common.Constants_gitignore
import com.nemo.tuktalk.data.remote.dto.request.mentor.MentorProfileRequestDto
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.CareerInput
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.HashTag
import com.nemo.tuktalk.domain.model.mypage.mentor.profileRegist.SubSpecialitySelect
import com.nemo.tuktalk.domain.usecase.mentor.MentorGetCompanyNameUseCase
import com.nemo.tuktalk.domain.usecase.mentor.MentorRegistProfileUseCase

class MentorProfileViewModel(
    private val getMentorCompanyNameUseCase : MentorGetCompanyNameUseCase,
    private val registMentorProfileUseCase : MentorRegistProfileUseCase
): ViewModel() {
    // step1
    var isIntroduceSimpleFilled = false
    var isIntroduceDetailFilled = false
    var step1Checked = MutableLiveData<Boolean>()

    var SIMPLE_INTRODUCTION = ""  // 데이터
    var DETAILED_INTRODUCTION = "" // 데이터


    fun fillSimpleIntroduce(flag : Boolean){
        isIntroduceSimpleFilled = flag
        step1Check()
    }
    fun fillDeatailedIntroduce(flag: Boolean){
        isIntroduceDetailFilled = flag
        step1Check()
    }
    fun step1Check(){
        step1Checked.value = isIntroduceSimpleFilled && isIntroduceDetailFilled
    }
    ////////////

    // step2
    var speciality_num = 0   // 디자인 = 0, IT/개발 = 1
    var SPECIALITY = ""
    var SUB_SPECIALITY = ""
    var ld_speciality = MutableLiveData<String>()  // 데이터
    var ld_speciality_selected = MutableLiveData<Boolean>() // 전문분야 선택되었는지

    var SUBSPECIALITY_LIST = ArrayList<String>()  // 상세분야 리스트

    var ld_sub_speciality_1 = MutableLiveData<String>() // 데이터
    var ld_sub_speciality_2 = MutableLiveData<String>() // 데이터
    var ld_sub_speciality_3 = MutableLiveData<String>() // 데이터
    var ld_sub_speciality_selected = MutableLiveData<Boolean>()  // 상세분야 하나라도 선택되었는지

    var subspeciality_design_list = arrayOf("UXUI 디자인", "웹 디자인", "브랜드 디자인", "그래픽 디자인", "산업/제품 디자인")
    var subSpecialityDesignSelectedList = Array<SubSpecialitySelect>(subspeciality_design_list.size) { SubSpecialitySelect(0, false) }

    var subspeciality_it_list = arrayOf("앱", "웹", "IT/기술직군", "서버", "AI/머신러닝", "데이터", "게임")
    var subSpecialityITSelectedList = Array<SubSpecialitySelect>(subspeciality_it_list.size) { SubSpecialitySelect(0, false) }

    var subspeciality_cv_num = 1  // 현재 선택된 상세분야 cardview 알기 위함

    // 전문분야 디자인 -> 상세분야 선택 시
    fun checkDesignSelected(cvNum:Int, index: Int) : Boolean{
        if(subSpecialityDesignSelectedList[index].isSelected == false){

            // 해당 card view 에 이전 선택된 것 있었다면 미선택으로 바꾸기 -> 미선택 cvNum = 0 으로 하기
            for(i in 0..subSpecialityDesignSelectedList.size-1){
                if(subSpecialityDesignSelectedList[i].cvNum == cvNum){
                    subSpecialityDesignSelectedList[i].cvNum = 0
                    subSpecialityDesignSelectedList[i].isSelected = false
                }
            }

            subSpecialityDesignSelectedList[index].cvNum = cvNum
            subSpecialityDesignSelectedList[index].isSelected = true

            return true
        }
        else
            return false
    }

    // 전문분야 IT/개발 -> 상세분야 선택 시
    fun checkITSelected(cvNum:Int, index: Int) : Boolean{
        if(subSpecialityITSelectedList[index].isSelected == false){

            // 해당 card view 에 이전 선택된 것 있었다면 미선택으로 바꾸기 -> 미선택 cvNum = 0 으로 하기
            for(i in 0..subSpecialityITSelectedList.size-1){
                if(subSpecialityITSelectedList[i].cvNum == cvNum){
                    subSpecialityITSelectedList[i].cvNum = 0
                    subSpecialityITSelectedList[i].isSelected = false
                }
            }
            subSpecialityITSelectedList[index].cvNum = cvNum
            subSpecialityITSelectedList[index].isSelected = true

            return true
        }
        else
            return false
    }

    // 모든 상세분야 선택 초기화
    fun clearSubSpecailitySelected(){
        for(i in 0..subSpecialityDesignSelectedList.size-1){
            subSpecialityDesignSelectedList[i].cvNum = 0
            subSpecialityDesignSelectedList[i].isSelected = false
        }

        for(i in 0..subSpecialityITSelectedList.size-1){
            subSpecialityITSelectedList[i].cvNum = 0
            subSpecialityITSelectedList[i].isSelected = false
        }
    }

    fun setSubSpecialityList(){
        if(ld_sub_speciality_1.value != null)
            SUBSPECIALITY_LIST.add(ld_sub_speciality_1.value!!)

        if(ld_sub_speciality_2.value != null)
            SUBSPECIALITY_LIST.add(ld_sub_speciality_2.value!!)

        if(ld_sub_speciality_3.value != null)
            SUBSPECIALITY_LIST.add(ld_sub_speciality_3.value!!)
    }

    // step3에서 stpe2로 되돌아 가는 경우 리스트에 들어있는 값 없애주기!!!
    fun clearSubSpecialityList(){
        Log.e("AppTest", "상세분야 리스트 초기화! / 중복방지")
        SUBSPECIALITY_LIST.clear()
    }

////////////////////////////////////////////////////////////////////

    // step3

    var isDepartmentFilled = false
    var isPositionFilled = false
    var isYearFilled = false
    var isMonthFilled = false
    var isMonthUnder12 = true

    var progressBarStep3Visibility = MutableLiveData<Boolean>()  // 로딩 프로그레스바
    var IsGetCompanyNameSuccess = MutableLiveData<Boolean>()

    var COMPANY_NAME = ""
    var DEPARTMENT = ""
    var YEAR = 0
    var MONTH = 0
    var POSITION = "" // 데이터
    var CAREER = CareerInput(0,0)  // 데이터
    var Step3Checked = MutableLiveData<Boolean>()

    fun fillDepartment(flag: Boolean){
        isDepartmentFilled = flag
        checkStep3()
    }

    fun fillPosition(flag: Boolean){
        isPositionFilled = flag
        checkStep3()
    }

    fun fillMonth(flag:Boolean, month: Int){
        isMonthFilled = flag
        MONTH = month
        checkStep3()
    }

    fun monthUnder12Check(flag: Boolean){     /// 0년 0월 처리하기!!!!
        isMonthUnder12 = flag
        checkStep3()
    }

    fun fillYear(flag: Boolean, year: Int){
        isYearFilled = flag
        YEAR = year
        checkStep3()
    }

    fun checkStep3(){
        Step3Checked.value = isDepartmentFilled && (isYearFilled || isMonthFilled) && isMonthUnder12 && isPositionFilled && (MONTH>0 || YEAR>0)
    }

    fun setCareer(){
        CAREER.years = YEAR
        CAREER.months = MONTH
    }

    // 멘토 회사 이름 가져오기
    @SuppressLint("CheckResult")
    fun getMentorCompnayName(){
        progressBarStep3Visibility.value = true

        getMentorCompanyNameUseCase.getMentorCompanyName(Constants_gitignore.USER_TOKEN, Constants.USER_EMAIL).subscribe(
            {
                if(it.code() == 200){
                    Log.e("AppTest", "MentorProfileViewModel/ 회사 이름 가져오기 성공")

                    COMPANY_NAME = it.body()!!.companyName
                    IsGetCompanyNameSuccess.value = true

                    Log.e("AppTest", "회사 이름 : ${COMPANY_NAME}")

                }
                else{
                    Log.e("AppTest", "MentorProfileViewModel/ 회사 이름 가져오기 실패, status code : ${it.code()}")
                    IsGetCompanyNameSuccess.value = false
                    COMPANY_NAME = ""
                }

                progressBarStep3Visibility.value = false
            },
            {
                throwable -> Log.e("AppTest", "login error ${throwable}")
                progressBarStep3Visibility.value = false
                IsGetCompanyNameSuccess.value = false
                COMPANY_NAME = ""
            }
        )
    }


///////////////////////////////////////////////////////////////

    // step 4

    var isCareerDescriptionFilled = false  // 경력 내용 유무
    var CAREER_DESCRIPTION = "" // 데이터


////////////////////////////////////////////////////////////////

    // step 5
    var isSpecialityDesign = MutableLiveData<Boolean>()  // 선택된 전문분야가 '디자인' 인지
    var Step5Checked = MutableLiveData<Boolean>()  // 기업, 해시태그 하나 이상 씩 선택되었는지
    var COMPANY_SIZE = ""  // 데이터
    var HASHTAGS_LIST = ArrayList<HashTag>()  // 데이터

    var Regist_Mentor_Profile_Success = MutableLiveData<Boolean>()  // 멘토 프로필 등록 성공했는 지
    var progressBarStep5Visibility = MutableLiveData<Boolean>()  // 로딩 프로그레스바

    var isCompanySizeSelected = false
    var isDesignHashTagSuggestSelected = false
    var isItHashTagSuggestSelected = false

    fun selectCompanySize(companySize: String){
        COMPANY_SIZE = companySize
        isCompanySizeSelected = true

        if(isSpecialityDesign.value == true){  //  디자인 해시태그인 경우
            if(isDesignHashTagSuggestSelected == true)
                Step5Checked.value = true
        }
        else{
            if(isItHashTagSuggestSelected == true)
                Step5Checked.value = true
        }
    }

    fun selectDesignHashTag(flag : Boolean){
        isDesignHashTagSuggestSelected = flag

        if(isDesignHashTagSuggestSelected && isCompanySizeSelected)
            Step5Checked.value = true
        else
            Step5Checked.value = false
    }

    fun selectItHashTag(flag : Boolean){
        isItHashTagSuggestSelected = flag

        if(isItHashTagSuggestSelected && isCompanySizeSelected)
            Step5Checked.value = true
        else
            Step5Checked.value = false
    }

    fun clearHashTagList(){
        HASHTAGS_LIST.clear()
    }

    fun setHashTagList(hashTag: HashTag){
        HASHTAGS_LIST.add(hashTag)
    }

    ///////////////////////////////////////////////////////////////////////////////
    // step1 ~ step5 데이터로 멘토 프로필 등록 통신!
    @SuppressLint("CheckResult")
    fun registMentorProfile(){

        var mentorProfile = MentorProfileRequestDto(Constants.USER_NICKNAME, SIMPLE_INTRODUCTION, DETAILED_INTRODUCTION,
        SPECIALITY, SUBSPECIALITY_LIST, COMPANY_NAME, DEPARTMENT, POSITION, CAREER,
        CAREER_DESCRIPTION, COMPANY_SIZE, HASHTAGS_LIST)

        progressBarStep5Visibility.value = true

        registMentorProfileUseCase.registMentorProfile(Constants_gitignore.USER_TOKEN, mentorProfile).subscribe(
                {
                    if(it.code() == 201){

                        if(it.body()?.mentorId == null){ // 프로필 등록 실패
                            Regist_Mentor_Profile_Success.value = false
                        }
                        else{ // 프로필 등록 성공
                            Constants.CURRENT_MENTOR_ID = it.body()!!.mentorId
                            Log.e("AppTest", "MentorProfileViewModel/ 멘토 프로필 등록 성공 " +
                                    "멘토 id : ${Constants.CURRENT_MENTOR_ID}")

                            Regist_Mentor_Profile_Success.value = true
                        }

                    }
                    else{
                        Log.e("AppTest", "MentorProfileViewModel/ 프로필 등록 실패 " +
                                "status:${it.code()} ")

                        Regist_Mentor_Profile_Success.value = false
                    }

                    progressBarStep5Visibility.value = false
                },
                {
                    throwable -> Log.e("AppTest", "login error ${throwable}")

                    progressBarStep5Visibility. value = false
                    Regist_Mentor_Profile_Success.value = false
                }
        )
    }

}