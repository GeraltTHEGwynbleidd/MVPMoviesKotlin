package com.gwynbleidd.mvpmovieskotlin.presenter

import com.gwynbleidd.mvpmovieskotlin.mvp.FragmentSearchVP
import com.gwynbleidd.mvpmovieskotlin.retrofit.ApiClient
import com.gwynbleidd.mvpmovieskotlin.retrofit.ApiInterface
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.modelData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchFragmentPresenter(var view: FragmentSearchVP.View) : FragmentSearchVP.Presenter {
    override fun getMoviesBySearch(page: String, query: String) {
        view.showProgress()
        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)
        val dataFromCall: Call<modelData> = apiService.getMoviesList(page,query)
        dataFromCall.enqueue(object : Callback<modelData> {
            override fun onFailure(call: Call<modelData>, t: Throwable) {
                view.hideProgress()
            }

            override fun onResponse(call: Call<modelData>, response: Response<modelData>) {
                if (page.equals("1")) {
                    view.cleanAdapter()
                }
                if (response.body() != null) {
                    view.makeAdapter(response.body()?.data as ArrayList<Data>)
                }
                view.hideProgress()
            }
        })
    }
}