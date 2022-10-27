package com.example.fittracker.profilo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.fittracker.R
import com.example.fittracker.databinding.ActivityProfiloBinding
import com.example.fittracker.model.Utente
import kotlinx.android.synthetic.main.riautenticazione_layout.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ProfiloActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityProfiloBinding
    private val model = ProfiloViewModel()

    lateinit var sessoSpinner: Spinner
    lateinit var stileDiVitaSpinner: Spinner
    lateinit var sportSpinner: Spinner
    lateinit var agonisticoSwitch: Switch

    private var sessoS = arrayOf<String>("Uomo", "Donna")
    private var stile = arrayOf<String>("Sedentario","Poco attivo", "Attivo", "Molto attivo")
    private var sport = arrayOf<String>("Calcio","Basket", "Pallavolo", "Nuoto", "Baseball","Karate","Pattinaggio","Cricket",
                                "Tennis","Hockey","Ping Pong","Golf","Rugby","Football americano", "Atletica","Ginnastica artistica",
                                "Ginnastica ritmica", "Ciclismo","Judo","Pallanuoto","Altro")


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profilo)


        model.getDataProfilo()

        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this
        binding.eTCambioEmail.isEnabled=false


        // on below line we are initializing spinner with ids.
        sessoSpinner = binding.sWSesso
        stileDiVitaSpinner = binding.sWStileDiVita
        sportSpinner = binding.sWSport
        agonisticoSwitch = binding.switchAgonistico

        agonisticoSwitch.setOnCheckedChangeListener{ _, isChecked ->
            binding.selezioneSport.isVisible = isChecked
        }



        // on below line we are adding click listener for our spinner
        sessoSpinner.onItemSelectedListener = this
        stileDiVitaSpinner.onItemSelectedListener = this
        sportSpinner.onItemSelectedListener = this

        // on below line we are initializing adapter for our spinner
        val adapterSesso: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, sessoS)
        val adapterStile: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, stile)
        val adapterSport: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, sport)

        // on below line we are setting drop down view resource for our adapter.
        adapterSesso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterStile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterSport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // on below line we are setting adapter for spinner.
        sessoSpinner.adapter = adapterSesso
        stileDiVitaSpinner.adapter = adapterStile
        sportSpinner.adapter = adapterSport

        val profiloObserver = Observer<Utente> {
            //setto i miei spinner con i valori del db
            setSpinner(adapterSesso, adapterStile, adapterSport)
            //setto lo switcher con il valore del db
            agonisticoSwitch.isChecked = model.profilo.value!!.agonista
            //se lo switch è true faccio vedere la selezione dello sport
            binding.selezioneSport.isVisible = model.profilo.value!!.agonista

            //calendario

            var date = LocalDate.parse(model.profilo.value!!.data_nascita)
            val year = date.year
            val month = (date.monthValue - 1)
            val day = date.dayOfMonth

            //selezione data
            binding.tVDataNascita.setOnClickListener {
                val dpd = DatePickerDialog(this, { view, mYear, mMonth, mDay ->
                    date = LocalDate.of(mYear,(mMonth+1),mDay)
                    binding.tVDataNascita.text = "$mYear-"+(mMonth +1)+"-$mDay"
                }, year, month, day)
                dpd.show()
            }

            //pulsante cambia password
            binding.btnCambioPass.setOnClickListener {
                val email = model.profilo.value!!.email
                lifecycleScope.launch {
                    val task = model.changePassword(email)
                    task.addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@ProfiloActivity,
                                "Il link per cambiare la password è stato mandato all'indirizzo email!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this@ProfiloActivity,
                                "Email non registrata o non corretta!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            binding.btnSalva.setOnClickListener{
                val nome_up = binding.eTNome.text.toString().trim()
                val cognome_up = binding.etCognome.text.toString().trim()
                val email_up = binding.eTCambioEmail.text.toString().trim()
                var LAF_up =0.0
                when(binding.sWStileDiVita.selectedItem.toString()){
                    "Sedentario" -> LAF_up = 1.2
                    "Poco attivo" -> LAF_up = 1.375
                    "Attivo" -> LAF_up = 1.55
                    "Molto attivo" -> LAF_up =1.725
                }
                val data_nascita_up = date.toString()
                val agonistico_up = binding.switchAgonistico.isChecked
                val sesso_up = binding.sWSesso.selectedItem.toString()
                val altezza_up = binding.eTAltezza.text.toString()
                val peso_up = binding.eTPeso.text.toString()
                var sport_up = ""
                sport_up = if(!agonistico_up) ""
                else binding.sWSport.selectedItem.toString()
                model.updateAuthUtenteOnDB(nome_up, cognome_up, email_up,LAF_up,agonistico_up,sesso_up,data_nascita_up,altezza_up.toInt(),peso_up.toDouble(),sport_up,this)
            }

        }

        model.profilo.observe(this,profiloObserver)

}


    private fun setSpinner(
        adapterSesso: ArrayAdapter<CharSequence>,
        adapterStile: ArrayAdapter<CharSequence>,
        adapterSport: ArrayAdapter<CharSequence>
    ) {
        // on below line we are creating a variable to which we have to set our spinner item selected.
        val selectionSesso = model.profilo.value!!.sesso
        var selectionStile = ""
        when(model.profilo.value!!.LAF){
            1.2 ->  selectionStile = "Sedentario"
            1.375 ->  selectionStile = "Poco attivo"
            1.55 ->  selectionStile = "Attivo"
            1.725 ->  selectionStile = "Molto attivo"
        }
        val selectionSport = model.profilo.value!!.sport

        // on below line we are getting the position  the item by the item name in our adapter.
        val spinnerPositionSesso: Int = adapterSesso.getPosition(selectionSesso)
        val spinnerPositionStile: Int = adapterStile.getPosition(selectionStile)
        val spinnerPositionSport: Int = adapterSport.getPosition(selectionSport)

        // on below line we are setting selection for our spinner to spinner position.
        sessoSpinner.setSelection(spinnerPositionSesso)
        stileDiVitaSpinner.setSelection(spinnerPositionStile)
        sportSpinner.setSelection(spinnerPositionSport)

    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}