package com.example.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ItemHomeByTaskMentorRvBinding
import com.example.tuktalk.databinding.ItemHomeTop5MentorRvBinding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.domain.model.home.ByTaskMentorRVitem
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item

class ByTaskMentorRVAdpater(private var dataSet : MutableList<ByTaskMentorRVitem>) :
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
            holder.binding.tvMentorName.text = dataSet[position].mentorName  // 멘토 이름
            holder.binding.tvConmpanyName.text = dataSet[position].companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].task // 업무명

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<ByTaskMentorRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }

}