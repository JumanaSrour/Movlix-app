package com.example.movlix.feature.profile.presenter

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.movlix.feature.profile.view.ProfileActivity
import com.example.movlix.feature.profile.view.ProfileView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.ProfileResponse
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.AppSharedFunctions

data class ProfilePresenter(val mActivity: Activity, val view: ProfileView){

   @RequiresApi(Build.VERSION_CODES.M)
   public fun getUser(){
       if (AppSharedFunctions.networkIsConnected(mActivity)){
          UserRequest().userProfile(object : com.example.movlix.network.utils.RequestListener<User>{
              override fun onSuccess(data: User) {
                  Log.e("-----", "onSuccess: $data", )
              }

              override fun onFailure(message: String) {
                  Log.e("-----", "onFailure: $message", )
                  view.showErrorMsg(message)
              }

          })
       }
   }
}