package com.example.fittracker.autenticazione

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityInizioBinding
import com.example.fittracker.databinding.ActivityLoginBinding

class InizioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInizioBinding
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inizio)
        binding = ActivityInizioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        avvioVideo()


        binding.btInizia.setOnClickListener {
            var a = Intent(this, ObbiettivoActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(a)
        }


        binding.btAccesso.setOnClickListener(){
            var a = Intent(this, LoginActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(a)
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
        binding.videoView.resume()
        super.onResume()
    }

    override fun onPause() {
        binding.videoView.suspend()
        super.onPause()
    }

    override fun onDestroy() {
        binding.videoView.stopPlayback()
        super.onDestroy()
    }




}