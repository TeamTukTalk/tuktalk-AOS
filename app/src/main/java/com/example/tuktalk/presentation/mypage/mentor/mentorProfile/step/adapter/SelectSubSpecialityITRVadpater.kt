package com.example.tuktalk.presentation.mypage.mentor.mentorProfile.step.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ItemEmptyViewBinding
import com.example.tuktalk.databinding.ItemMentorListRecycler1Binding
import com.example.tuktalk.databinding.ItemSelectSpecialityRvBinding
import com.example.tuktalk.domain.model.home.MentorListRVitem

class SelectSubSpecialityITRVadpater (
    private var dataSet : MutableList<String>
    , val selectSpeciality:(speciality: String, index: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // 멘토리스트 아이템 뷰
    inner class ViewType1ViewHolder(val binding: ItemSelectSpecialityRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_select_speciality_rv,
            parent,
            false
        )

        // 비율로 높이 설정하기
        var params = view.layoutParams
        params.height = parent.measuredHeight / 6

        return ViewType1ViewHolder(ItemSelectSpecialityRvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is SelectSubSpecialityITRVadpater.ViewType1ViewHolder){
            holder.binding.tvSpeciality.text = dataSet[position] // 전문분야 텍스트

            holder.binding.root.setOnClickListener {
                selectSpeciality.invoke(dataSet[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList: MutableList<String>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
        notifyDataSetChanged()
    }

}