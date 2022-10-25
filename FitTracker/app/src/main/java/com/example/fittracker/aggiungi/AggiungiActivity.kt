package com.example.fittracker.aggiungi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        binding.aggToolbar.title =  intent.getStringExtra("bottone")
        var pager = binding.tabContainer
        var tab = binding.tabLayout
        pager.adapter = MyAdapterTab(supportFragmentManager, lifecycle)

        TabLayoutMediator(tab, pager){
            tab, position -> tab.setIcon(tabTitle[position])

        }.attach()

        binding.aggToolbar.setOnMenuItemClickListener{
            if(intent.getStringExtra("bottone")=="ESERCIZIO")
                when(it.itemId){
                    R.id.reg_rapida -> aggiungiEsercizio()
                }
            else when (it.itemId) {
                R.id.reg_rapida -> showRegRapidaDialog()
              //  R.id.ic_alimento -> openAlimento()
              //  R.id.ic_ricetta -> openRicetta()
            }
            true
        }






    }

    private fun aggiungiEsercizio() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.calorie_semplici_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)


        with(builder){
            setTitle("CALORIE SEMPLICI")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var titolo = titolo.text.toString().trim()


            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
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
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)
        val foto = dialogLayout.findViewById<Button>(R.id.btn_foto)
        val note = dialogLayout.findViewById<EditText>(R.id.eT_note)


        with(builder){
            setTitle("CREA RICETTA")
            setPositiveButton("Crea"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var carbo_salva = carbo.text.toString()
                var proteine_salva = proteine.text.toString()
                var grassi_salva = grassi.text.toString()
                var titolo = titolo.text.toString().trim()
                var foto = foto.text.toString().trim()
                var note = note.text.toString()

            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }


    private fun openAlimento() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.aggiunta_prodotto_pers_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)
        val foto = dialogLayout.findViewById<Button>(R.id.btn_foto)



        with(builder){
            setTitle("CREA PRODOTTO")
            setPositiveButton("Crea"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var carbo_salva = carbo.text.toString()
                var proteine_salva = proteine.text.toString()
                var grassi_salva = grassi.text.toString()
                var titolo = titolo.text.toString().trim()
                var foto = foto.text.toString().trim()


            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
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
            setTitle("REGISTRAZIONE RAPIDA PASTO")
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