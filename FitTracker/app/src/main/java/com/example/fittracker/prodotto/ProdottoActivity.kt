package com.example.fittracker.prodotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityHomeBinding

import com.example.fittracker.databinding.ActivityProdottoBinding
import com.example.fittracker.databinding.FragmentDiarioBinding
import com.example.fittracker.model.Nutrients
import com.google.protobuf.Any

class ProdottoActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityProdottoBinding
    private var prodotto  = HashMap<String,String?>()
    private var nutrients = HashMap<String,Double?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodotto)

        getExtra()

        Log.d("immagine", prodotto.get("image")!!)

        Glide.with(this)
            .load(prodotto.get("image"))
            .placeholder(R.drawable.no_image)
            .into(binding.imageProdotto)

        binding.tvProductName.text = prodotto.get("label")
        binding.tvBrand.text = prodotto.get("brand")
        binding.tvCategory.text = prodotto.get("category")
        binding.tvDescription.text = prodotto.get("foodContents")
        binding.tvCalorie.text = nutrients.get("calorie").toString()
        binding.tvProteine.text = nutrients.get("proteine").toString()
        binding.tvCarboidrati.text = nutrients.get("carboidrati").toString()
        binding.tvGrassi.text = nutrients.get("grassi").toString()


/*
        prodotto.put("foodId",bundle.getString("foodId")!!)
        prodotto.put("image",bundle.getString("image")!!)
        prodotto.put("knownAs",bundle.getString("knownAs")!!)
        prodotto.put("label",bundle.getString("label")!!)



 */

    }
    private fun getExtra(){
        val bundle = intent.extras!!
        prodotto.put("brand",bundle.getString("brand"))
        prodotto.put("category",bundle.getString("category"))
        prodotto.put("foodContents",bundle.getString("foodContents"))
        prodotto.put("foodId",bundle.getString("foodId"))
        prodotto.put("image",bundle.getString("image"))
        prodotto.put("knownAs",bundle.getString("knownAs"))
        prodotto.put("label",bundle.getString("label"))
        var nut = bundle.getParcelable<Nutrients>("nutrients")
        nutrients.put("calorie",nut!!.chiloCalorie)
        nutrients.put("proteine",nut!!.proteine)
        nutrients.put("carboidrati",nut!!.carboidrati)
        nutrients.put("grassi",nut!!.grassi)
    }
}