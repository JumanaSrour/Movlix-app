package com.example.movlix.feature.addFavoriteItem.presenter

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.movlix.feature.addFavoriteItem.view.FavoriteView
import com.example.movlix.feature.movieDetails.view.MovieDetailsActivity
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.FavoriteResponse
import com.example.movlix.network.utils.Constants.Companion.movie_id
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.AppSharedFunctions

data class FavoritePresenter(
    val mActivity: Activity,
    val mView: FavoriteView
){
    @RequiresApi(Build.VERSION_CODES.M)
    public fun addFavoriteItem(){
        val params = androidx.collection.ArrayMap<String, Any>()
        params["movie_id"] = movie_id

        if (AppSharedFunctions.networkIsConnected(mActivity)){
            UserRequest().addFavoriteItem(params, object : RequestListener<FavoriteResponse>{
                override fun onSuccess(data: FavoriteResponse) {
                    Log.e("--------", "onSuccess: $data")
//                    star_movie.setBackgroundResource(R.drawable.star_filled)

                }

                override fun onFailure(message: String) {
                    Log.e("--------", "onFailure: $message")
                    mView.showErrorMsg(message)
//                    star_movie.setBackgroundResource(R.drawable.star_movie)
                }

            })
        }
    }
}