package com.nemo.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nemo.tuktalk.R
import com.nemo.tuktalk.databinding.ItemHomeRealtimeMenteeReviewRvBinding
import com.nemo.tuktalk.domain.model.home.RealTimeMenteeReviewRVitem

class RealTimeMenteeReviewRVAdpater(private var dataSet : MutableList<RealTimeMenteeReviewRVitem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class MyViewHolder(val binding: ItemHomeRealtimeMenteeReviewRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_realtime_mentee_review_rv,
                parent,
                false
        )

        // 비율로 높이 설정하기
        var params = view.layoutParams
        params.width = parent.measuredHeight / 230 * 234

        return MyViewHolder(ItemHomeRealtimeMenteeReviewRvBinding.bind(view))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.binding.tvMentorName.text = dataSet[position].mentorName  // 멘토 이름
            holder.binding.tvCompanyName.text = dataSet[position].companyName // 회사명
            holder.binding.tvTaskName.text = dataSet[position].taskName // 업무명
            holder.binding.tvMenteeName.text = dataSet[position].menteeName // 리뷰남긴 멘티명
            holder.binding.tvReviewDate.text = dataSet[position].reviewDate // 리뷰남긴 날짜
            holder.binding.tvReviewContent.text = dataSet[position].reviewContent.replace(" ", "\u00A0") // 리뷰 내용

            //별점
            when(dataSet[position].star){
                0-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_0)
                1-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_1)
                2-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_2)
                3-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_3)
                4-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_4)
                5-> holder.binding.ivStar.setImageResource(R.drawable.ic_star_5)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<RealTimeMenteeReviewRVitem>){
        dataSet = newList
        notifyDataSetChanged()
    }

}