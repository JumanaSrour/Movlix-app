package com.example.movlix.feature.homeMovies.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.AbsListView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movlix.R
import com.example.movlix.feature.homeMovies.presenter.MoviePresenter
import com.example.movlix.feature.login.view.LoginActivity
import com.example.movlix.feature.movieDetails.view.MovieDetailsActivity
import com.example.movlix.feature.profile.view.ProfileActivity
import com.example.movlix.ui.main.adapters.MovieAdapter
import com.example.movlix.network.asp.models.CustomDialog
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.asp.models.CustomDialog.CustomDialogListener
import com.example.movlix.utils.storage.SharedPrefManager
import com.sundus.abjw.moge.utils.PaginationLinearScrollListener
import kotlinx.android.synthetic.main.activity_home_movies.*

class HomeMoviesActivity : AppCompatActivity(), MovieAdapter.OnClick , MovieView{
    private val TOTAL_PAGES: Int = 10
    private val PAGE_START: Int = 1
    private var isLastPage: Boolean = true
    private var currentPage: Int= 1
   private var isLoading: Boolean = false
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var mPresenter: MoviePresenter
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movies)
        initPreseneter()

        val movieItems = arrayListOf<Movie>()

        movieItems.add(
            Movie(
                movieId = 1,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with),
                movie_rate = 3,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )
        movieItems.add(
            Movie(
                movieId = 2,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa),
                movie_rate = 3,
                movie_rate_number = "8",
                movie_votes = "300"
            )
        )
        movieItems.add(
            Movie(
                movieId = 3,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with),
                movie_rate = 4,
                movie_rate_number = "6",
                movie_votes = "220"
            )
        )
        movieItems.add(
            Movie(
                movieId = 4,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa),
                movie_rate = 4,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )
        movieItems.add(
            Movie(
                movieId = 5,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with),
                movie_rate = 3,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )
        movieItems.add(
            Movie(
                movieId = 6,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa),
                movie_rate = 3,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )
        movieItems.add(
            Movie(
                movieId = 7,
                movieImage = R.drawable.movie_img1,
                movieName = getString(R.string.conversations_with),
                movie_rate = 3,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )
        movieItems.add(
            Movie(
                movieId = 8,
                movieImage = R.drawable.movie_img2,
                movieName = getString(R.string.This_is_how_it_alwa),
                movie_rate = 3,
                movie_rate_number = "7",
                movie_votes = "257"
            )
        )


        movieAdapter = MovieAdapter(this, this, this, movieItems)
        rv_movies.adapter = movieAdapter
        rv_movies.addOnScrollListener(object : PaginationLinearScrollListener(layoutManager = LinearLayoutManager(applicationContext)){
            override fun onScrolledList(dx: Int, dy: Int) {
                TODO("Not yet implemented")
            }

            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override fun setEnabled(refresh: Boolean) {
                TODO("Not yet implemented")
            }

            override fun _isLastPage(): Boolean {
                return isLastPage
            }

            override fun _isLoading(): Boolean {
                return isLastPage
            }
        })
        loadFirstPage()


        ib_logout.setOnClickListener {
            createDialog()
        }

        iv_profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadNextPage() {
        if (currentPage <= TOTAL_PAGES) {
            movieAdapter.addLoadingFooter()
        } else {
            isLastPage = true
        }
        getDataFromServer()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadFirstPage() {
        currentPage = PAGE_START
        var data = ArrayList<Movie>()
        isLastPage = false
        getDataFromServer()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public fun getDataFromServer() {
    }

    private fun initPreseneter() {
       mPresenter  = MoviePresenter(this, this)
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

    override fun returnMovie(movie: Movie?) {
        Log.e("--------", "returnMovie: $movie ", )
    }

    override fun showErrorMsg(msg: String?) {
        Log.e("------", "showErrorMsg: $msg", )
    }


}