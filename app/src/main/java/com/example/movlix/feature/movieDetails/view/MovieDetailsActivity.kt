package com.example.movlix.feature.movieDetails.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.feature.favortieItem.presenter.FavoritePresenter
import com.example.movlix.feature.favortieItem.view.FavoriteView
import com.example.movlix.feature.login.view.LoginActivity
import com.example.movlix.ui.main.adapters.MovieCastAdapter
import com.example.movlix.ui.main.adapters.MovieReviewAdapter
import com.example.movlix.network.asp.models.AppBarStateChangeListener
import com.example.movlix.network.asp.models.Cast
import com.example.movlix.network.asp.models.Review
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), FavoriteView {

    private lateinit var mPresenter: FavoritePresenter
    private lateinit var movieCastAdapter: MovieCastAdapter
    private lateinit var movieReviewAdapter: MovieReviewAdapter
    private lateinit var mAppBarStateChangeListener: AppBarLayout
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initPresenter()
          btn_login_details.setOnClickListener {
              startActivity(Intent(this, LoginActivity::class.java))
              finish()
          }

        val castItem = arrayListOf<Cast>()
        castItem.add(Cast(castId = 1,
            castImageView = R.drawable.zack_s,
        castName = getString(R.string.zack_snyder)))

        castItem.add(Cast(castId = 2,
        castImageView = R.drawable.ben_affleck,
        castName = getString(R.string.ben)))

        castItem.add(Cast(castId = 3,
        castImageView = R.drawable.gal_gadot,
        castName = getString(R.string.gal)))

        castItem.add(
            Cast(
            castId = 4,
                castImageView = R.drawable.amy_adams,
                castName = getString(R.string.amy)
        ))

        castItem.add(
            Cast(
                castId = 5,
                castImageView = R.drawable.henry_cavill,
                castName = getString(R.string.henry)
            )
        )

        val reviewItem = arrayListOf<Review>()
        reviewItem.add(Review(
            reviewId = 1,
            userId = 1,
            username = getString(R.string.roy),
            userImage = R.drawable.profile_icon,
            reviewDesc = getString(R.string.the_french)
        ))
        reviewItem.add(Review(
            reviewId = 2,
            userId = 2,
            username = getString(R.string.bernard),
            userImage = R.drawable.profile_icon,
            reviewDesc = getString(R.string.the_french)

        ))
        reviewItem.add(Review(
            reviewId = 3,
            userId = 3,
            username = getString(R.string.bernice),
            userImage = R.drawable.profile_icon,
            reviewDesc = getString(R.string.the_french)
        ))
        reviewItem.add(Review(
            reviewId = 4,
            userId = 4,
            username = getString(R.string.jon),
            userImage = R.drawable.profile_icon,
            reviewDesc = getString(R.string.the_french)
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

        //Favorite/unFavorite item
        star_movie.setOnClickListener {
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPresenter() {
        mPresenter = FavoritePresenter(this, this)
        mPresenter.addFavoriteItem()
    }

    override fun showErrorMsg(msg: String?) {
        Log.e("------", "showErrorMsg: $msg", )
    }
}