package com.example.movlix.utils.storage

import android.annotation.SuppressLint
import android.content.Context
import com.example.movlix.network.asp.models.TokenBean
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.MovlixApp
import com.google.gson.Gson
import android.content.SharedPreferences
import androidx.core.content.edit
import okhttp3.internal.cache2.Relay.Companion.edit


class SharedPrefManager() {
    companion object {
        var gson = Gson()
        val isLoggedIn: Boolean
            get() {
                val sharedPreferences =
                    MovlixApp.getInstance()
                        .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                return sharedPreferences.getInt("id", -1) != -1
            }


        val user: User
            get() {
                return gson.fromJson(
                    MovlixApp.getInstance()
                        .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(
                            SHARED_USER, ""
                        ), User::class.java
                )
            }

        val token: TokenBean
            get() {
                gson = Gson()
                return gson.fromJson(
                    MovlixApp.getInstance()
                        .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(
                            SHARED_TOKEN, null
                        ), TokenBean::class.java
                )
            }


        fun saveUser(user: User) {
            MovlixApp.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(SHARED_USER, gson.toJson(user)).apply()
        }

        fun saveToken(token: TokenBean) {
            MovlixApp.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(SHARED_TOKEN, gson.toJson(token)).apply()
        }

        fun clear() {
            val sharedPreferences =
                MovlixApp.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }

        fun signUp(user: User) {

        }

//        fun saveState(isFavourite: Boolean) {
//            val sharedPreferences: SharedPreferences = MovlixApp.getInstance().getSharedPreferences(
//                SHARED_FAVORITE, Context.MODE_PRIVATE
//            )
//            val editor = SharedPreferences.edit()
//            editor.putBoolean(SHARED_STATE, isFavourite)
//            editor.apply()
//        }
//
//        fun readState(): Boolean {
//            val aSharedPreferences: SharedPreferences = MovlixApp.getInstance().getSharedPreferences(
//                SHARED_FAVORITE, Context.MODE_PRIVATE
//            )
//            return aSharedPreferences.getBoolean("State", true)
//        }

        private val SHARED_PREF_NAME = "movlix"
        private val SHARED_USER = "user"
        private val SHARED_TOKEN = "token"
//        private val SHARED_STATE = "state"
//        private val SHARED_FAVORITE = "favorite"

        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null

    }

}