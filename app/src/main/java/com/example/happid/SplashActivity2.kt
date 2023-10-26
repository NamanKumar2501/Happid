package com.example.happid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happid.databinding.ActivitySplash2Binding

class SplashActivity2 : AppCompatActivity() {

    private lateinit var bindingSplach2: ActivitySplash2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplach2 = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(bindingSplach2.root)

        bindingSplach2.llButton.setOnClickListener {
            val intent = Intent(this@SplashActivity2, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}