package com.gwynbleidd.mvpmovieskotlin.mvp

import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data

interface FragmentMainVP {
    interface View{
        fun makeAdapter(mData : ArrayList<Data> )
        fun showProgress()
        fun hideProgress()

    }
    interface Presenter{
        fun getMovies(page : String)
    }
}