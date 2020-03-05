package com.feeds.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.feeds.R
import com.feeds.models.Result
import kotlinx.android.synthetic.main.item_song.view.*

class SongsAdapter(val context:Context, private val list: List<Result>,val onSongClickListener: OnSongClickListener): RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_song,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val result=list[position]

        holder.tvReleaseDate.text=result.releaseDate
        holder.tvSongName.text=result.name
        holder.tvSongArtist.text=result.artistName

        Glide.with(context).applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(result.artworkUrl100).into(holder.ivSongImage)

        holder.itemView.setOnClickListener {
            onSongClickListener.onSongClicked(result)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivSongImage: AppCompatImageView =itemView.ivSongImage
        val tvSongName: AppCompatTextView =itemView.tvSongName
        val tvSongArtist: AppCompatTextView =itemView.tvSongArtist
        val tvReleaseDate: AppCompatTextView =itemView.tvReleaseDate
    }

    interface OnSongClickListener{
        fun onSongClicked(result: Result)
    }
}