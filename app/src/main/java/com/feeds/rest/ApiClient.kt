package com.feeds.rest

import com.feeds.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        fun create(): ApiServices {

            val gson = GsonBuilder().setLenient().create()

            val httpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)

            httpClient.addInterceptor(RequestInterceptor())

            val retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }

}