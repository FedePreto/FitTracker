package com.example.fittracker.aggiungi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.fittracker.R
import com.example.fittracker.autenticazione.InizioActivity
import com.example.fittracker.databinding.ActivityAggiungiBinding
import com.google.android.material.tabs.TabLayoutMediator

class AggiungiActivity : AppCompatActivity() {

    lateinit var binding: ActivityAggiungiBinding
    var tabTitle = arrayOf(R.drawable.ricerca,R.drawable.add_to_playlist,R.drawable.heart)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAggiungiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.aggToolbar.title = "Aggiungi " + intent.getStringExtra("bottone")

        showRegRapidaDialog()

        var pager = binding.tabContainer
        var tab = binding.tabLayout
        pager.adapter = MyAdapterTab(supportFragmentManager, lifecycle)

        TabLayoutMediator(tab, pager){
            tab, position -> tab.setIcon(tabTitle[position])

        }.attach()

        binding.aggToolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.reg_rapida -> showRegRapidaDialog()
                R.id.ic_alimento -> openAlimento()
                R.id.ic_ricetta -> openRicetta()
            }
            true
        }






    }

    private fun openRicetta() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.aggiunta_ricetta_pers_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)

        with(builder){
            setTitle("Crea ricetta")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var carbo_salva = carbo.text.toString()
                var proteine_salva = proteine.text.toString()
                var grassi_salva = grassi.text.toString()

            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }


    private fun openAlimento() {
        TODO("Not yet implemented")
    }


    private fun showRegRapidaDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registrazione_rapida_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)

        with(builder){
            setTitle("Registrazione rapida")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var carbo_salva = carbo.text.toString()
                var proteine_salva = proteine.text.toString()
                var grassi_salva = grassi.text.toString()
                var titolo = titolo.text.toString().trim()

            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }





}