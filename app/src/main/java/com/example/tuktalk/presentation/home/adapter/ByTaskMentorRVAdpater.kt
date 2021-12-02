package com.example.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.data.remote.dto.response.home.ByTaskMentorResponseDto
import com.example.tuktalk.databinding.ItemHomeByTaskMentorRvBinding
import com.example.tuktalk.databinding.ItemHomeTop5MentorRvBinding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.domain.model.home.ByTaskMentorRVitem
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item

class ByTaskMentorRVAdpater(private var dataSet : MutableList<ByTaskMentorResponseDto>,
                            val selectMentor:(mentorId : Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class MyViewHolder(val binding: ItemHomeByTaskMentorRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_by_task_mentor_rv,
                parent,
                false
        )

        // 비율로 높이 설정하기
        var params = view.layoutParams
        params.width = parent.measuredHeight / 140 * 156

        return MyViewHolder(ItemHomeByTaskMentorRvBinding.bind(view))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.binding.tvMentorName.text = dataSet[position].nickname  // 멘토 이름
            holder.binding.tvConmpanyName.text = dataSet[position].companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].department // 업무명

            holder.binding.tvProfileFirstLetter.text = dataSet[position].firstLetter // 닉네임 첫 글자

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

            // 아이템 뷰 클릭 시 해당 멘토정보 페이지 이동동
            holder.binding.root.setOnClickListener {
                selectMentor.invoke(dataSet[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<ByTaskMentorResponseDto>){
        // dataSet clear 할 필요 없이 바로 newList 할당하기,  clear 하면 처음에만 보이고 그다음 update 시 리스트가 보이지 않았음
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }

}