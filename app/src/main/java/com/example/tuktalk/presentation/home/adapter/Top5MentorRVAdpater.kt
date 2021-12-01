package com.example.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.data.remote.dto.response.home.Top5MentorResponseDto
import com.example.tuktalk.databinding.ItemHomeTop5MentorRvBinding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item

class Top5MentorRVAdpater(private var dataSet : MutableList<Top5MentorResponseDto>,
                          val selectMentor:() -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class MyViewHolder(val binding: ItemHomeTop5MentorRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_top5_mentor_rv,
                parent,
                false
        )

        // 비율로 높이 설정하기
        var params = view.layoutParams
        params.width = parent.measuredHeight / 135 * 280

        return MyViewHolder(ItemHomeTop5MentorRvBinding.bind(view))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.binding.tvName.text = dataSet[position].nickname  // 멘토 이름
            holder.binding.tvCompany.text = dataSet[position].companyName // 회사명
            holder.binding.tvDepartment.text = dataSet[position].department // 업무명

            var hashTagText = ""
            dataSet[position].hashTags.forEach{
                hashTagText += "#" + it.hashTag + " "
            }
            holder.binding.tvHashTag.text = hashTagText

            //holder.binding.tvHashTag.text = dataSet[position].hashTag

            // 아이템 뷰 클릭 시 해당 멘토정보 페이지 이동동
           holder.binding.root.setOnClickListener {
                selectMentor.invoke()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<Top5MentorResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }

}