package com.example.movlix.feature.signup.presenter

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.movlix.activities.HomeMoviesActivity
import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.features.UserRequest
import com.example.movlix.network.asp.models.LoginResponse
import com.example.movlix.network.utils.Status
import com.example.movlix.utils.AppSharedFunctions
import com.example.movlix.utils.AppSharedFunctions.Companion.getStringFromEditText
import com.example.movlix.utils.MovlixApp
import com.example.movlix.utils.storage.SharedPrefManager

data class SignupPresenter(val mActivity: Activity, var mView: LoginView) :
    LifecycleOwner {
    @RequiresApi(Build.VERSION_CODES.M)
    public fun signup(
        email: EditText,
        password: EditText,
        confirmPassword: EditText
    ) {
        if (AppSharedFunctions.checkEditTextIsEmpty(email)) {
            AppSharedFunctions.showErrorField(email, "this field is required")
            return
        }
        if (AppSharedFunctions.checkEditTextIsEmpty(password)) {
            AppSharedFunctions.showErrorField(password, "this field is required")
            return
        }
        if (AppSharedFunctions.checkEditTextIsEmpty(confirmPassword)) {
            AppSharedFunctions.showErrorField(confirmPassword, "this field is required")
            return
        }

        singupUser(
            getStringFromEditText(email),
            getStringFromEditText(password),
            getStringFromEditText(confirmPassword)
        )
    }
        @RequiresApi(Build.VERSION_CODES.M)
        private fun singupUser(
            email: String,
            password: String,
            confirmPassword: String
        ) {
            if (AppSharedFunctions.networkIsConnected(mActivity)) {

                val params = ArrayMap<String, Any>()
                params["email"] = email
                params["password"] = password
                params["confirmPassword"] = confirmPassword
                UserRequest().createUser(params).observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { appresponse ->
                                    {
                                        if (appresponse.status) {
                                            val loginResponse = appresponse.items as LoginResponse
//                                            mView.returnUser(loginResponse.user)
                                        } else {
                                            mView.showErrorMsg(appresponse.message)
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                mView.showErrorMsg(it.message)

                            }
                            Status.LOADING -> {
                            }
                        }
                    }
                })

            } else {
                mView.showErrorMsg("it.message")
            }
        }


    override fun getLifecycle(): Lifecycle {
        return this.lifecycle
    }
}