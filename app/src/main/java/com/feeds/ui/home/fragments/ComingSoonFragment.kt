package com.feeds.ui.home.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.feeds.R
import com.feeds.models.Result
import com.feeds.models.Songs
import com.feeds.rest.ApiClient
import com.feeds.rest.exceptions.NoNetworkException
import com.feeds.ui.adapters.SongsAdapter
import com.feeds.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_coming_soon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ComingSoonFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_coming_soon, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh.setOnRefreshListener(this)
        getComingSoonSongs()
    }


    private fun getComingSoonSongs() {

        progressBar.visibility = View.VISIBLE

        val call = ApiClient.create().getComingSoonSongs()

        call.enqueue(object : Callback<Songs> {
            override fun onFailure(call: Call<Songs>?, t: Throwable?) {
                progressBar.visibility = View.GONE

                if (t is NoNetworkException) {
                    Toast.makeText(context, getString(R.string.check_internet), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, t?.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }

            override fun onResponse(call: Call<Songs>?, response: Response<Songs>?) {
                progressBar.visibility = View.GONE

                if (response?.isSuccessful!!) {

                    val songsList = response.body().feed.results

                    if (songsList.isNotEmpty()) {

                        val adapter = SongsAdapter(
                            context!!,
                            songsList,
                            object : SongsAdapter.OnSongClickListener {
                                override fun onSongClicked(result: Result) {

                                    val intent=Intent(context,DetailsActivity::class.java)
                                    intent.putExtra("result",result)
                                    startActivity(intent)
                                }

                            })

                        rvSongs.setHasFixedSize(true)
                        rvSongs.layoutManager = LinearLayoutManager(context)

                        rvSongs.adapter = adapter
                        adapter.notifyDataSetChanged()


                    } else {
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                    }


                }
            }

        })


    }

    override fun onRefresh() {

        swipeRefresh.isRefreshing = false
        getComingSoonSongs()
    }
}