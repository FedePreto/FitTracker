package com.example.fittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.example.fittracker.autenticazione.InizioActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val updateHandler = Handler()

        val runnable = Runnable {
            val intent = Intent(this, InizioActivity::class.java)
            startActivity(intent)
            finish()
        }
        updateHandler.postDelayed(runnable, 2000)
    }


}
