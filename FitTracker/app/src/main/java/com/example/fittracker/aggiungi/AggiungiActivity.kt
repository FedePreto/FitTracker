package com.example.fittracker.aggiungi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fittracker.R
import com.example.fittracker.autenticazione.InizioActivity
import com.example.fittracker.databinding.ActivityAggiungiBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class AggiungiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAggiungiBinding
    val ricercaFragment = RicercaFragment()
    val personalizzatiFragment = PersonalizzatiFragment()
    val preferitiFragment = PreferitiFragment ()






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






}