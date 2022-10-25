package com.example.fittracker.prodotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R

import com.example.fittracker.databinding.ActivityProdottoBinding

class ProdottoActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityProdottoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodotto)

        val bundle: Bundle? = intent.extras
        val headings = bundle!!.getString("heading")
        val imageId = bundle!!.getInt("imageID")
        val news = bundle!!.getString("news")

        binding.imageProduct.setImageResource(intent.extras!!.getInt("imageID"))
        binding.tvProductName.text = intent.extras!!.getString("heading")
        binding.tvProductDescription.text = intent.extras!!.getString("news")



    }
}