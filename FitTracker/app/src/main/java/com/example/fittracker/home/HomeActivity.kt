package com.example.fittracker.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fittracker.R
import com.example.fittracker.autenticazione.InizioActivity
import com.example.fittracker.profilo.ProfiloActivity
import com.example.fittracker.databinding.ActivityHomeBinding
import com.example.fittracker.diario.DiarioFragment
import com.example.fittracker.diete.DieteFragment
import com.example.fittracker.funzioni.FunzioniFragment
import com.example.fittracker.statistiche.StatisticheFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
     val diarioFragment = DiarioFragment()
     val statisticheFragment = StatisticheFragment()
     val dieteFragment = DieteFragment()
     val funzioniFragment = FunzioniFragment()

    private val model = HomeViewModel()
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)


        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ic_diary,true)
        replaceFragment(diarioFragment) // La home si aprirà sul fragment del diario



        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                //redirecting fragment
                when(newIndex){
                    0 -> replaceFragment(diarioFragment);
                    1 -> replaceFragment(statisticheFragment);
                    2 -> replaceFragment(dieteFragment);
                    3 -> replaceFragment(funzioniFragment);
                    else -> replaceFragment(diarioFragment)
                }


            }


        })



        binding.aggToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.ic_profilo ->  openProfilo()
                R.id.ic_logout -> {
                    model.logOut()
                    startActivity(Intent(this,InizioActivity::class.java))
                    finish()
                }
            }
            true
        }


    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Premi due volte indietro per uscire", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
    }
    private fun openSettings(){

    }

    private fun openGuida(){

    }

    private fun openProfilo(){
        startActivity(Intent(this, ProfiloActivity::class.java))
        finish()
    }
}


