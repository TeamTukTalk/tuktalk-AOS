package com.nemo.tuktalk.domain.model.mypage.mentee.wishlist

import com.nemo.tuktalk.data.remote.dto.response.mentee.MenteeWishListResponseDto

data class WishListItem(
        var itemViewType : Int,
        var menteeWishListResponseDto: MenteeWishListResponseDto
)