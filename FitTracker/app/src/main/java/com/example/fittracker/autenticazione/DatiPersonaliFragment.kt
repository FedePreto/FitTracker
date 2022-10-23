package com.example.fittracker.autenticazione

import android.annotation.SuppressLint
import android.app.DatePickerDialog
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
import com.example.fittracker.databinding.FragmentDatiPersonaliBinding
import java.util.Calendar


class DatiPersonaliFragment : Fragment() {
    lateinit var binding : FragmentDatiPersonaliBinding
    val args: DatiPersonaliFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dati_personali, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var utente = args.utente
        binding.imageView28.isVisible = utente.agonista
        //calendario
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //selezione data
        binding.tvDataNascita.setOnClickListener{
            val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                binding.tvDataNascita.text = "$mDay-"+(mMonth +1)+"-$mYear"
            }, year, month, day)
            dpd.show()
        }

        binding.btAvantiDati.setOnClickListener{
            utente.data_nascita = binding.tvDataNascita.text.toString()
            utente.nome = binding.tEName.text.toString()
            utente.cognome = binding.tESurname.text.toString()
            if(utente.data_nascita != "" && utente.nome != "" && utente.cognome != ""){
                val action = DatiPersonaliFragmentDirections.actionDatiPersonaliFragmentToAltezzaFragment(utente)
                view.findNavController().navigate(action)
            }
            else
                Toast.makeText(context, "Per favore, completa tutti i campi",Toast.LENGTH_SHORT).show()

        }



    }


}
