package com.example.movlix.feature.profile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.activities.SettingsActivity
import com.example.movlix.feature.homeMovies.view.HomeMoviesActivity
import com.example.movlix.feature.movieDetails.view.MovieDetailsActivity
import com.example.movlix.feature.profile.presenter.ProfilePresenter
import com.example.movlix.ui.main.adapters.MovieAdapter
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.internal.userAgent

class ProfileActivity : AppCompatActivity(), MovieAdapter.OnClick, ProfileView{
    private lateinit var mPresenter: ProfilePresenter
    private lateinit var movieAdapter: MovieAdapter
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initPresenter()

        val movieItems = arrayListOf<Movie>()

        movieItems.add(Movie(
            movieId = 1,
            movieImage = R.drawable.movie_img1,
            movieName = getString(R.string.conversations_with),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 2,
            movieImage = R.drawable.movie_img2,
            movieName = getString(R.string.This_is_how_it_alwa),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 3,
            movieImage = R.drawable.movie_img1,
            movieName = getString(R.string.conversations_with),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 4,
            movieImage = R.drawable.movie_img2,
            movieName = getString(R.string.This_is_how_it_alwa),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 5,
            movieImage = R.drawable.movie_img1,
            movieName = getString(R.string.conversations_with),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 6,
            movieImage = R.drawable.movie_img2,
            movieName = getString(R.string.This_is_how_it_alwa),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 7,
            movieImage = R.drawable.movie_img1,
            movieName = getString(R.string.conversations_with),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))
        movieItems.add(Movie(
            movieId = 8,
            movieImage = R.drawable.movie_img2,
            movieName = getString(R.string.This_is_how_it_alwa),
            movie_rate = 3,
            movie_rate_number = "7",
            movie_votes = "257"
        ))

        movieAdapter = MovieAdapter(this, this,this,  movieItems)
        rv_favortie.adapter = movieAdapter

        ib_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        btn_back_profile.setOnClickListener {
            startActivity(Intent(this, HomeMoviesActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPresenter() {
        mPresenter = ProfilePresenter(this, this)
        mPresenter.getUser()
        tv_username.text = (SharedPrefManager.user.name)
    }


    override fun onClickItem(data: Movie, position: Int) {
        startActivity(Intent(this, MovieDetailsActivity::class.java))
    }

    override fun returnUser(user: User) {
        Toast.makeText(applicationContext, "$user",Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMsg(msg: String?) {
        Log.e("------", "showErrorMsg: $msg", )

    }
}