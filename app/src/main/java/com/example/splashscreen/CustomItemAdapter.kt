package com.example.splashscreen

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class CustomItemAdapter(private var context: Context, private var data: ArrayList<CustomItem>) :
    RecyclerView.Adapter<CustomItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.text)
        var desc: TextView = itemView.findViewById(R.id.textD)
        var video: VideoView = itemView.findViewById(R.id.video)
        var downloadButton: Button = itemView.findViewById(R.id.btnDownload)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val videoItem = data[position]

        holder.title.text = data[position].title
        holder.desc.text = data[position].desc
        holder.video.setVideoPath(videoItem.videoUrl)
        holder.video.setOnClickListener {
            holder.video.start()
        }

        holder.downloadButton.setOnClickListener {
            val download = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val videoUri = Uri.parse(videoItem.videoUrl)
            val getVideo = DownloadManager.Request(videoUri)
            getVideo.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getVideo)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}