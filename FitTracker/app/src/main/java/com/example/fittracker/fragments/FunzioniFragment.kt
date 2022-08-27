package com.example.fittracker.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracker.R
import com.example.fittracker.ViewModel.FunzioniViewModel

class FunzioniFragment : Fragment() {

    companion object {
        fun newInstance() = FunzioniFragment()
    }

    private lateinit var viewModel: FunzioniViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_funzioni, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FunzioniViewModel::class.java)
        // TODO: Use the ViewModel
    }

}