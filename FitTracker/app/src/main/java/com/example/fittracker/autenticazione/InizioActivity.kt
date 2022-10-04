package com.example.fittracker.autenticazione

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityInizioBinding
import com.example.fittracker.databinding.ActivityLoginBinding

class InizioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInizioBinding
    private lateinit var mVideoView: VideoView
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inizio)
        binding = ActivityInizioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startVideo()

        binding.BtInizia.setOnClickListener(){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        binding.btAccesso.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
        }



    }

    private fun startVideo(){
        mVideoView = findViewById(R.id.videoView)
        uri = Uri.parse("android.resource://"+ packageName +"/"+R.raw.videoapp)
        mVideoView.setVideoURI(uri)
        mVideoView.start()
        mVideoView.setOnCompletionListener { mVideoView.start() }
    }
}