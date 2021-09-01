package com.example.movlix.activities

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import com.example.movlix.R
import com.example.movlix.feature.login.view.LoginActivity
import com.example.movlix.feature.profile.presenter.ProfilePresenter
import com.example.movlix.feature.profile.view.ProfileActivity
import com.example.movlix.ui.main.adapters.MovieAdapter
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import com.example.movlix.utils.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_home_movies.*

class HomeMoviesActivity : AppCompatActivity(), MovieAdapter.OnClick {
    private lateinit var movieAdapter: MovieAdapter
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movies)


        val movieItems = arrayListOf<Movie>()

        movieItems.add(
            Movie(
                movieId = 1,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with)
            )
        )
        movieItems.add(
            Movie(
                movieId = 2,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa)
            )
        )
        movieItems.add(
            Movie(
                movieId = 3,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with)
            )
        )
        movieItems.add(
            Movie(
                movieId = 4,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa)
            )
        )
        movieItems.add(
            Movie(
                movieId = 5,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with)
            )
        )
        movieItems.add(
            Movie(
                movieId = 6,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa)
            )
        )
        movieItems.add(
            Movie(
                movieId = 7,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with)
            )
        )
        movieItems.add(
            Movie(
                movieId = 8,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa)
            )
        )


        movieAdapter = MovieAdapter(this, this, this, movieItems)
        rv_movies.adapter = movieAdapter



        ib_logout.setOnClickListener {
            createDialog()
        }

        iv_profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }


    }


    private fun createDialog() {
        val dialog = CustomDialog().newInstance(
            getString(R.string.log_out),
            getString(R.string.are_you_sure_logout),
            getString(R.string.accept),
            getString(R.string.cancel)
        )
        dialog.show(supportFragmentManager, "CustomDialogFragment")
        dialog.onClickListener(object : CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                SharedPrefManager.clear()
                dialog.dismiss()
                startActivity(LoginActivity.newInstance(mActivity = this@HomeMoviesActivity))
            }

            override fun onDialogNegativeClick(str: String) {
                dialog.dismiss()
            }

        })
    }


    override fun onClickItem(data: Movie, position: Int) {
        startActivity(Intent(this, MovieDetailsActivity::class.java))
    }


}