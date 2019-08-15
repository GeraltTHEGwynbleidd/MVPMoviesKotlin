package com.gwynbleidd.mvpmovieskotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gwynbleidd.mvpmovieskotlin.R
import com.gwynbleidd.mvpmovieskotlin.retrofit.model.Data

class MoviesListAdapter(var context: Context?) : RecyclerView.Adapter<MoviesListAdapter.RecyclerViewholder>() {

     var mDataList: ArrayList<Data> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewholder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return RecyclerViewholder(itemView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewholder, position: Int) {
        val mData = mDataList[position]
        Glide
            .with(this.context!!)
            .load(mData.poster)
            .into(holder.thumbnail)
        holder.title.text = mData.title
        holder.genre.text = mData.genres.toString()
        holder.rating.text = mData.imdb_rating
        holder.releaseYear.text = mData.year
    }

    fun cleanAdapter(){
        mDataList.clear()
    }

    fun add(items : ArrayList<Data>) {
        val previousDataSize:Int = this.itemCount
        if(previousDataSize==0){
            mDataList.addAll(items)
            notifyItemInserted(1)
        }else {
            mDataList.addAll(items)
            notifyItemRangeInserted(previousDataSize,items.size)
        }
    }

    class RecyclerViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        var title: TextView = itemView.findViewById(R.id.title)
        var rating: TextView = itemView.findViewById(R.id.rating)
        var genre: TextView = itemView.findViewById(R.id.genre)
        var releaseYear: TextView = itemView.findViewById(R.id.releaseYear)
    }
}