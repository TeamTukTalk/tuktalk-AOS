package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemRvMenteeDoneReviewBinding
import com.nemo.tuktalk.databinding.ItemRvMenteeRecentPortfolioBinding
import com.nemo.tuktalk.databinding.ItemRvMenteeRecentPortfolioForReviewBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.domain.model.mypage.mentee.reviewlist.ReviewListRvItem
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.adapter.MenteeRecentPorfolioRVadapter
import java.lang.RuntimeException

class ReviewDoneRVadapter(
        private var dataSet : MutableList<ReviewListRvItem>
        // 터치시 해당 리뷰 상세페이지 가기 연동하기
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    // 뷰 타입 오버라이드!   ->  1 = 빈 뷰  /  2 = 찜 멘토 리스트 아이템
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 후기 리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemRvMenteeDoneReviewBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_empty_view,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.height = parent.measuredWidth / 360 * 16

                ViewType1ViewHolder(ItemEmptyViewBinding.bind(view))
            }
            2-> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_rv_mentee_done_review,
                        parent,
                        false
                )

                ViewType2ViewHolder(ItemRvMenteeDoneReviewBinding.bind(view))
            }
            else -> {
                throw RuntimeException("unknown item view type error")
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewType1ViewHolder){

        }
        else if(holder is ViewType2ViewHolder){

            holder.binding.tvMentorNickname.text = dataSet[position].menteeReviewListResponseDto.mentor.mentorNickname // 멘토 이름

            var originPortfolioDate = dataSet[position].menteeReviewListResponseDto.portfolioViewedDateTime
            var filterPortfolioDate = originPortfolioDate.substring(0 until 10)
            holder.binding.tvPortfolioDate.text = filterPortfolioDate

            var originReviewDate = dataSet[position].menteeReviewListResponseDto.reviewCreatedDateTime
            var filterReviewDate = originReviewDate.substring(0 until 10)
            holder.binding.tvReviewDate.text = filterReviewDate


            holder.binding.tvReviewDescription.text = dataSet[position].menteeReviewListResponseDto.reviewDescription.replace(" ", "\u00A0") // 리뷰 내용

            //별점
            when(dataSet[position].menteeReviewListResponseDto.rating){
                0-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_0)
                1-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_1)
                2-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_2)
                3-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_3)
                4-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_4)
                5-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_5)
            }

            // 리뷰 아이템뷰 터치 시 리뷰 상세 액티비티로 이동하기 연동하기



        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<ReviewListRvItem>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }
}