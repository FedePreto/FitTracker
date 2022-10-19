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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_peso_obb, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente = args.utente
        binding.btAvantiPesoObb.setOnClickListener {
            utente.peso_obbiettivo = binding.eTPesoObb.text.toString().toDouble()
            if (utente.peso_obbiettivo != 0.0) {
                val action = PesoObbFragmentDirections.actionPesoObbFragmentToSliderFragment(utente)
                view.findNavController().navigate(action!!)
            } else
                Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
        }
    }
}