package com.nemo.tuktalk.common

object Constants {

    // 일단 멘토로 설정 / 멘토 = 0, 멘티 = 1
    var USER_MODE = 0   // 멘토 or 멘티

    // 하단바 번호 / 홈 = 0
    var BOTTOM_NAVI_NUM = 0

    // 탐색 탭에서 마지막으로 봤던 프래그먼트  // 처음은 0 !!
    var SEARCH_FRAGMENT = 0   //  0 = 분야선택, 1 = design 분야,  2 = it/개발 분야,  3 = 직접검색

    var SELECT_CATEGORY_LIST = ArrayList<String>()
    // 회원가입 정보 입력 화면에서 중간에 나가면 선택 카테고리 리스트 초기화 -> BreakAwayDialogFragment 에서!

    var USER_NICKNAME = ""
    var USER_PROFILE_IMAGE_COLOR = ""
    var USER_FIRST_LETTER = ""
    var USER_EMAIL = ""

    var CURRENT_MENTOR_ID = 0


    var IS_CERTIFIED_MENTOR = false  // 멘토 기업메일 인증 여부

}