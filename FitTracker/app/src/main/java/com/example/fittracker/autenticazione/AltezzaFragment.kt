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
import com.example.fittracker.databinding.FragmentAltezzaBinding
import com.example.fittracker.databinding.FragmentSessoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AltezzaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AltezzaFragment : Fragment() {
    lateinit var binding: FragmentAltezzaBinding
    val args: AltezzaFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_altezza, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var utente = args.utente
        setStep(utente.obbiettivo)
        binding.btAvantiAltezza.setOnClickListener {
            var altezza = binding.eTAltezza.text.toString()
            if(altezza != ""){
                utente.altezza = altezza.toInt()
                val action =AltezzaFragmentDirections.actionAltezzaFragmentToPesoAttualeFragment(utente)
                view.findNavController().navigate(action)
            }else
                Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            }
    }

    fun setStep(obbietivo: Int) {
        when(obbietivo){
            0 -> {
                binding.imageView37.isVisible = true
                binding.imageView36.isVisible = true
            }
            1 ->{
                binding.imageView37.isVisible = false
                binding.imageView36.isVisible = false
            }
            2 ->{
                binding.imageView37.isVisible = true
                binding.imageView36.isVisible = true
            }
        }
    }

    
}