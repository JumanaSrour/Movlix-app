package com.example.movlix.feature.homeMovies.presenter

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView
import com.example.movlix.feature.homeMovies.view.MovieView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.utils.Constants.Companion.page_number
import com.example.movlix.network.utils.Constants.Companion.page_size
import com.example.movlix.network.utils.PaginationListener
import com.example.movlix.utils.AppSharedFunctions

data class MoviePresenter(val mActivity: Activity, val mView: MovieView ) {
    lateinit var recyclerView: RecyclerView
    @ExperimentalStdlibApi
    @RequiresApi(Build.VERSION_CODES.M)
    public fun getMovie(){
        val params = ArrayMap<String, Any>()
        params["page_size"] =page_size
        params["page_number"] = page_number


        if (AppSharedFunctions.networkIsConnected(mActivity)) {
            UserRequest().getMovie(params, object : PaginationListener<ArrayList<Movie>> {
                override fun onSuccess(data: ArrayList<Movie>,num :Int) {
                    Log.e("------", "onSuccess: $data",)
//                    mView.returnMovie(data.movie)
                }

                override fun onFailure(message: String) {
                    Log.e("------------", message,)
                    mView.showErrorMsg(message)
                }

            })

        }
    }
}