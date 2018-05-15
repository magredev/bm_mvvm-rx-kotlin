package com.bemobile.jaume.challenge.view.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.extension.delay
import com.bemobile.jaume.challenge.view.goToMainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delay(SPLASH_TIME) {
            goToMainActivity()
            finish()
        }
    }
}
