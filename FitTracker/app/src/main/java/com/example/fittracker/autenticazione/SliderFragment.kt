package com.example.fittracker.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentObbiettivoBinding
import com.example.fittracker.databinding.FragmentSliderBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SliderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SliderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentSliderBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_slider, container, false)
        return binding.root
    }


}