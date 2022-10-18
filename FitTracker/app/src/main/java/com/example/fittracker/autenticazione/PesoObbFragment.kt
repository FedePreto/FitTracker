package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentPesoAttualeBinding
import com.example.fittracker.databinding.FragmentPesoObbBinding


/**
 * A simple [Fragment] subclass.
 * Use the [PesoObbFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PesoObbFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPesoObbBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_peso_obb, container, false)
        binding.btAvantiPesoObb.setOnClickListener {view : View->
            view.findNavController().navigate(R.id.action_pesoObbFragment_to_sliderFragment) }
        return binding.root
    }
}