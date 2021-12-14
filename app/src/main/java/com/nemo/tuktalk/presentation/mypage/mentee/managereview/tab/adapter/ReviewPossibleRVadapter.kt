package com.nemo.tuktalk.presentation.mypage.mentee.managereview.tab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemRvMenteeRecentPortfolioBinding
import com.nemo.tuktalk.databinding.ItemRvMenteeRecentPortfolioForReviewBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.recenthistory.RecentHistoryItem
import com.nemo.tuktalk.presentation.mypage.mentee.recentPortfolio.adapter.MenteeRecentPorfolioRVadapter
import java.lang.RuntimeException

class ReviewPossibleRVadapter(
        private var dataSet : MutableList<RecentHistoryItem>,
        val writeReview:(mentorId : Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    // 뷰 타입 오버라이드!   ->  1 = 빈 뷰  /  2 = 찜 멘토 리스트 아이템
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 멘토리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemRvMenteeRecentPortfolioForReviewBinding)
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
                        R.layout.item_rv_mentee_recent_portfolio_for_review,
                        parent,
                        false
                )

                ViewType2ViewHolder(ItemRvMenteeRecentPortfolioForReviewBinding.bind(view))
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

            holder.binding.tvNickname.text = dataSet[position].menteeRecentHistoryResponseDto.mentorNickname // 멘토 이름

            var originDate = dataSet[position].menteeRecentHistoryResponseDto.createdDateTime
            var filterDate = originDate.substring(0 until 10)

            holder.binding.tvDate.text = filterDate
            holder.binding.tvPortfolioDescription.text = dataSet[position].menteeRecentHistoryResponseDto.description // 설명


            // 리뷰 작성하기  버튼 터치 시 리뷰 작성 액티비티로 이동하기
            holder.binding.btnWriteReview.setOnClickListener {
                writeReview.invoke(dataSet[position].menteeRecentHistoryResponseDto.mentorId)
            }


        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<RecentHistoryItem>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }
}