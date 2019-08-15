package com.gwynbleidd.mvpmovieskotlin.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.annotation.SuppressLint
import android.os.Bundle
import android.app.ProgressDialog
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.gwynbleidd.mvpmovieskotlin.adapter.MoviesListAdapter
import com.gwynbleidd.mvpmovieskotlin.mvp.FragmentMainVP
import com.gwynbleidd.mvpmovieskotlin.presenter.MainfragmentPresenter
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data


class MainFragment : Fragment(), FragmentMainVP.View {

    internal lateinit var linearLayoutManager: LinearLayoutManager
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var rootView: View
    internal lateinit var dialog: ProgressDialog
    internal lateinit var adapter: MoviesListAdapter
    internal lateinit var presenter: MainfragmentPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(com.gwynbleidd.mvpmovieskotlin.R.layout.fragment_main, container, false)
        adapter = MoviesListAdapter(context)
        dialog = ProgressDialog(context)
        presenter = MainfragmentPresenter(this)
        return rootView
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = rootView.findViewById(com.gwynbleidd.mvpmovieskotlin.R.id.rec_movie_main_Frag)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        presenter.getMovies("1")
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    val totalItemCount = linearLayoutManager.itemCount
                    presenter.getMovies((totalItemCount / 10 + 1).toString())
                }
            }
        })
    }

    override fun makeAdapter(mData: ArrayList<Data>) {
        adapter.add(mData)
    }

    override fun showProgress() {
        dialog.show()
    }

    override fun hideProgress() {
        dialog.hide()
    }

}

