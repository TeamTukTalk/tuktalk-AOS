package com.example.tuktalk.presentation.search.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.data.remote.dto.response.search.SearchMentorResponseDto
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler2Binding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler3LoadingBinding
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import okhttp3.Challenge
import java.lang.RuntimeException

class SearchDirectRVadapter(
        private var dataSet : MutableList<SearchMentorResponseDto>,
        val selectMentor:(mentorId : Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    // 포트폴리오 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemSearchDesignRecycler2Binding)
        :RecyclerView.ViewHolder(binding.root){

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // 실제 포트폴리오 아이템 뷰
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_search_design_recycler_2,
            parent,
            false
        )

        // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
       /* var params = view.layoutParams
        params.height = parent.measuredHeight / 527 * 180*/

        return ViewType2ViewHolder(ItemSearchDesignRecycler2Binding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

         if(holder is ViewType2ViewHolder){

             holder.binding.tvMentorName.text = dataSet[position].nickname  // 멘토 이름
             holder.binding.tvConmpanyName.text = dataSet[position].companyName // 회사명
             holder.binding.tvTaskName.text = dataSet[position].department // 부서명

             holder.binding.tvProfileFirstLetter.text = dataSet[position].firstLetter // 프로필 닉네임 이미지 첫글자

             // 프로필 이미지 배경색
             when(dataSet[position].profileImageColor){
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
             dataSet[position].hashTags.forEach{
                 hashTagText += "#" + it.hashTag + " "
             }
             holder.binding.tvHashTag.text = hashTagText


             // 아이템 선택 시 해당 멘토 상세페이지 이동 연동하기
             holder.binding.root.setOnClickListener {
                 selectMentor.invoke(dataSet[position].id)
             }

         }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    fun updateList(newList: MutableList<SearchMentorResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }

}