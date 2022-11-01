package com.example.fittracker.esercizio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityAggiungiBinding
import com.example.fittracker.databinding.ActivityAggiungiEsercizioBinding
import com.example.fittracker.databinding.ActivityEsercizioBinding
import com.example.fittracker.databinding.ActivityProdottoBinding
import com.example.fittracker.model.Json_Parsing.Nutrients
import com.example.fittracker.prodotto.ProdottoViewModel
import kotlin.math.roundToInt

class EsercizioAddPrefActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAggiungiEsercizioBinding
    private var prodotto  = HashMap<String,String?>()
    private var nutrients = HashMap<String,Double?>()
    private val model = EsercizioViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aggiungi_esercizio)

        setLayout()

        binding.btnAggiungiDiario.setOnClickListener {
            var durata = binding.eTDurataEsercizio.text.toString()
            if(durata != null && durata != "0" && durata != ""){
                durata
                model.setEsercizioOnDB(intent.extras!!.getString("nome")!!, intent.extras!!.getInt("calorieOra"),durata.toInt() ,this)
                finish()
            }else
                Toast.makeText(this, "Per favore inserisci una durata diversa da $durata", Toast.LENGTH_LONG).show()
            }


            binding.btnAggiungiPreferiti.setOnClickListener {
                model.setEsercizioPreferitiOnDB(intent.extras!!.getString("nome")!!, intent.extras!!.getInt("calorieOra"), this)
                finish()
            }




        }

        private fun setLayout(){
            Glide.with(this)
                .load("https://cdn.vectorstock.com/i/preview-1x/38/32/square-barbell-icon-vector-5293832.webp")
                .into(binding.imageView37)

            binding.tvKcalH.text = intent.extras!!.getInt("calorieOra").toString()
            binding.nomeEsercizio.text = intent.extras!!.getString("nome")
        }

    }