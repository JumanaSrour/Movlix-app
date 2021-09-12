package com.example.movlix.feature.homeMovies.presenter

import android.app.Activity
import android.os.Build
import android.util.ArrayMap
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.movlix.feature.homeMovies.view.MovieView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.Movie
import com.example.movlix.network.asp.models.MovieResponse
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.AppSharedFunctions

data class MoviePresenter(val mActivity: Activity, val mView: MovieView ) {

    @RequiresApi(Build.VERSION_CODES.M)
    public fun getMovie(){
        val params = androidx.collection.ArrayMap<String, Any>()
        params["total_pages"] = 9
        params["current_page"] = 1
        params["total_records"] = 9

        // function to test the scroll and total number

        if (AppSharedFunctions.networkIsConnected(mActivity)) {
            UserRequest().getMovie(params, object : RequestListener<MovieResponse> {
                override fun onSuccess(data: MovieResponse) {
                    Log.e("------", "onSuccess: $data",)
                    mView.returnMovie(data.items)
                }

                override fun onFailure(message: String) {
                    Log.e("------------", message,)
                    mView.showErrorMsg(message)
                }

            })

        }
    }
}