package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDatiPersonaliBinding
import com.example.fittracker.databinding.FragmentPesoAttualeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DatiPersonaliFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DatiPersonaliFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDatiPersonaliBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dati_personali, container, false)
        binding.btAvantiDati.setOnClickListener {view : View->
            view.findNavController().navigate(R.id.action_datiPersonaliFragment_to_altezzaFragment) }
        return binding.root
    }


}