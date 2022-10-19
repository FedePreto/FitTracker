package com.example.fittracker.autenticazione

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
        binding.btAvantiPesoObb.setOnClickListener {
            var peso_obiettivo = binding.eTPesoObb.text.toString()
            if (peso_obiettivo != "") {
                var isCorrect = checkPesi(peso_obiettivo.toDouble(), utente.peso_attuale, utente.obbiettivo)
                if (isCorrect!!) {
                    utente.peso_obbiettivo = peso_obiettivo.toDouble()
                    val action = PesoObbFragmentDirections.actionPesoObbFragmentToSliderFragment(utente)
                    view.findNavController().navigate(action!!)
                }
            } else
                Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkPesi(pOb: Double, pAt: Double, ob: Int): Boolean? {
        when (ob) {
            0 -> {
                if (pAt < pOb) {
                    binding.eTPesoObb.setError("Inserisci un obiettivo di peso minore di ${utente.peso_attuale}")
                    return false
                } else
                    return true
            }

            2 -> {
                if (pOb < pAt) {
                    binding.eTPesoObb.setError("Inserisci un obiettivo di peso maggiore di ${utente.peso_attuale}")
                    return false
                } else
                    return true
            }
            else-> return null
        }
    }
}