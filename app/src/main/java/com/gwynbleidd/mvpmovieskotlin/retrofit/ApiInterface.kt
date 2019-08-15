package com.gwynbleidd.mvpmovieskotlin.retrofit

import com.gwynbleidd.mvpmovieskotlin.retrofit.model.modelData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("movies")
    fun getMoviesList(@Query("page") page: String): Call<modelData>

    @GET("movies")
    fun getMoviesList(@Query("page") page: String , @Query("q") query :String ) :Call<modelData>

}