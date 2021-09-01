package com.example.movlix.feature.login.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.activities.HomeMoviesActivity
import com.example.movlix.activities.Onboarding
import com.example.movlix.feature.login.presenter.LoginPresenter
import com.example.movlix.feature.signup.presenter.SignupPresenter
import com.example.movlix.network.asp.models.User
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(), LoginView {
    private lateinit var mPresenter: SignupPresenter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()

        btn_register.setOnClickListener {
            val intent = startActivity(Intent(this, Onboarding::class.java))
        }

        btn_login.setOnClickListener {
            mPresenter.signup(ed_email, ed_password, ed_confirm_password_signup)

        }

    }

    private fun initPresenter() {
        mPresenter = SignupPresenter(this, this)
    }

    override fun returnUser(user: User) {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
    }

    override fun showErrorMsg(msg: String?) {
        android.util.Log.e(TAG, "showErrorMsg: $msg", )
    }
}