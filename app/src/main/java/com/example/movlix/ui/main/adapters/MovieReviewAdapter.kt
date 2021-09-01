package com.example.movlix.ui.main.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movlix.R
import com.example.movlix.network.asp.models.Review
import kotlinx.android.synthetic.main.review_item.view.*

class MovieReviewAdapter(var context: Context, var activity: Activity, var data: MutableList<Review>)
    : RecyclerView.Adapter<MovieReviewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val reviewUser : TextView = itemView.tv_username
        val reviewDesc : TextView = itemView.tv_desc
        val reviewImage:ImageButton = itemView.ib_profile
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reviewUser.text = data[position].username
        holder.reviewDesc.text = data[position].reviewDesc
        holder.reviewImage.setImageResource(data[position].userImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}