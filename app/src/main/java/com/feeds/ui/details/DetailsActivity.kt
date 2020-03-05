package com.feeds.ui.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.feeds.R
import com.feeds.models.Result
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        val resultData=intent.getSerializableExtra("result") as Result
        setData(resultData)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(result: Result) {

        var genreList = ""

        if(result.genres.isNotEmpty()){
            for (genre in result.genres){
                genreList=genreList+genre.name+","
            }

            tvSongGenre.text =getString(R.string.genre)+ genreList.substring(0, genreList.length - 1)

        }else{
            tvSongGenre.visibility= View.GONE
        }

        tvSongName.text =getString(R.string.song_name)+ result.name
        tvSongArtist.text =getString(R.string.artist_name)+ result.artistName
        tvSongReleaseDate.text =getString(R.string.release_date)+ result.releaseDate
        tvCopyRightInfo.text = getString(R.string.copyright)+result.copyright

        Glide.with(this).load(result.artworkUrl100).into(ivSongImage)

    }
}
