package com.example.kraftopia.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kraftopia.R

class CategoryAdapter(context: Context, data: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>() {
    var categoryList = data

    class CategoryAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val categoryImageView: ImageView = view.findViewById(R.id.category_image)
        val categoryName:TextView=view.findViewById(R.id.category_name)
        override fun onClick(v: View?) {
            Log.d("yolo", "clicked")
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapterViewHolder {
        val inflate=LayoutInflater.from(parent.context).inflate(R.layout.category_grid_item,null)
        return CategoryAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: CategoryAdapterViewHolder, position: Int) {
        val categoryItem=categoryList[position]
        Glide.with(holder.itemView)
            .load(categoryItem.image)
            .fitCenter()
            .into(holder.categoryImageView)
        holder.categoryName.text=categoryItem.categoryName
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}