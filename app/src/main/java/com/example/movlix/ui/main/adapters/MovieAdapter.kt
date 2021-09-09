package com.example.movlix.ui.main.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movlix.R
import com.example.movlix.network.asp.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    var context: Context,
    var onClick: OnClick,
    var activity: Activity, var data: MutableList<Movie>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(){


     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
          val movieName: TextView = itemView.movie_name
          val movieImage : ImageView = itemView.movie_image
         val movieRateNumber: TextView = itemView.tv_rate_number
         val movieVote: TextView = itemView.tv_count
         val movieRate: RatingBar = itemView.rb_rate
         val cardItem : CardView = itemView.cv_movie

     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieRateNumber.text = data[position].movie_rate_number
        holder.movieVote.text = data[position].movie_votes
        holder.movieName.text = data[position].movieName
        data[position].movie_rate.also { holder.movieRate.numStars = it}
        holder.movieImage.setImageResource(data[position].movieImage)
        holder.cardItem.setOnClickListener {
            onClick.onClickItem(data[position], position)
        }
    }

    interface OnClick{
        fun onClickItem(data: Movie, position: Int)
    }
    }
