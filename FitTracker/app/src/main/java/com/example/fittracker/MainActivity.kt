package com.example.fittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import android.content.pm.ActivityInfo
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val updateHandler = Handler()

        val runnable = Runnable {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        updateHandler.postDelayed(runnable, 2000)
    }


}
