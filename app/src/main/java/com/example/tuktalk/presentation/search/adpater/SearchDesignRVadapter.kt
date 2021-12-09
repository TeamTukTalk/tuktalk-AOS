package com.example.tuktalk.presentation.search.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler2Binding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler3LoadingBinding
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import com.example.tuktalk.domain.model.search.SearchDesignMentorList
import okhttp3.Challenge
import java.lang.RuntimeException

class SearchDesignRVadapter(
        private var dataSet : MutableList<SearchDesignMentorList>,
        val selectMentor:(mentorId : Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //var dataSet = mutableListOf<PortfolioRV_item>()

    // 뷰 타입 오버라이드! 1. 설명 텍스트뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 텍스트 뷰
    inner class ViewType1ViewHolder(val binding: ItemSearchDesignRecycler1Binding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 포트폴리오 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemSearchDesignRecycler2Binding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 다음 페이지 정보 요청 시 로딩 프로그레스바 뷰
    inner class ViewType3ViewHolder(val binding: ItemSearchDesignRecycler3LoadingBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            1->{ // 상단 텍스트 1개를 위한 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_search_design_recycler_1,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.height = parent.measuredHeight / 527 * 80

                ViewType1ViewHolder(ItemSearchDesignRecycler1Binding.bind(view))
            }
            2->{ // 실제 포트폴리오 아이템 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_search_design_recycler_2,
                        parent,
                        false
                )

                // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
               /* var params = view.layoutParams
                params.height = parent.measuredWidth / 360 * 135*/

                ViewType2ViewHolder(ItemSearchDesignRecycler2Binding.bind(view))
            }
            3 -> { // 로딩 뷰
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_search_design_recycler_3_loading,
                        parent,
                        false
                )

                ViewType3ViewHolder(ItemSearchDesignRecycler3LoadingBinding.bind(view))
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
            holder.binding.tvMentorName.text = dataSet[position].searchMentorResponseDto.nickname  // 멘토 이름
            holder.binding.tvConmpanyName.text = dataSet[position].searchMentorResponseDto.companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].searchMentorResponseDto.department // 부서명

            holder.binding.tvProfileFirstLetter.text = dataSet[position].searchMentorResponseDto.firstLetter

            // 프로필 배경색
            when(dataSet[position].searchMentorResponseDto.profileImageColor){
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
            dataSet[position].searchMentorResponseDto.hashTags.forEach{
                hashTagText += "#" + it.hashTag + " "
            }
            holder.binding.tvHashTag.text = hashTagText


            // 아이템 선택 시 해당 멘토 상세페이지 이동 연동하기
            holder.binding.root.setOnClickListener {
                selectMentor.invoke(dataSet[position].searchMentorResponseDto.id)
            }


        }
        else if(holder is ViewType3ViewHolder){

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    fun updateList(newList: MutableList<SearchDesignMentorList>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }

}