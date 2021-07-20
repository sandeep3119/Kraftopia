package com.example.kraftopia.ui.main.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kraftopia.R
import com.example.kraftopia.adapter.AddressAdapter
import com.example.kraftopia.adapter.AddressItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Address : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addressRecyclerView: RecyclerView = view.findViewById(R.id.address_recycler_view)
        val data: List<AddressItem> = listOf(
            AddressItem(
                "Raj Sharma",
                "976543210",
                "Kv Zone # *2 Street",
                "Moscow",
                "Brazil",
                653210
            ),
            AddressItem(
                "Sandeep rana",
                "9876543219",
                "Near Temple Village Abcd",
                "Bhubaneswar",
                "Odisha",
                655794
            ),
            AddressItem(
                "Sandeep Sharma",
                "9876543219",
                "Near Temple Village Abcd",
                "Bhubaneswar",
                "Delhi",
                1252857
            ),
            AddressItem(
                "Sandeep Sharma",
                "9876543219",
                "Near Temple Village Abcd",
                "Bhubaneswar",
                "Delhi",
                1252857
            )
        )
        val addressAdapter = AddressAdapter(requireContext(), data)
        addressRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = addressAdapter

        }
    }

}