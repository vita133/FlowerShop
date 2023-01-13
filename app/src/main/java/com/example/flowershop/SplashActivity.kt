package com.example.flowershop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler()
            .postDelayed(
                {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                },
                2500
            )
    }
}
