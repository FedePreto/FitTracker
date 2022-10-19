package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentAltezzaBinding
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentPesoAttualeBinding
import com.example.fittracker.model.Utente

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PesoAttualeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        setStep(utente.obbiettivo)
        val action = setNavigation(utente.obbiettivo)
            binding.btAvantiPesoAttuale.setOnClickListener {
                var pesoAttuale = binding.eTPesoAttuale.text.toString()
                if (pesoAttuale != "") {
                    utente.peso_attuale = pesoAttuale.toDouble()
                    view.findNavController().navigate(action!!)
                } else
                    Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            }
    }

    fun setStep(obbietivo: Int) {
        when (obbietivo) {
            0 -> {
                binding.imageView44.isVisible = true
                binding.imageView45.isVisible = true
            }

            1 -> {
                binding.imageView44.isVisible = false
                binding.imageView45.isVisible = false
            }

            2 -> {
                binding.imageView44.isVisible = true
                binding.imageView45.isVisible = true
            }
        }
    }

    fun setNavigation(obbietivo: Int): NavDirections? {
        when (obbietivo) {
            0 -> return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
            1 -> return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToRegisterActivity(utente)
            2 -> return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
            else -> return null
        }
    }
}
