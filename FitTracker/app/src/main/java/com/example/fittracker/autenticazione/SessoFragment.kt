package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        binding.btAvantiSesso.setOnClickListener {view : View->
            view.findNavController().navigate(R.id.action_sessoFragment_to_datiPersonaliFragment) }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val obbiettivo = args.obbiettivo
        Toast.makeText(context,obbiettivo,Toast.LENGTH_SHORT).show()
    }


}