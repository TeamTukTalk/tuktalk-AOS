package com.example.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ItemHomeTop5MentorRvBinding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler1Binding
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item

class Top5MentorRVAdpater( private var dataSet : MutableList<HomeTop5MentorRVitem>) :
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
            holder.binding.tvName.text = dataSet[position].mentorName  // 멘토 이름
            holder.binding.tvCompany.text = dataSet[position].companyName // 회사명
            holder.binding.tvTask.text = dataSet[position].task // 업무명
            holder.binding.tvHashTag.text = dataSet[position].hashTag
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<HomeTop5MentorRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }

}