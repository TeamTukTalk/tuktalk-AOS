package com.nemo.tuktalk.presentation.mypage.mentee.wishlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemEmptyViewBinding
import com.nemo.tuktalk.databinding.ItemMentorListRecycler1Binding
import com.nemo.tuktalk.databinding.ItemRvMenteeWishListBinding
import com.nemo.tuktalk.domain.model.mypage.mentee.wishlist.WishListItem
import com.nemo.tuktalk.domain.model.search.SearchDesignMentorList
import com.nemo.tuktalk.presentation.search.adpater.SearchDesignRVadapter
import java.lang.RuntimeException

class MenteeWishListRVadapter(
        private var dataSet : MutableList<WishListItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 타입 오버라이드!   ->  1 = 빈 뷰  /  2 = 찜 멘토 리스트 아이템
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 멘토리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemRvMenteeWishListBinding)
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
                        R.layout.item_rv_mentee_wish_list,
                        parent,
                        false
                )

                // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
                /*var params = view.layoutParams
                params.height = parent.measuredWidth / 328 * 135*/

                ViewType2ViewHolder(ItemRvMenteeWishListBinding.bind(view))

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

            holder.binding.tvMentorName.text = dataSet[position].menteeWishListResponseDto.nickname  // 멘토 이름
            holder.binding.tvConmpanyName.text = dataSet[position].menteeWishListResponseDto.companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].menteeWishListResponseDto.department // 부서명

            holder.binding.tvProfileFirstLetter.text = dataSet[position].menteeWishListResponseDto.firstLetter  // 프로필 첫 글자

            // 프로필 배경색
            when(dataSet[position].menteeWishListResponseDto.profileImageColor){
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

            // 해쉬태그 설정
            var hashTagText = ""
            dataSet[position].menteeWishListResponseDto.hashTags.forEach{
                hashTagText += "#" + it.hashTag + " "
            }
            holder.binding.tvHashTag.text = hashTagText


            // 아이템 선택 시 해당 멘토 상세페이지 이동 연동하기
            holder.binding.root.setOnClickListener {



            }


        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    fun updateList(newList: MutableList<WishListItem>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }

}