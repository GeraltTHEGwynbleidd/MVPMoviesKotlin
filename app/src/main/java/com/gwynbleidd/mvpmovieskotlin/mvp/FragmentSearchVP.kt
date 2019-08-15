package com.gwynbleidd.mvpmovieskotlin.mvp

import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data

interface FragmentSearchVP {
    interface View{
        fun makeAdapter(mData : ArrayList<Data> )
        fun showProgress()
        fun hideProgress()
        fun cleanAdapter()

    }
    interface Presenter{
        fun getMoviesBySearch(page : String, query:String)
    }
}