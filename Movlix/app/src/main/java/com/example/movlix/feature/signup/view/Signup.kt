package com.example.movlix.feature.signup.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.activities.ForgetPasswordActivity
import com.example.movlix.activities.HomeMoviesActivity
import com.example.movlix.feature.login.presenter.LoginPresenter
import com.example.movlix.feature.login.view.Login
import com.example.movlix.feature.login.view.LoginView
import com.example.movlix.network.asp.models.Token
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.MovlixApp
import com.example.movlix.utils.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.btn_login

class Signup : AppCompatActivity(), LoginView {
    private lateinit var mPresenter: LoginPresenter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initPresenter()

        btn_signup.setOnClickListener {

            startActivity(Intent(this, Login::class.java))
        }

        btn_login.setOnClickListener {
            mPresenter.login(
                ed_email_signup,
                ed_password
            )
        }

        btn_forget_password.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

    }

    private fun initPresenter() {
        mPresenter = LoginPresenter(this, this, this)
    }

    override fun returnUser(user: User){
        startActivity(Intent(MovlixApp.getInstance(), HomeMoviesActivity::class.java))
    }


    override fun showErrorMsg(msg: String?) {
        android.util.Log.e(TAG, "showErrorMsg: $msg", )
    }

}