package com.example.fittracker.autenticazione

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.renderscript.ScriptGroup
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
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 * Use the [ObbiettivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObbiettivoFragment : Fragment() {
    lateinit var binding: FragmentObbiettivoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_obbiettivo, container, false)
        binding.liniette.isVisible = false
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var index = -1
       var listener = View.OnClickListener { v ->
           when(v.id){
               R.id.rB_Diminuisci -> {
                   index = 0
                   setStep(false)

               }

               R.id.rB_Mantieni -> {
                   index = 1
                   setStep(true)
               }

               R.id.rB_Aumenta -> {
                   index = 2
                   setStep(false)

               }
           }
       }

       binding.rBAumenta.setOnClickListener(listener)
       binding.rBDiminuisci.setOnClickListener(listener)
       binding.rBMantieni.setOnClickListener(listener)

       // Get radio group selected status and text using button click event
       binding.btAvantiObb.setOnClickListener { view : View->
           // Get the checked radio button id from radio group
           if (index != -1) {
               val action = ObbiettivoFragmentDirections.actionObbiettivoFragmentToSessoFragment(index)
               view.findNavController().navigate(action) //navigazione da obiettivo a sesso


           } else {
               // If no radio button checked in this radio group
               Toast.makeText(context, "Per favore, seleziona un'opzione",Toast.LENGTH_SHORT).show()
           }
       }
   }

    override fun onStop() {
        super.onStop()
        binding.GruppoRadioObbiettivo.clearCheck()
    }
    fun setStep(isMantieni: Boolean) {
            if (!isMantieni) {
                binding.liniette.isVisible = true
                binding.imageView16.isVisible = true
                binding.imageView71.isVisible = true

            } else {
                binding.liniette.isVisible = true
                binding.imageView16.isVisible = false
                binding.imageView71.isVisible = false

            }
    }

}


