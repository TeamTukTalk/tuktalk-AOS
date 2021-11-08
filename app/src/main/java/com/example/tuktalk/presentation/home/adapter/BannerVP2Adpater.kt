package com.example.tuktalk.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuktalk.R
import com.example.tuktalk.databinding.ItemBannerImageListBinding
import com.example.tuktalk.databinding.ItemSearchDesignRecycler3LoadingBinding

class BannerVP2Adpater(bannerImgList : MutableList<Int>) : RecyclerView.Adapter<BannerVP2Adpater.PagerViewHolder>() {
    var item = bannerImgList



    inner class PagerViewHolder(val binding: ItemBannerImageListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_banner_image_list,
                parent,
                false
        )

        return PagerViewHolder(ItemBannerImageListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.binding.ivBanner.setImageResource(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}