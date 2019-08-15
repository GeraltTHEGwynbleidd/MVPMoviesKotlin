package com.gwynbleidd.mvpmovieskotlin.fragment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gwynbleidd.mvpmovieskotlin.adapter.MoviesListAdapter
import com.gwynbleidd.mvpmovieskotlin.mvp.FragmentSearchVP
import com.gwynbleidd.mvpmovieskotlin.presenter.searchFragmentPresenter
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data

class SearchFragment : Fragment(), FragmentSearchVP.View {

    internal lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var dialog: ProgressDialog
    private lateinit var adapter: MoviesListAdapter
    private lateinit var presenter: searchFragmentPresenter
    private lateinit var rootView: View



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(com.gwynbleidd.mvpmovieskotlin.R.layout.fragment_search, container, false)
        adapter = MoviesListAdapter(context)
        dialog = ProgressDialog(context)
        presenter = searchFragmentPresenter(this)
        return rootView
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = rootView.findViewById(com.gwynbleidd.mvpmovieskotlin.R.id.rec_movie_search_Frag)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        val searchTxt = arguments?.get("SEARCH").toString()
        presenter.getMoviesBySearch("1", searchTxt)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    val totalItemCount = linearLayoutManager.itemCount
                    if(totalItemCount>=10)
                    presenter.getMoviesBySearch((totalItemCount / 10 + 1).toString(),searchTxt)
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

    override fun cleanAdapter() {
        adapter.cleanAdapter()
    }
}