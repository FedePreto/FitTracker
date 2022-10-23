package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPesoAttualeBinding
import com.example.fittracker.model.Utente


class PesoAttualeFragment : Fragment() {
    lateinit var binding: FragmentPesoAttualeBinding
    private lateinit var utente: Utente
    val args: PesoAttualeFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_peso_attuale, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente = args.utente
        binding.imageView44.isVisible = utente.agonista
        val action = setNavigation(utente.agonista)
            binding.btAvantiPesoAttuale.setOnClickListener {
                val pesoAttuale = binding.eTPesoAttuale.text.toString()
                if (pesoAttuale != "") {
                        utente.peso_attuale = pesoAttuale.toDouble()
                        view.findNavController().navigate(action)
                } else
                    Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            }
    }

    /*fun setNavigation(obbietivo: Int): NavDirections? {
        when (obbietivo) {
            0 -> return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
            1 ->{
                val intent = Intent(this@PesoAttualeFragment.context, RegisterActivity::class.java)
                intent.putExtra("utente",utente)
                this@PesoAttualeFragment.startActivity(intent)
                return null
                }
            2 -> return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
            else -> return null
        }
    }

     */
    fun setNavigation(isAgonista: Boolean): NavDirections {
        if(isAgonista) return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
        return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToRegisterActivity(utente)
    }

}
