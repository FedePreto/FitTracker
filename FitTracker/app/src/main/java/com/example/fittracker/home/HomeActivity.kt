package com.example.fittracker.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.fittracker.R
import com.example.fittracker.autenticazione.AuthViewModel
import com.example.fittracker.autenticazione.InizioActivity
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



    private val model = AuthViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)

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






        binding.myToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.ic_settings -> openSettings()
                    R.id.ic_guida -> openGuida()
                    R.id.ic_logout -> {
                        model.logOut()
                        startActivity(Intent(this,InizioActivity::class.java))
                        finish()
                    }
                }
                true
            }


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
}


