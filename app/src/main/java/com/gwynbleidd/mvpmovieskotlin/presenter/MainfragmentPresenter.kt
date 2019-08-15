package com.gwynbleidd.mvpmovieskotlin.presenter

import com.gwynbleidd.mvpmovieskotlin.mvp.FragmentMainVP
import com.gwynbleidd.mvpmovieskotlin.retrofit.ApiClient
import com.gwynbleidd.mvpmovieskotlin.retrofit.ApiInterface
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.modelData
import retrofit2.Call
import retrofit2.Callback
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data
import retrofit2.Response


class MainfragmentPresenter(var view: FragmentMainVP.View) : FragmentMainVP.Presenter {
    override fun getMovies(page: String) {
        view.showProgress()
        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)
        val dataFromCall: Call<modelData> = apiService.getMoviesList(page)
        dataFromCall.enqueue(object : Callback<modelData> {
            override fun onFailure(call: Call<modelData>, t: Throwable) {
                view.hideProgress()
            }

            override fun onResponse(call: Call<modelData>, response: Response<modelData>) {
                if (response.body() != null) {
                    view.makeAdapter(response.body()?.data as ArrayList<Data>)
                }
                view.hideProgress()
            }
        })

    }

}