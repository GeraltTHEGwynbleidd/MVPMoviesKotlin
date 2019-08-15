package com.gwynbleidd.mvpmovieskotlin.mvp

import androidx.fragment.app.Fragment

interface MainActivityVP {
    interface View{
        fun setFragment(fragment :Fragment)
    }
    interface Presenter{
        fun getFragment(i:Int ,arg:String)
    }
}