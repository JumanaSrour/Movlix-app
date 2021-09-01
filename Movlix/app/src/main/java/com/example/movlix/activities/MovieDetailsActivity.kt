package com.example.movlix.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.ui.main.adapters.MovieCastAdapter
import com.example.movlix.ui.main.adapters.MovieReviewAdapter
import com.example.movlix.network.asp.models.AppBarStateChangeListener
import com.example.movlix.network.asp.models.Cast
import com.example.movlix.network.asp.models.Review
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieCastAdapter: MovieCastAdapter
    private lateinit var movieReviewAdapter: MovieReviewAdapter
    private lateinit var mAppBarStateChangeListener: AppBarLayout
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val castItem = arrayListOf<Cast>()
        castItem.add(Cast(castId = 1,
            castImageView = R.drawable.zack_s,
        castName = "Zack Snyder"))

        castItem.add(Cast(castId = 2,
        castImageView = R.drawable.ben_affleck,
        castName = "Ben Affleck"))

        castItem.add(Cast(castId = 3,
        castImageView = R.drawable.gal_gadot,
        castName = "Gal Gadot"))

        castItem.add(
            Cast(
            castId = 4,
                castImageView = R.drawable.amy_adams,
                castName = "Amy Adams"
        ))

        castItem.add(
            Cast(
                castId = 5,
                castImageView = R.drawable.henry_cavill,
                castName = "Henry Cavill"
            )
        )

        val reviewItem = arrayListOf<Review>()
        reviewItem.add(Review(
            reviewId = 1,
            userId = 1,
            username = "Roy McKenzie",
            userImage = R.drawable.profile_icon,
            reviewDesc = "The French Revolution constituted for the conscience of the dominant aristocratic class a fall from innocence, and upturning of the natural chain of events that resounded all over Europe"
        ))
        reviewItem.add(Review(
            reviewId = 2,
            userId = 2,
            username = "Bernard Goodman",
            userImage = R.drawable.profile_icon,
            reviewDesc = "The French Revolution constituted for the conscience of the dominant aristocratic class a fall from innocence, and upturning of the natural chain of events that resounded all over Europe"

        ))
        reviewItem.add(Review(
            reviewId = 3,
            userId = 3,
            username = "Bernice Frazier",
            userImage = R.drawable.profile_icon,
            reviewDesc = "The French Revolution constituted for the conscience of the dominant aristocratic class a fall from innocence, and upturning of the natural chain of events that resounded all over Europe"

        ))
        reviewItem.add(Review(
            reviewId = 4,
            userId = 4,
            username = "Jon Barnes",
            userImage = R.drawable.profile_icon,
            reviewDesc = "The French Revolution constituted for the conscience of the dominant aristocratic class a fall from innocence, and upturning of the natural chain of events that resounded all over Europe"

        ))


        movieCastAdapter = MovieCastAdapter(this, this, castItem)
        rv_cast.adapter = movieCastAdapter

        movieReviewAdapter = MovieReviewAdapter(this, this, reviewItem)
        rv_reviews.adapter = movieReviewAdapter

        mAppBarStateChangeListener = appbar
        mAppBarStateChangeListener.addOnOffsetChangedListener(object: AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                Log.d("State", "")
                when(state) {
                    State.COLLAPSED -> {
                        movie_title.visibility = View.VISIBLE
                        img_back.visibility = View.VISIBLE
                        txt_back.visibility = View.INVISIBLE
                    }
                    State.EXPANDED -> {
                        movie_title.visibility = View.INVISIBLE
                        img_back.visibility = View.INVISIBLE
                        txt_back.visibility = View.VISIBLE
                    }
                    State.IDLE -> { /* Do something */ }
                }
            }
        }
        )

    }
}