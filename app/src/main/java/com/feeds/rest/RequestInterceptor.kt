package com.feeds.rest

import android.content.Context
import android.net.ConnectivityManager
import com.feeds.FeedsApp
import com.feeds.rest.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        if(!isConnected()){
            throw NoNetworkException()
        }

        val r=chain?.request()?.newBuilder()

        return chain?.proceed(r?.build())!!

    }

    private fun isConnected():Boolean {

        var connection=false

        val cm = FeedsApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null)
        {
            // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
            {
                connection=true
            }
            else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            {
                connection=true
            }
        }
        else
        {
            connection=false
        }

        return connection
    }
}