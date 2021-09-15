package com.example.movlix.network.asp.features

import android.util.Log
import androidx.collection.ArrayMap
import com.example.movlix.network.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.models.*
import com.example.movlix.network.utils.PaginationListener
import com.example.movlix.network.utils.RequestListener
import com.example.movlix.utils.storage.SharedPrefManager
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


class UserRequest {
    private val retrofitClient = RetrofitClient()
    private val gson = Gson()
    private val mView: LoginView? = null

    fun createUser(map: ArrayMap<String, Any>, listener: RequestListener<LoginResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofitClient.createUser(map)
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = gson.fromJson(
                        appResponse.status_code.toString(),
                        LoginResponse::class.java
                    ) as LoginResponse
                    listener.onSuccess(data = data)
                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }

    }

    fun loginUser(map: ArrayMap<String, Any>, listener: RequestListener<LoginResponse>) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = retrofitClient.loginUser(map)
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = gson.fromJson(
                        appResponse.getResult(),
                        LoginResponse::class.java
                    ) as LoginResponse
                    Log.e("///***", data.token.toString())
                    SharedPrefManager.saveUser(data.user)
                    SharedPrefManager.saveToken(data.token)
                    mView?.returnUser(data.user)
                    listener.onSuccess(data = data)
                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }
    }

    fun resetPassword(
        map: ArrayMap<String, Any>,
        listener: RequestListener<ForgetPasswordResponse>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofitClient.forgetPassword(map)
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = appResponse.getResult()
                    appResponse.message
                    appResponse.status
                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }
    }

    fun userProfile(listener: RequestListener<User>) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofitClient.userProfile()
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = gson.fromJson(
                        appResponse.getResult(),
                        User::class.java
                    ) as User
                    listener.onSuccess(data)
                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }
    }

    // ddnt read json
    // form url in header
    @ExperimentalStdlibApi
    fun getMovie(map: ArrayMap<String, Any>, listener: PaginationListener<ArrayList<Movie>>) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofitClient.listOfMovies(map)
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = gson.fromJson(
                        appResponse.getResult(),
                        MovieResponse::class.java
                    ) as MovieResponse
                    val myType = typeOf<List<Movie>>().javaType
                    val list =
                        gson.fromJson<List<Movie>>(data.getResult(), myType)

                    listener.onSuccess(list as ArrayList<Movie>,data.total_pages)

                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }
    }

    fun addFavoriteItem(map: ArrayMap<String, Any>, listener: RequestListener<FavoriteResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofitClient.addFavoriteItem(map)
            if (response.isSuccessful) {
                val appResponse = response.body()!!
                if (appResponse.status) {
                    val data = gson.fromJson(
                        appResponse.getResult(),
                        FavoriteResponse::class.java
                    ) as FavoriteResponse
                    SharedPrefManager.token
                    listener.onSuccess(data)
                    appResponse.status
                    appResponse.message
                    mView!!.returnUser(SharedPrefManager.user)
                    SharedPrefManager.token
                } else {
                    listener.onFailure(appResponse.message)
                }
            } else {
                listener.onFailure(response.message())
            }
        }
    }
}

