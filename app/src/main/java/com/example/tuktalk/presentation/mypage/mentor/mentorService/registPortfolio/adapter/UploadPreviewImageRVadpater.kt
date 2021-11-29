package com.example.tuktalk.presentation.mypage.mentor.mentorService.registPortfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.*
import com.example.tuktalk.domain.model.mypage.mentor.portfolio.UploadPreviewImage
import com.example.tuktalk.domain.model.search.PortfolioRV_item
import java.lang.RuntimeException

class UploadPreviewImageRVadpater(
        private var dataSet : MutableList<UploadPreviewImage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // 뷰 타입 오버라이드! 1. 설명 텍스트뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemViewType
    }

    // empty item view
    inner class ViewType1ViewHolder(val binding: ItemRvPreviewImageEmptyBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 사진 선택 활성화 및 현재 사진 개수  item view
    inner class ViewType2ViewHolder(val binding: ItemRvPreviewImageFirstBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 사진 담을 아이템 뷰
    inner class ViewType3ViewHolder(val binding: ItemRvPreviewImageItemBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_rv_preview_image_empty,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredHeight * 4 / 100

                ViewType1ViewHolder(ItemRvPreviewImageEmptyBinding.bind(view))
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_rv_preview_image_first,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredHeight

                ViewType2ViewHolder(ItemRvPreviewImageFirstBinding.bind(view))
            }
            3-> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_rv_preview_image_item,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredHeight

                ViewType3ViewHolder(ItemRvPreviewImageItemBinding.bind(view))
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

        }
        else if(holder is ViewType3ViewHolder){

        }
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }

    fun updateList(newList: MutableList<UploadPreviewImage>){
        dataSet = newList
        notifyDataSetChanged()
    }

    fun clearList(){
        dataSet.clear()
    }
}