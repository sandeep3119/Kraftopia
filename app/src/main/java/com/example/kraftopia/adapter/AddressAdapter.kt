package com.example.kraftopia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kraftopia.R
import com.example.kraftopia.databinding.AddressListItemBinding

class AddressAdapter(context: Context, data: List<AddressItem>) :
    RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    var addressList = data
    class AddressViewHolder(val binding:AddressListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:AddressItem){
            with(binding){
                addressName.text=item.name
                addressPhoneNumber.text=item.phoneNumber
                addressLocality.text=item.locality
                addressCity.text=item.city
                addressState.text=item.state
                addressPinCode.text=item.pinCode.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding=AddressListItemBinding.inflate(LayoutInflater.from(parent.context))
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addressList[position]
        holder.bind(address)

    }

    override fun getItemCount(): Int {
        return addressList.size
    }


}