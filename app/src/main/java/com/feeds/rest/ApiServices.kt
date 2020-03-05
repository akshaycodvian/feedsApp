package com.feeds.rest

import com.feeds.models.Songs
import com.feeds.utils.Constants.COMING_SOON
import com.feeds.utils.Constants.HOT_TRACKS
import com.feeds.utils.Constants.NEW_RELEASES
import com.feeds.utils.Constants.TOP_ALBUMS
import com.feeds.utils.Constants.TOP_SONGS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServices {

    @Headers("Content-Type:application/json")
    @GET(COMING_SOON)
    fun getComingSoonSongs():Call<Songs>

    @Headers("Content-Type:application/json")
    @GET(HOT_TRACKS)
    fun getHotTracks():Call<Songs>


    @Headers("Content-Type:application/json")
    @GET(NEW_RELEASES)
    fun getNewReleases():Call<Songs>


    @Headers("Content-Type:application/json")
    @GET(TOP_ALBUMS)
    fun getTopAlbums():Call<Songs>


    @Headers("Content-Type:application/json")
    @GET(TOP_SONGS)
    fun getTopSongs():Call<Songs>

}