package com.example.kraftopia.ui.main.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kraftopia.R
import com.example.kraftopia.adapter.FeedVideoAdapter
import com.example.kraftopia.adapter.FeedVideoItem
import com.example.kraftopia.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Feed : Fragment() {


    private lateinit var binding: FragmentFeedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feedData = listOf(
            FeedVideoItem(
                "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
                "My new Video",
                "Hello friends this my new Video",
                "Dancing Doll"
            ),
            FeedVideoItem(
                "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
                "My new Video part 2",
                "Hello friends this my new Video Again",
                "Sleeping Moto"
            )
        )
        binding.videoViewPager.adapter=FeedVideoAdapter(requireActivity().applicationContext,feedData)

    }
}