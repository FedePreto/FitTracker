package com.example.fittracker.autenticazione

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.fittracker.R
import com.example.fittracker.databinding.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


class InizioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInizioBinding
    private lateinit var uri: Uri
    private lateinit var progresBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_inizio)
        avvioVideo()
        progresBar = binding.ProgressBar01
        progresBar.visibility = ProgressBar.INVISIBLE



        binding.btInizia.setOnClickListener(){
            progresBar.visibility = ProgressBar.VISIBLE
            lifecycleScope.launch {
                var a = Intent(this@InizioActivity, ConosciamociActivity::class.java)
                startActivity(a)
            }
        }


        binding.btAccesso.setOnClickListener(){
            progresBar.visibility = ProgressBar.VISIBLE
            lifecycleScope.launch {
                var a = Intent(this@InizioActivity, LoginActivity::class.java)
                startActivity(a)
                finish()
            }

        }



    }

    private fun avvioVideo() {
        uri = Uri.parse("android.resource://"+ packageName +"/"+R.raw.videoapp)
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()


        binding.videoView.setOnPreparedListener {
            it.isLooping = true
        }
    }


    override fun onResume(){
        super.onResume()
        binding.videoView.resume()
        progresBar.visibility = ProgressBar.INVISIBLE
    }

    override fun onPause() {
        binding.videoView.suspend()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.stopPlayback()
        progresBar.visibility = ProgressBar.INVISIBLE
    }






}