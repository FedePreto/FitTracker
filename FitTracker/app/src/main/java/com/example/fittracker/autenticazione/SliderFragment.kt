/*package com.example.fittracker.autenticazione

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentSliderBinding
import com.example.fittracker.model.Utente
import com.google.firebase.database.core.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_slider.*
import java.time.LocalDate


/*class SliderFragment : Fragment() {
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
        var kg_settimanali = 0.5
        binding.tvDateRaggiungimento.text = setData(utente.peso_attuale, utente.peso_obbiettivo!!, kg_settimanali)
        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when(seek.progress){
                    0 -> kg_settimanali = 0.3
                    1 -> kg_settimanali = 0.4
                    2 -> kg_settimanali = 0.5
                    3 -> kg_settimanali = 0.6
                    4 -> kg_settimanali = 0.7
                    else -> kg_settimanali = 0.0
                }
                binding.kgPerSettimana.text = kg_settimanali.toString() + " Kg/Settimanali"
                binding.tvDateRaggiungimento.text = setData(utente.peso_attuale, utente.peso_obbiettivo!!, kg_settimanali)
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}

        })

        binding.button.setOnClickListener {
            utente.kg_settimanali = kg_settimanali
            utente.data_raggiungimento = binding.tvDateRaggiungimento.text.toString()
            if (utente.kg_settimanali != 0.0 && utente.data_raggiungimento != "") {

                val intent = Intent(requireContext(), RegisterActivity::class.java)
                intent.putExtra("utente",utente)
                startActivity(intent)

            } else
                Toast.makeText(context, "Per favore, completa i campi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setData(p_attuale: Double, p_obiettivo: Double, kg_settimanali : Double) : String{
        var delta = 0.0
        if(p_obiettivo < p_attuale)
            delta = p_attuale - p_obiettivo
        else
            delta = p_obiettivo - p_attuale

        val n_settimane = delta / kg_settimanali
        return LocalDate.now().plusDays(n_settimane.toLong()).toString()
    }

}*/