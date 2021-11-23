package com.example.tuktalk.presentation.mypage.mentor.mentorProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuktalk.domain.model.mypage.mentor.profileRegist.SubSpecialitySelect

class MentorProfileViewModel: ViewModel() {
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

    var subspecialityList = ArrayList<String>()  // 상세분야 리스트

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
            subspecialityList.add(ld_sub_speciality_1.value!!)

        if(ld_sub_speciality_2.value != null)
            subspecialityList.add(ld_sub_speciality_2.value!!)

        if(ld_sub_speciality_3.value != null)
            subspecialityList.add(ld_sub_speciality_3.value!!)
    }

////////////////////////////////////////////////////////////////////

    // step3






}