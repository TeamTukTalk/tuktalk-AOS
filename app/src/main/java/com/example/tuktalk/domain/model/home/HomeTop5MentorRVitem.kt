package com.example.tuktalk.domain.model.home

/**
 * Domain layer 에서 필요한 데이터만 Dto 에서 가져와서 model 로 사용
 */

data class HomeTop5MentorRVitem(
        val profileImage: Int,
        val mentorName : String,
        val companyName : String,
        val task : String,
        val hashTag : String,
        val itemViewType : Int
)