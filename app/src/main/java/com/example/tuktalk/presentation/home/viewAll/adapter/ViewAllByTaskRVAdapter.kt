package com.example.tuktalk.presentation.home.viewAll.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.*
import com.example.tuktalk.domain.model.home.HomeTop5MentorRVitem
import com.example.tuktalk.domain.model.home.MentorListRVitem
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import com.example.tuktalk.presentation.search.adpater.SearchDesignRVadapter
import java.lang.RuntimeException

class ViewAllByTaskRVAdapter(
        private var dataSet : MutableList<MentorListRVitem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // 뷰 타입 오버라이드! 1. 빈 뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // 상단 empty 뷰
    inner class ViewType1ViewHolder(val binding: ItemEmptyViewBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 멘토리스트 아이템 뷰
    inner class ViewType2ViewHolder(val binding: ItemMentorListRecycler1Binding)
        :RecyclerView.ViewHolder(binding.root){

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
                        R.layout.item_mentor_list_recycler_1,
                        parent,
                        false
                )

                // 비율로 높이 설정하기  // recyclerview 영역이 0dp + left,right constraint = parent 로 해줘야 인식되는 듯
                var params = view.layoutParams
                params.height = parent.measuredWidth / 328 * 135

                ViewType2ViewHolder(ItemMentorListRecycler1Binding.bind(view))
            }

            else -> {
                throw RuntimeException("unknown item view type error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewAllByTaskRVAdapter.ViewType1ViewHolder){

        }
        else if(holder is ViewAllByTaskRVAdapter.ViewType2ViewHolder){
            holder.binding.tvMentorName.text = dataSet[position].mentorName  // 멘토 이름
            holder.binding.tvConmpanyName.text = dataSet[position].companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].task // 업무명
            holder.binding.tvHashTag.text = dataSet[position].hashTag
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<MentorListRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
        notifyDataSetChanged()
    }

}