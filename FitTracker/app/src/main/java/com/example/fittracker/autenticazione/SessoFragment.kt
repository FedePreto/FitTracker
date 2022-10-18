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
import com.example.fittracker.databinding.FragmentSessoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SessoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SessoFragment : Fragment() {
    lateinit var binding: FragmentSessoBinding
    val args: SessoFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sesso, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sex = ""
        var obbiettivo = args.obbiettivo
        setStep(obbiettivo)
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.rB_uomo -> sex = "uomo"
                R.id.rB_donna -> sex = "donna"
            }
        }

        binding.rBUomo.setOnClickListener(listener)
        binding.rBDonna.setOnClickListener(listener)

        // Get radio group selected status and text using button click event
        binding.btAvantiSesso.setOnClickListener { view : View->
            // Get the checked radio button id from radio group
            if (sex != "") {
                val action = SessoFragmentDirections.actionSessoFragmentToDatiPersonaliFragment(sex,obbiettivo)
                view.findNavController().navigate(action) //navigazione da obiettivo a sesso


            } else {
                // If no radio button checked in this radio group
                Toast.makeText(context, "Per favore, seleziona un'opzione",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun setStep(obbietivo: Int) {
        when(obbietivo){
            0 -> {
                binding.imageView20.isVisible = true
                binding.imageView21.isVisible = true
            }
            1 ->{
                binding.imageView20.isVisible = false
                binding.imageView21.isVisible = false
            }
            2 ->{
                binding.imageView20.isVisible = true
                binding.imageView21.isVisible = true
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.GruppoRadioSesso.clearCheck()
    }


}