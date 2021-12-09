package com.nemo.tuktalk.presentation.home.viewAll.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemRealtimeMenteeReviewRecycler1Binding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem_viewAll
import java.lang.RuntimeException

class ViewAllMenteeReviewRVAdapter(
        private var dataSet : MutableList<RealTimeMenteeReviewRVitem_viewAll>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // 뷰 타입 오버라이드! 1. 빈 뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        : RecyclerView.ViewHolder(binding.root){

    }

    // 멘토리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemRealtimeMenteeReviewRecycler1Binding)
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
                params.height = parent.measuredWidth / 328 * 16

                ViewType1ViewHolder(ItemEmptyViewBinding.bind(view))
            }
            2->{ // 실제 포트폴리오 아이템 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_realtime_mentee_review_recycler_1,
                        parent,
                        false
                )

                // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
                /*var params = view.layoutParams
                params.height = parent.measuredWidth / 328 * 135 */

                ViewType2ViewHolder(ItemRealtimeMenteeReviewRecycler1Binding.bind(view))
            }

            else -> {
                throw RuntimeException("unknown item view type error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewAllMenteeReviewRVAdapter.ViewType1ViewHolder){

        }
        else if(holder is ViewAllMenteeReviewRVAdapter.ViewType2ViewHolder){
            holder.binding.tvMentorName.text = dataSet[position].mentorName  // 멘토 이름
            holder.binding.tvCompanyName.text = dataSet[position].companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].taskName // 업무명
            holder.binding.tvMenteeName.text = dataSet[position].menteeName
            holder.binding.tvReviewDate.text = dataSet[position].reviewDate
            holder.binding.tvReviewContent2.text = dataSet[position].reviewContent.replace(" ", "\u00A0") // 리뷰 내용

            //별점
            when(dataSet[position].star){
                0-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_0)
                1-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_1)
                2-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_2)
                3-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_3)
                4-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_4)
                5-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_5)
            }


            // 더보기 클릭 시 리뷰 전체 내용 보여야 함
            holder.binding.llViewallReview.setOnClickListener {
                if(!dataSet[position].viewAllSelected){ // 더보기x 에서 더보기 누를 시
                    holder.binding.tvReviewContent2.maxLines = 6  // 우선 더보기 누를 시 최대 6줄로 변경
                    dataSet[position].viewAllSelected = true
                    holder.binding.ivViewallReview.setImageResource(R.drawable.ic_up_arrow)
                }
                else{
                    holder.binding.tvReviewContent2.maxLines = 3
                    dataSet[position].viewAllSelected = false
                    holder.binding.ivViewallReview.setImageResource(R.drawable.ic_down_arrow)
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<RealTimeMenteeReviewRVitem_viewAll>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
        notifyDataSetChanged()
    }

}