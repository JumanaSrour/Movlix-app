package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.example.movlix.R
import com.example.movlix.ui.main.adapters.MovieAdapter
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import kotlinx.android.synthetic.main.activity_home_movies.*

class HomeMoviesActivity : AppCompatActivity(), MovieAdapter.OnClick{

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var CollapsingToolbarLayout: Lifecycle.State
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movies)



        val movieItems = arrayListOf<Movie>()

        movieItems.add(Movie(
            movieId = 1,
            movieImage = R.drawable.movie_img1,
            movieName = "Conversations with "
        ))
        movieItems.add(Movie(
            movieId = 2,
            movieImage = R.drawable.movie_img2,
            movieName = "This Is How It Alwa"
        ))
        movieItems.add(Movie(
            movieId = 3,
            movieImage = R.drawable.movie_img1,
            movieName = "Conversations with "
        ))
        movieItems.add(Movie(
            movieId = 4,
            movieImage = R.drawable.movie_img2,
            movieName = "This Is How It Alwa"
        ))
        movieItems.add(Movie(
            movieId = 5,
            movieImage = R.drawable.movie_img1,
            movieName = "Conversations with "
        ))
        movieItems.add(Movie(
            movieId = 6,
            movieImage = R.drawable.movie_img2,
            movieName = "This Is How It Alwa"
        ))
        movieItems.add(Movie(
            movieId = 7,
            movieImage = R.drawable.movie_img1,
            movieName = "Conversations with "
        ))
        movieItems.add(Movie(
            movieId = 8,
            movieImage = R.drawable.movie_img2,
            movieName = "This Is How It Alwa"
        ))


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
            "Log out",
            "Are you sure you want to Log out?",
            "Accept",
            "Cancel"
        )
        dialog.show(supportFragmentManager, "CustomDialogFragment")
        dialog.onClickListener(object : CustomDialogListener {
            override fun onDialogPositiveClick(str: String) {
                dialog.dismiss()
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