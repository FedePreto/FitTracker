package com.example.fittracker.autenticazione

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentPesoAttualeBinding
import com.example.fittracker.databinding.FragmentPesoObbBinding
import com.example.fittracker.model.Utente
import kotlinx.android.synthetic.main.fragment_peso_obb.*


class PesoObbFragment : Fragment() {
    lateinit var binding: FragmentPesoObbBinding
    private lateinit var utente: Utente
    val args: PesoObbFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_peso_obb, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente = args.utente
        utente.sport=""
        binding.btAvantiPesoObb.setOnClickListener {
            takeChecked()
            if(utente.sport == "")
                Toast.makeText(context,"Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("utente", utente)
                startActivity(intent)
            }
        }
    }

    private fun takeChecked() {
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.cB_calcio -> utente.sport = cB_calcio.text.toString()
                R.id.cB_basket -> utente.sport = cB_basket.text.toString()
                R.id.cB_baseball -> utente.sport = cB_baseball.text.toString()
                R.id.cB_nuoto -> utente.sport = cB_nuoto.text.toString()
                R.id.cB_judo -> utente.sport = cB_judo.text.toString()
                R.id.cB_golf -> utente.sport = cB_golf.text.toString()
                R.id.cB_tennis-> utente.sport = cB_tennis.text.toString()
                R.id.cB_ping_pong -> utente.sport = cB_ping_pong.text.toString()
                R.id.cB_football -> utente.sport = cB_football.text.toString()
                R.id.cB_cricket -> utente.sport = cB_cricket.text.toString()
                R.id.cB_karate -> utente.sport = cB_karate.text.toString()
                R.id.cB_hockey -> utente.sport = cB_hockey.text.toString()
                R.id.cB_ginnastica_art -> utente.sport = cB_ginnastica_art.text.toString()
                R.id.cB_ginnastica_ritm -> utente.sport = cB_ginnastica_ritm.text.toString()
                R.id.cB_rugby -> utente.sport = cB_rugby.text.toString()
                R.id.cB_atletica -> utente.sport = cB_atletica.text.toString()
                R.id.cB_ciclismo -> utente.sport = cB_ciclismo.text.toString()
                R.id.cB_pallavolo -> utente.sport = cB_pallavolo.text.toString()
                R.id.cB_pattinaggio -> utente.sport = cB_pattinaggio.text.toString()
                R.id.cB_pallanuoto -> utente.sport = cB_pallanuoto.text.toString()
                R.id.cB_altro -> utente.sport = cB_altro.text.toString()

            }
        }

        binding.cBCalcio.setOnClickListener(listener)
        binding.cBBasket.setOnClickListener(listener)
        binding.cBBaseball.setOnClickListener(listener)
        binding.cBNuoto.setOnClickListener(listener)
        binding.cBJudo.setOnClickListener(listener)
        binding.cBGolf.setOnClickListener(listener)
        binding.cBTennis.setOnClickListener(listener)
        binding.cBPingPong.setOnClickListener(listener)
        binding.cBFootball.setOnClickListener(listener)
        binding.cBCricket.setOnClickListener(listener)
        binding.cBRugby.setOnClickListener(listener)
        binding.cBKarate.setOnClickListener(listener)
        binding.cBHockey.setOnClickListener(listener)
        binding.cBGinnasticaArt.setOnClickListener(listener)
        binding.cBGinnasticaRitm.setOnClickListener(listener)
        binding.cBAtletica.setOnClickListener(listener)
        binding.cBCiclismo.setOnClickListener(listener)
        binding.cBPallavolo.setOnClickListener(listener)
        binding.cBPattinaggio.setOnClickListener(listener)
        binding.cBPallanuoto.setOnClickListener(listener)
        binding.cBAltro.setOnClickListener(listener)
    }


}