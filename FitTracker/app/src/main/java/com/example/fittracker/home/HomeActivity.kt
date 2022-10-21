package com.example.fittracker.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fittracker.R
import com.example.fittracker.autenticazione.AuthViewModel
import com.example.fittracker.autenticazione.LoginActivity
import com.example.fittracker.databinding.ActivityHomeBinding
import com.example.fittracker.diario.DiarioFragment
import com.example.fittracker.diete.DieteFragment
import com.example.fittracker.funzioni.FunzioniFragment
import com.example.fittracker.io.IoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val diarioFragment = DiarioFragment()
    private val ioFragment = IoFragment()
    private val dieteFragment = DieteFragment()
    private val funzioniFragment = FunzioniFragment()

    private lateinit var navController: NavController

    private val model = AuthViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)

        replaceFragment(diarioFragment) // La home si aprirà sul fragment del diario

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_diary -> replaceFragment(diarioFragment)
                R.id.ic_io -> replaceFragment(ioFragment)
                R.id.ic_diete -> replaceFragment(dieteFragment)
                R.id.ic_funzioni -> replaceFragment(funzioniFragment)
            }
            true
        }



        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.ic_settings -> openSettings()
                R.id.ic_guida -> openGuida()
                R.id.ic_logout -> {
                    model.logOut()
                    finish()
                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
    private fun openSettings(){

    }

    private fun openGuida(){


    }
}


