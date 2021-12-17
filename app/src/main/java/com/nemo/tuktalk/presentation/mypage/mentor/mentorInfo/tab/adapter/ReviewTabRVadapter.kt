package com.nemo.tuktalk.presentation.mypage.mentor.mentorInfo.tab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemRealtimeMenteeReviewRecycler1Binding
import com.nemo.tuktalk.databinding.ItemRvMentorinfoTabReviewListBinding
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem2
import com.nemo.tuktalk.domain.model.mypage.mentor.info.ReviewTabRVitem
import com.nemo.tuktalk.presentation.home.viewAll.adapter.ViewAllMenteeReviewRVAdapter
import okio.blackholeSink
import org.koin.core.parameter.parametersOf
import java.lang.RuntimeException

class ReviewTabRVadapter(
        private var dataSet : MutableList<ReviewTabRVitem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // 뷰 타입 오버라이드! 1. 빈 뷰 2. 포트폴리오 리스트 아이템
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        : RecyclerView.ViewHolder(binding.root){

    }

    // 멘티 후기 리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemRvMentorinfoTabReviewListBinding)
        : RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->{ // 상단 텍스트 1개를 위한 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_empty_view,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.height = parent.measuredWidth / 328 * 15

                ViewType1ViewHolder(ItemEmptyViewBinding.bind(view))
            }
            2->{ // 실제 후기 리스트 아이템 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_rv_mentorinfo_tab_review_list,
                        parent,
                        false
                )

                ViewType2ViewHolder(ItemRvMentorinfoTabReviewListBinding.bind(view))
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

            // 후기 남긴 날짜
            holder.binding.tvReviewDate.text =
                    dataSet[position].menteeReviewListResponseDto.reviewCreatedDateTime.substring(0 until 10)

            // 후기 남긴 멘티 닉네임
            holder.binding.tvMenteeNickname.text = dataSet[position].menteeReviewListResponseDto.mentee.menteeNickname

            // 리뷰 내용
            holder.binding.tvReviewDescription.text =
                dataSet[position].menteeReviewListResponseDto.reviewDescription.replace(" ", "\u00A0")

            //별점
            when(dataSet[position].menteeReviewListResponseDto.rating){
                0-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_0)
                1-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_1)
                2-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_2)
                3-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_3)
                4-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_4)
                5-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_5)
            }

            // 더보기(리뷰 내용 다 보이게) 기능 -> 임시로 막은 상태 ->  뷰 높이가 달라지는 오류 현재 발생 -> ViewPager 부분 다시 수정하기
           /* holder.binding.llViewallReview.setOnClickListener {
                if(!dataSet[position].viewAllSelected){ // 더보기x 에서 더보기 누를 시
                    holder.binding.tvReviewDescription.maxLines = 6  // 우선 더보기 누를 시 최대 6줄로 변경
                    dataSet[position].viewAllSelected = true
                    holder.binding.ivViewallReview.setImageResource(R.drawable.ic_up_arrow)
                }
                else{
                    holder.binding.tvReviewDescription.maxLines = 3
                    dataSet[position].viewAllSelected = false
                    holder.binding.ivViewallReview.setImageResource(R.drawable.ic_down_arrow)
                }
            }
*/

        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }


    fun updateList(newList: MutableList<ReviewTabRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }


}