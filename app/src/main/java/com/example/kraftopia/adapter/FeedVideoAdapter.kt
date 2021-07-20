package com.example.kraftopia.adapter

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.kraftopia.R

class FeedVideoAdapter(context: Context,data:List<FeedVideoItem>):RecyclerView.Adapter<FeedVideoAdapter.FeedViewHolder>() {
    val feedData=data
    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val videoView:VideoView=view.findViewById(R.id.video_view)
        val videoTitle:TextView=view.findViewById(R.id.video_title)
        val videoDescription:TextView=view.findViewById(R.id.video_description)
        val videoProductName:TextView=view.findViewById(R.id.video_product_name)
        val videoProgressBar:ProgressBar=view.findViewById(R.id.video_progressBar)
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
        fun setVideoData( videoItem: FeedVideoItem){
            videoTitle.text=videoItem.videoTitle
            videoDescription.text=videoItem.videoDescription
            videoProductName.text=videoItem.product
            val uri= Uri.parse(videoItem.videoUrl)
            videoView.setVideoURI(uri)
            videoView.setOnPreparedListener {
                videoProgressBar.visibility=View.GONE
                it.start()
                val videoRatio=(it.videoWidth/it.videoHeight).toFloat()
                val screenRatio=(videoView.width/videoView.height).toFloat()
                val scale=videoRatio/screenRatio
                if(scale>=1f){
                    videoView.scaleX=scale
                }else{
                    videoView.scaleY=scale
                }
            }
            videoView.setOnCompletionListener {
                it.start()
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedVideoAdapter.FeedViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.video_container,parent,false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedVideoAdapter.FeedViewHolder, position: Int) {

        holder.setVideoData(feedData[position])
    }

    override fun getItemCount(): Int {
        return feedData.size
    }
}