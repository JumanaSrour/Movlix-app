package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.movlix.R
import com.example.movlix.feature.homeMovies.view.HomeMoviesActivity
import com.example.movlix.feature.login.view.LoginActivity
import com.example.movlix.utils.MovlixApp
import com.example.movlix.utils.storage.SharedPrefManager

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,
            WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW
        )
        val sharedPref = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)


        handler = Handler()
        handler.postDelayed({

            if (!isFirstTime) {
                val editor = sharedPref.edit()
                editor.putBoolean("isFirstTime", false)
                editor.apply()
                if (SharedPrefManager.isLoggedIn) {
                    /// go to home
                    startActivity(Intent(MovlixApp.getInstance(), HomeMoviesActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(MovlixApp.getInstance(), LoginActivity::class.java))
                    finish()
                }
            } else {
                startActivity(Intent(MovlixApp.getInstance(), OnboardingActivity::class.java))
                finish()
            }
        }, 3000)
    }
}