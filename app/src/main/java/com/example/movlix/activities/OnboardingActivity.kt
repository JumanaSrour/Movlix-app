package com.example.movlix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movlix.ui.main.adapters.OnboardingItemsAdapter
import com.example.movlix.network.asp.models.OnboardingItem
import com.example.movlix.R
import com.example.movlix.R.drawable.*
import com.example.movlix.feature.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setOnboardingItems()

        btn_getStarted.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setOnboardingItems() {
        val array = ArrayList<OnboardingItem>();
        array.add(
            OnboardingItem(
                onboardingImage = onboarding_img1,
                description = getString(R.string.get_first_movie)
            )
        )
        array.add(
            OnboardingItem(
                onboardingImage = onboarding_img2,
                description = getString(R.string.know_the_movie)
            )
        )
        array.add(
            OnboardingItem(
                onboardingImage = onboarding_img3,
                description = getString(R.string.realtime_updates)
            )
        )


        onboardingItemsAdapter = OnboardingItemsAdapter(
            array
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboarding_viewpager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        indicator.setViewPager(onboardingViewPager)

        btn_next.setOnClickListener {
            when (onboardingViewPager.currentItem) {
                0 -> {
                    onboardingViewPager.setCurrentItem(array.size - 2, true)
                }

                1 -> {
                    onboardingViewPager.setCurrentItem(array.size - 1, true)
                }
                2 -> {
                    onboardingViewPager.setCurrentItem(array.size, true)

                }
            }
        }

        onboardingViewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {


                when (position) {
                    0 -> {
                        btn_next.visibility = View.VISIBLE
                        btn_getStarted.visibility = View.INVISIBLE
                    }
                    1 -> {
                        btn_next.visibility = View.VISIBLE
                        btn_getStarted.visibility = View.INVISIBLE
                    }
                    else -> {
                        btn_next.visibility = View.INVISIBLE
                        btn_getStarted.visibility = View.VISIBLE
                    }
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

}