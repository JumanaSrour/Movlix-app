package com.example.movlix.feature.login.view

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.movlix.R
import com.example.movlix.activities.HomeMoviesActivity
import com.example.movlix.feature.forgetPassword.view.ForgetPasswordActivity
import com.example.movlix.feature.login.presenter.LoginPresenter
import com.example.movlix.feature.signup.view.SignupActivity
import com.example.movlix.network.asp.models.User
import com.example.movlix.utils.MovlixApp
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.btn_login
import kotlinx.android.synthetic.main.activity_signup.ed_password

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var mPresenter: LoginPresenter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initPresenter()

        btn_signup.setOnClickListener {

            startActivity(Intent(this, SignupActivity::class.java))
        }

        btn_login.setOnClickListener {
            mPresenter.login(
                ed_email_signup,
                ed_password,
            )
        }

        btn_forget_password.setOnClickListener {
            startActivity(Intent(MovlixApp.getInstance(), ForgetPasswordActivity::class.java))
        }

    }


    private fun initPresenter() {
        mPresenter = LoginPresenter(this, this, this)
    }

    override fun returnUser(user: User) {
        startActivity(Intent(MovlixApp.getInstance(), HomeMoviesActivity::class.java))
    }

    override fun showErrorMsg(msg: String?) {
        Toast.makeText(applicationContext, "$msg", Toast.LENGTH_SHORT).show()
    }

    companion object {
        public fun newInstance(mActivity: Activity): Intent {
            val intent = Intent(mActivity, LoginActivity::class.java)
            return intent
        }

    }

}