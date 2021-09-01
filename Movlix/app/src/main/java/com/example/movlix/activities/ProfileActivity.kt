package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movlix.R
import com.example.movlix.ui.main.adapters.MovieAdapter
import com.example.movlix.network.asp.models.Movie
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), MovieAdapter.OnClick{
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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

        movieAdapter = MovieAdapter(this, this,this,  movieItems)
        rv_favortie.adapter = movieAdapter

        ib_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        btn_back_profile.setOnClickListener {
            startActivity(Intent(this, HomeMoviesActivity::class.java))
        }
    }


    override fun onClickItem(data: Movie, position: Int) {
        startActivity(Intent(this, MovieDetailsActivity::class.java))
    }
}