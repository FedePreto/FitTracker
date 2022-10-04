package com.example.fittracker.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.fittracker.R
import com.example.fittracker.autenticazione.LoginActivity
import com.example.fittracker.databinding.ActivityHomeBinding
import com.example.fittracker.diario.DiarioFragment
import com.example.fittracker.diete.DieteFragment
import com.example.fittracker.funzioni.FunzioniFragment
import com.example.fittracker.io.IoFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val diarioFragment = DiarioFragment()
    private val ioFragment = IoFragment()
    private val dieteFragment = DieteFragment()
    private val funzioniFragment = FunzioniFragment()


    private var user  = Firebase.auth.currentUser
    lateinit var logIntent: Intent



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(diarioFragment)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_diary -> replaceFragment(diarioFragment)
                R.id.ic_io -> replaceFragment(ioFragment)
                R.id.ic_diete -> replaceFragment(dieteFragment)
                R.id.ic_funzioni -> replaceFragment(funzioniFragment)
            }
            true
        }


        var myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar

        myToolbar.setNavigationOnClickListener {
            Log.v("ok","ok")
        }

        var logoutIcon = findViewById<ActionMenuView>(R.id.ic_logout)
        if(user==null)
            logoutIcon.visibility = View.GONE
        else
            logoutIcon.visibility = View.VISIBLE

        var loginIcon = findViewById<ActionMenuView>(R.id.ic_login)
        if(user!=null)
            loginIcon.visibility = View.GONE
        else
            loginIcon.visibility = View.VISIBLE

        myToolbar.setOnMenuItemClickListener { menuItem ->
         when (menuItem.itemId) {
             R.id.ic_settings -> openSettings()
             R.id.ic_guida -> openGuida()
             R.id.ic_login -> {
                if(user == null){
                    logIntent = Intent(this,LoginActivity::class.java)
                    startActivity(logIntent)
                }
             }
             R.id.ic_logout -> {
                 if (user != null){
                     logout()
                 }
             }
         }
            true
        }


        /*
        binding.myToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.ic_settings -> openSettings()
                R.id.ic_guida -> openGuida()
                R.id.ic_logout -> logout()
            }
            true
        }*/
    }


    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_conteiner, fragment)
            transaction.commit()
        }
    }

    private fun openSettings(){

    }

    private fun openGuida(){


    }

    private fun logout(){
        Firebase.auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}


