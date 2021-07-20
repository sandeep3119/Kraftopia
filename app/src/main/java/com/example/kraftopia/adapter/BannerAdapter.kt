package com.example.kraftopia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kraftopia.R
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter(context: Context, data: List<BannerItem>) :
    SliderViewAdapter<BannerAdapter.BannerAdapterViewHolder>() {
    var bannerList = data

    class BannerAdapterViewHolder(view: View) : SliderViewAdapter.ViewHolder(view),
        View.OnClickListener {
        val imageView: ImageView = view.findViewById(R.id.banner_image)
        override fun onClick(v: View?) {
            Log.d("yolo", "clicked")
        }

    }

    override fun getCount(): Int {
       return bannerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerAdapter.BannerAdapterViewHolder {
        val inflate = LayoutInflater.from(parent?.context).inflate(R.layout.banner_shop_item, null)
        return BannerAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(
        viewHolder: BannerAdapter.BannerAdapterViewHolder?,
        position: Int
    ) {
        val bannerItem = bannerList[position]
        Glide.with(viewHolder!!.itemView)
            .load(bannerItem.image)
            .fitCenter()
            .into(viewHolder.imageView)

    }


}