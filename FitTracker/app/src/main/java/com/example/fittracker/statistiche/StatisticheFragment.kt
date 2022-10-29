package com.example.fittracker.statistiche

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentStatisticheBinding
import com.google.android.material.datepicker.MaterialDatePicker
import me.moallemi.tools.daterange.date.DateRange
import java.time.LocalDate
import java.util.*

class StatisticheFragment : Fragment() {

    private lateinit var model: StatisticheViewModel
    private lateinit var binding: FragmentStatisticheBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistiche, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //calendario
        var date = ""
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //selezione data
        binding.tVDataiInizio.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(requireActivity().supportFragmentManager, "DatePicker")
        }


    }


}