package com.example.tuktalk.domain.model.home

/**
 * Domain layer 에서 필요한 데이터만 Dto 에서 가져와서 model 로 사용
 */

data class RealTimeMenteeReviewRVitem(
        val profileImage: Int,
        val mentorName : String,
        val companyName : String,
        val taskName : String,
        val star : Int,
        val menteeName : String,
        val reviewDate : String, // 일단 String 형태 -> 추후 서버 데이터와 맞추기!!!
        val reviewContent : String, // 리뷰 내용!!
        val itemViewType : Int
)