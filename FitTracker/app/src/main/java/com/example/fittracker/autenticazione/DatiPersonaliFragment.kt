package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDatiPersonaliBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DatiPersonaliFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        val action = DatiPersonaliFragmentDirections.actionDatiPersonaliFragmentToAltezzaFragment()
        view.findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()
    }


}