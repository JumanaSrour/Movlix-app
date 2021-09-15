package com.example.movlix.ui.main.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movlix.R
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.utils.storage.SharedPrefManager.Companion.movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    var context: Context,
    var onClick: OnClick,
    var activity: Activity, var data: MutableList<Movie>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movies: ArrayList<Movie>? = null
    private var listener: AdapterView.OnItemClickListener? = null
    private var isLoading = 1
    private var isLoadingAdded = false
    private var index = 0

    init {
        movies = ArrayList()
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies!!.size - 1 == position && isLoadingAdded) isLoading else TYPE_ITEM
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieName: TextView = itemView.movie_name
        val movieImage: ImageView = itemView.movie_image
        val movieRateNumber: TextView = itemView.tv_rate_number
        val movieVote: TextView = itemView.tv_count
        val movieRate: RatingBar = itemView.rb_rate
        val cardItem: CardView = itemView.cv_movie

        fun setData(productViewHolder: ViewHolder, item: Movie, position: Int) {
            movieRateNumber.text = item.movie_rate_number
            movieVote.text = item.movie_votes
            movieName.text = item.movieName
//            item.movie_rate.also {item.movieRate.numStars = it }
            movieImage.setImageResource(item.movieImage)
            cardItem.setOnClickListener {
//                lionClick.onClickItem(item, position)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view: View
        when (viewType) {
            TYPE_ITEM -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_last_add, parent, false)
                viewHolder = MovieVH(view)
            }
            isLoading -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.load_more, parent, false)
                viewHolder = LoadingVH(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_last_add, parent, false)
                viewHolder = MovieVH(view)
            }

        }
        return viewHolder
    }

    private fun getViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        viewHolder = MovieVH(view1)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
//                val productViewHolder = holder as ViewHolder
//                holder.setData(productViewHolder, getItem(position), position)
            }
        }
    }

    interface OnClick {
        fun onClickItem(data: Movie, position: Int)
    }


    fun add(movie: Movie) {
        movies!!.add(movie)
        notifyItemInserted(movies!!.size - 1)
    }

    fun addAll(movieList: List<Movie>) {
        for (mc in movieList) {
            add(mc)
        }
    }

    fun remove(movie: Movie) {
        val position = movies!!.indexOf(movie)
        if (position >= movies!!.size) {
            movies!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAll() {
        this.movies!!.clear()
        notifyDataSetChanged()

    }
    fun clear() {
        isLoadingAdded = false
        while (getItemCount() > 0) {
            remove(data[0])
        }
    }

    fun isEmpty(): Boolean {
        return getItemCount() == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(movie)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = movies!!.size - 1
        val movieItem = getItem(position)
        if (movieItem != null) {
            movies!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): Movie {
        return movies!!.get(position)
    }

    companion object {
        val TYPE_ITEM = 0
    }

    inner class LoadingVH internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    //    class LoadingVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    public fun loadingVH(itemView: View): View {
        return (itemView)
    }


    inner class MovieVH internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    public fun movieVH(itemView: View): View {
        return (itemView)
    }
}
