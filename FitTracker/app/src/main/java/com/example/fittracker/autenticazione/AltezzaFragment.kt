package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentAltezzaBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AltezzaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AltezzaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAltezzaBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_altezza, container, false)
        binding.btAvantiAltezza.setOnClickListener {view : View->
            view.findNavController().navigate(R.id.action_altezzaFragment_to_pesoAttualeFragment) }
        return binding.root
    }

    
}