package com.nemo.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyView2Binding
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemHomeRealtimeMenteeReviewRvBinding
import com.nemo.tuktalk.databinding.ItemMentorListRecycler1Binding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem
import com.nemo.tuktalk.domain.model.home.RealTimeReviewRVitem
import com.nemo.tuktalk.presentation.home.viewAll.adapter.ViewAllByTaskRVAdapter
import java.lang.RuntimeException

class RealTimeMenteeReviewRVAdpater(private var dataSet : MutableList<RealTimeReviewRVitem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 타입 오버라이드! 1. 빈 뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemviewType
    }

    // 좌측 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyView2Binding)
        :RecyclerView.ViewHolder(binding.root){

    }

    inner class ViewType2ViewHolder(val binding: ItemHomeRealtimeMenteeReviewRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1->{ // 좌측 empty 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_empty_view_2,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredWidth / 360 * 28

                ViewType1ViewHolder(ItemEmptyView2Binding.bind(view))
            }
            2->{ // 실제 포트폴리오 아이템 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_home_realtime_mentee_review_rv,
                        parent,
                        false
                )

                // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
                var params = view.layoutParams
                params.width = parent.measuredHeight / 230 * 234

                ViewType2ViewHolder(ItemHomeRealtimeMenteeReviewRvBinding.bind(view))
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
            holder.binding.tvMentorName.text = dataSet[position].reviewResponseDto.mentor.mentorNickname  // 멘토 이름
            holder.binding.tvCompanyName.text = dataSet[position].reviewResponseDto.mentor.companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].reviewResponseDto.mentor.subSpecialty // 업무명
            holder.binding.tvMenteeName.text = dataSet[position].reviewResponseDto.mentee.menteeNickname // 리뷰남긴 멘티명

            var originDate = dataSet[position].reviewResponseDto.reviewCreatedDateTime
            var filterDate = originDate.substring(0 until 10)
            holder.binding.tvReviewDate.text = filterDate // 리뷰남긴 날짜

            holder.binding.tvReviewContent.text = dataSet[position].reviewResponseDto.reviewDescription.replace(" ", "\u00A0") // 리뷰 내용

            //별점
            when(dataSet[position].reviewResponseDto.rating){
                0-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_0)
                1-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_1)
                2-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_2)
                3-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_3)
                4-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_4)
                5-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_5)
            }

            // 닉네임 첫 글자
            holder.binding.tvProfileFirstLetter.text = dataSet[position].reviewResponseDto.firstLetter // 닉네임 첫 글자

            // 프로필 배경색
            when(dataSet[position].reviewResponseDto.profileImageColor){
                "profileBlue" -> {
                    holder.binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_blue)
                    holder.binding.tvProfileFirstLetter.setTextColor(holder.itemView.resources.getColor(R.color.tuktalk_profileBlue_text))
                }
                "profileRed"->{
                    holder.binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_red)
                    holder.binding.tvProfileFirstLetter.setTextColor(holder.itemView.resources.getColor(R.color.tuktalk_profileRed_text))
                }
                "profileYellow"->{
                    holder.binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_yellow)
                    holder.binding.tvProfileFirstLetter.setTextColor(holder.itemView.resources.getColor(R.color.tuktalk_profileYellow_text))
                }
                "profileGray"->{
                    holder.binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_gray)
                    holder.binding.tvProfileFirstLetter.setTextColor(holder.itemView.resources.getColor(R.color.tuktalk_profileGray_text))
                }
                "profileGreen"->{
                    holder.binding.clProfile.setBackgroundResource(R.drawable.profile_image_circle_background_green)
                    holder.binding.tvProfileFirstLetter.setTextColor(holder.itemView.resources.getColor(R.color.tuktalk_profileGreen_text))
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<RealTimeReviewRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }

}