package com.example.fittracker.aggiungi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.fittracker.R
import com.example.fittracker.autenticazione.InizioActivity
import com.example.fittracker.databinding.ActivityAggiungiBinding
import com.example.fittracker.home.HomeActivity
import nl.joery.animatedbottombar.AnimatedBottomBar

class AggiungiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAggiungiBinding
    val ricercaFragment = RicercaFragment()
    val personalizzatiFragment = PersonalizzatiFragment()
    val preferitiFragment = PreferitiFragment ()
    private var doubleBackToExitPressedOnce = false





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aggiungi)
        binding.aggToolbar.title =  intent.getStringExtra("bottone")

        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ricerca,true)
        checkTabToReplace(0)

        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                checkTabToReplace(newIndex)

            }


        })

    }

    private fun checkTabToReplace(index : Int){
        when(index){
            0 -> {
                val bottone = intent.getStringExtra("bottone")
                val bundle = Bundle()
                bundle.putString("bottone",bottone)
                ricercaFragment.arguments = bundle
                replaceFragment(ricercaFragment)
            }
            1 -> {
                val bottone = intent.getStringExtra("bottone")
                val bundle = Bundle()
                bundle.putString("bottone",bottone)
                personalizzatiFragment.arguments = bundle
                replaceFragment(personalizzatiFragment)
            }
            2 -> replaceFragment(preferitiFragment)
            else -> replaceFragment(ricercaFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransiction = fragmentManager.beginTransaction()
        fragmentTransiction.replace(R.id.frame_layout, fragment)
        fragmentTransiction.commit()

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

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Premi due volte indietro per uscire", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}