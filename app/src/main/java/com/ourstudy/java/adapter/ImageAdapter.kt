package com.ourstudy.java.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ourstudy.R
import com.ourstudy.java.model.Model


class ImageAdapter(private var ctx: Context,private val list:ArrayList<Model>) : RecyclerView.Adapter<ImageAdapter.VideoInfoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoInfoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_image, parent, false)
        return VideoInfoHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoInfoHolder, position: Int) {

        Glide.with(ctx).load(list[position].video).asBitmap().into(holder.img)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VideoInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img: ImageView = itemView.findViewById<View>(R.id.img) as ImageView
    }
}