package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentPesoObbBinding
import com.example.fittracker.databinding.FragmentSliderBinding
import com.example.fittracker.model.Utente


class SliderFragment : Fragment() {
    lateinit var binding: FragmentSliderBinding
    private lateinit var utente: Utente
    val args: SliderFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_slider, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente = args.utente
        binding.tVDaPeso.text = utente.peso_attuale.toString()
        binding.tVAPeso.text = utente.peso_obbiettivo.toString()
        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when(seek.progress){
                    0 -> utente.kg_settimanali = 0.3
                    1 -> utente.kg_settimanali = 0.4
                    2 -> utente.kg_settimanali = 0.5
                    3 -> utente.kg_settimanali = 0.6
                    4 -> utente.kg_settimanali = 0.7
                    else -> utente.kg_settimanali = 0.0
                }
                binding.kgPerSettimana.text = utente.kg_settimanali.toString() + " Kg/Settimanali"
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}

        })

        binding.button.setOnClickListener {

            if (utente.peso_obbiettivo != 0.0) {
                val action = PesoObbFragmentDirections.actionPesoObbFragmentToSliderFragment(utente)
                view.findNavController().navigate(action!!)
            } else
                Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
        }
    }

    fun setData(p_attuale: Double, p_obiettivo: Double){


    }


}