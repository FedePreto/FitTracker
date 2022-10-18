package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentPesoAttualeBinding

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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPesoAttualeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_peso_attuale, container, false)
        binding.btAvantiPesoAttuale.setOnClickListener {view : View->
            view.findNavController().navigate(R.id.action_pesoAttualeFragment_to_pesoObbFragment) }
        return binding.root
    }

}