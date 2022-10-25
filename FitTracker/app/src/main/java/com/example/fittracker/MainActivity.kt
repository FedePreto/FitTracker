package com.example.fittracker
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.example.fittracker.autenticazione.AuthViewModel
import com.example.fittracker.autenticazione.InizioActivity
import com.example.fittracker.databinding.ActivityMainBinding
import com.example.fittracker.home.HomeActivity
import com.example.fittracker.model.Json_Parsing.Json_FoodList
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


class MainActivity : AppCompatActivity() {

    val model = AuthViewModel()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.progressBar.visibility = ProgressBar.VISIBLE



        if (model.checkUtenteisLoggato()) {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this@MainActivity, InizioActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}



