package com.gwynbleidd.mvpmovieskotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gwynbleidd.mvpmovieskotlin.R
import com.gwynbleidd.mvpmovieskotlin.presenter.MainActivityPresenter
import android.widget.EditText
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.gwynbleidd.mvpmovieskotlin.mvp.MainActivityVP


class MainActivity : AppCompatActivity(), MainActivityVP.View, View.OnClickListener {

    var searchTxt: EditText? = null
    var presenter: MainActivityPresenter? = null

    @Suppress("ImplicitThis")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchbtn: Button? = findViewById(R.id.search_btn)
        searchbtn?.setOnClickListener(this)
        searchTxt = findViewById(R.id.search_text)
        presenter = MainActivityPresenter(this)
        presenter?.getFragment(0,"")
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClick(p0: View?) {
        presenter?.getFragment(1, searchTxt?.text.toString())
    }
}
