package com.gwynbleidd.mvpmovieskotlin.presenter

import com.gwynbleidd.mvpmovieskotlin.fragment.MainFragment
import com.gwynbleidd.mvpmovieskotlin.fragment.SearchFragment
import com.gwynbleidd.mvpmovieskotlin.mvp.MainActivityVP
import android.os.Bundle




class MainActivityPresenter(private val view: MainActivityVP.View) : MainActivityVP.Presenter {

    override fun getFragment(i: Int, arg: String) {
        if (i == 0) {
            view.setFragment(MainFragment())
        } else {
            val fragmentSearch = SearchFragment()
            val argument = Bundle()
            argument.putString("SEARCH", arg)
            fragmentSearch.arguments = argument
            view.setFragment(fragmentSearch)
        }
    }

}
