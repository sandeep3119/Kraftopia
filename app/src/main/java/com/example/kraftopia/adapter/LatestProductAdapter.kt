package com.example.kraftopia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kraftopia.R

class LatestProductAdapter(context: Context, data: List<LatestProductItem>) :
    RecyclerView.Adapter<LatestProductAdapter.LatestProductAdapterViewHolder>() {
    var latestProductList = data

    class LatestProductAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val latestProductImage: ImageView = view.findViewById(R.id.latest_product_image)
        val latestProductPrice: TextView =view.findViewById(R.id.latest_product_price)
        val latestProductName: TextView =view.findViewById(R.id.latest_product_name)
        override fun onClick(v: View?) {
            Log.d("yolo", "clicked")
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestProductAdapterViewHolder {
        val inflate= LayoutInflater.from(parent.context).inflate(R.layout.latest_product_grid_item,null)
        return LatestProductAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: LatestProductAdapterViewHolder, position: Int) {
        val product=latestProductList[position]
        Glide.with(holder.itemView)
            .load(product.productImage)
            .fitCenter()
            .into(holder.latestProductImage)
        holder.latestProductName.text=product.productName
        holder.latestProductPrice.text=product.price
    }

    override fun getItemCount(): Int {
        return latestProductList.size
    }


}