package com.example.movlix.utils

import android.app.Application

class MovlixApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application

        @JvmName("getInstance1")
        public fun getInstance(): Application {
            return instance
        }
    }
}