package com.gwynbleidd.mvpmovieskotlin.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val Base_url = "http://moviesapi.ir/api/v1/"
    var retrofit : Retrofit?=null

    fun getClient() : Retrofit?{
        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(Base_url)
                .client(defaultClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun defaultClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(7,TimeUnit.SECONDS)
            .writeTimeout(7,TimeUnit.SECONDS)
            .readTimeout(7,TimeUnit.SECONDS)
            .build()
    }

}