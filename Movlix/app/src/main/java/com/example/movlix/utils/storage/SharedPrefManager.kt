package com.example.movlix.utils.storage

import android.annotation.SuppressLint
import android.content.Context
import com.example.movlix.network.asp.models.Token
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.MovlixApp
import com.google.gson.Gson

class SharedPrefManager() {
    companion object {
        val gson = Gson()
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

        val token: Token
            get() {
                return gson.fromJson(
                    MovlixApp.getInstance()
                        .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(
                            SHARED_TOKEN, ""
                        ), Token::class.java
                )
            }


        fun saveUser(user: User) {
            MovlixApp.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(SHARED_USER, gson.toJson(user)).apply()
        }

        fun saveToken(token: Token){
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

        fun signUp() {}

        private val SHARED_PREF_NAME = "movlix"
        private val SHARED_USER = "user"
        private val SHARED_TOKEN = "token"

        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null

//        @Synchronized
//        fun getInstance(mCtx: Context): SharedPrefManager {
//            if (mInstance == null) {
//                mInstance = SharedPrefManager(mCtx)
//            }
//            return mInstance as SharedPrefManager
//        }
    }

}