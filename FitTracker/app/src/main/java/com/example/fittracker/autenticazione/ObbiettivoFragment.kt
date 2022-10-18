package com.example.fittracker.autenticazione

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ObbiettivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObbiettivoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val binding: FragmentObbiettivoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_obbiettivo, container, false)
        buttonEffect(binding.btAvantiObb)
        binding.liniette.isVisible = false


        binding.GruppoRadio.setOnCheckedChangeListener { Gruppo: RadioGroup, Radio: Int ->
            if (binding.rBDiminuisci.isChecked || binding.rBAumenta.isChecked) {
                binding.liniette.isVisible = true
                binding.imageView16.isVisible = true
                binding.imageView71.isVisible = true

            } else {
                binding.liniette.isVisible = true
                binding.imageView16.isVisible = false
                binding.imageView71.isVisible = false
            }

        }


        // Get radio group selected status and text using button click event
        binding.btAvantiObb.setOnClickListener { view : View->
            // Get the checked radio button id from radio group
            var id: Int = binding.GruppoRadio.checkedRadioButtonId
            if (id != -1) {
                view.findNavController().navigate(R.id.action_obbiettivoFragment_to_sessoFragment) //navigazione da obiettivo a sesso
            } else {
                // If no radio button checked in this radio group
                Toast.makeText(context, "Per favore, seleziona un'opzione",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }



    @SuppressLint("ClickableViewAccessibility")
    fun buttonEffect(button: View) = button.setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.background.setColorFilter(0x66660000, PorterDuff.Mode.SRC_ATOP)
                v.invalidate()
            }

            MotionEvent.ACTION_UP -> {
                v.background.clearColorFilter()
                v.invalidate()
            }
        }
        false
    }





}