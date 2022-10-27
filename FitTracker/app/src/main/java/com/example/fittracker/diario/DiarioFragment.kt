package com.example.fittracker.diario

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.aggiungi.AggiungiActivity
import com.example.fittracker.databinding.FragmentDiarioBinding
import com.example.fittracker.model.Diario
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_diario.*
import kotlinx.android.synthetic.main.win_layout_dialog.*

class DiarioFragment : Fragment() {
    private lateinit var binding: FragmentDiarioBinding
    private val model = DiarioViewModel()

    private lateinit var intent : Intent
    private lateinit var glasses : Array<ImageView>
    private var contatore = 0
    private var flag_congratulazioni = false

    private var acqua = arrayListOf(false,false,false,false,false,false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diario, container, false)

        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this




        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glasses = arrayOf(binding.glass1,binding.glass2,binding.glass3,binding.glass4,
                            binding.glass5,binding.glass6,binding.glass7,binding.glass8)

        onClickGlass()
        setOnclickPasti()

        model.getUserDiarioDB()

        val diarioObserver = Observer<Diario> {
            if (model.diario.value == null && contatore == 0) {
                Log.e("Logger","Ho creato per la prima volta un diario")
                Toast.makeText(context, "Non è ancora stato creato un diario", Toast.LENGTH_LONG).show()
                model.setDiarioOnDB()
                model.getUserDiarioDB()
            }else{
                Log.e("Logger","Il diario è già presente e valorizzato")
                if(contatore < 1) {
                    Log.e("Logger","Il contatore viene incrementato di uno")
                    checkFullGlasses()
                    model.setFabbisognoRimanente()
                    contatore += 1
                }else {
                    Log.e("Logger", "contatore viene azzerato")
                    contatore = 0
                }
            }
        }


        model.diario.observe(viewLifecycleOwner,diarioObserver)




    }

    override fun onDestroy() {
        super.onDestroy()
        model.setDiarioOnDB(model.diario.value!!.grassiTot, model.diario.value!!.proteineTot,
            model.diario.value!!.carboidratiTot, model.diario.value!!.chiloCalorieEsercizio,
            model.diario.value!!.chiloCalorieColazione, model.diario.value!!.chiloCaloriePranzo,
            model.diario.value!!.chiloCalorieCena, model.diario.value!!.chiloCalorieSpuntino,
            acqua)

    }

    override fun onPause() {
        super.onPause()
        model.setDiarioOnDB(model.diario.value!!.grassiTot, model.diario.value!!.proteineTot,
            model.diario.value!!.carboidratiTot, model.diario.value!!.chiloCalorieEsercizio,
            model.diario.value!!.chiloCalorieColazione, model.diario.value!!.chiloCaloriePranzo,
            model.diario.value!!.chiloCalorieCena, model.diario.value!!.chiloCalorieSpuntino,
            acqua)

    }


/*
        binding.progressCalorie.apply {
            progressMax = calorie_giornaliere.toFloat()
            setProgressWithAnimation(65f, 1500)
            progressBarWidth = 7f
            backgroundProgressBarWidth = 5f
            backgroundProgressBarColor = Color.WHITE
            progressBarColor = Color.GREEN
            roundBorder = true
            startAngle= 90f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT

        }
        */


    private fun startAnimation(glass : ImageView){
        glass.setBackgroundResource(R.drawable.filling_animation)
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()
    }

    fun checkFullGlasses(){
        for(i in 0..7) {
            if (model.diario.value!!.acqua[i]) {
                acqua[i] = model.diario.value!!.acqua[i]
                startAnimation(glasses[i])
                if(i < 7)
                    glasses[i+1].setBackgroundResource(R.drawable.empty_glass_plus)
            }
        }
        checkAcquaBevuta()
    }

    private fun onClickGlass(){
        for(i in 0..7){
            glasses[i].setOnClickListener {
                if(acqua[i]){
                    glasses[i].setBackgroundResource(R.drawable.empty_glass_plus)
                    for(x in i..7){
                        if(x<7)
                            glasses[x+1].setBackgroundResource(R.drawable.empty_glass)
                        acqua[x] = false
                    }
                }else {
                    for(x in 0..i){
                        startAnimation(glasses[x])
                        acqua[x] = true
                    }
                    if(i < 7){
                        glasses[i+1].setBackgroundResource(R.drawable.empty_glass_plus)
                    }
                }
                checkAcquaBevuta()
            }
        }
    }

    private fun checkAcquaBevuta(){
        var litri_acqua = 0.0
        for (bicchiere in acqua)
            if(bicchiere)
                litri_acqua += 0.25
        model.setAcqua(litri_acqua)
        /*Per fare in modo che il messaggio di congratulazioni venga mostrato una sola volta nel caso di raggiungimento
        dell'obiettivo controllo che il totale sia a 2 litri, se ho gia mostrato il messaggio nella stesso ciclo di vita del fragment
        e se il settimo bicchiere d'acqua era gia presente al momento del caricamento del diario
         */
        if(litri_acqua == 2.0){
            binding.imageViewGoldMedal.isVisible = true
            if(!flag_congratulazioni && !model.diario.value!!.acqua[7]) {
                flag_congratulazioni = true
                openCongratulazioni()
            }
        }else{
            binding.imageViewGoldMedal.isVisible = false
        }

    }


    @SuppressLint("ResourceAsColor")
    private fun openCongratulazioni() {
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.win_layout_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
        dialog.imageViewClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_OK.setOnClickListener {
            dialog.dismiss()
        }
        Glide.with(requireContext())
            .load(R.raw.awards)
            .into(dialog.imageViewWin)
        dialog.show()
    }

    private fun setOnclickPasti(){
        intent =  Intent(context, AggiungiActivity::class.java)
        binding.colazione.setOnClickListener {
            intent.putExtra("bottone","COLAZIONE")
            startActivity(intent)
        }
        binding.pranzo.setOnClickListener {
            intent.putExtra("bottone","PRANZO")
            startActivity(intent)
        }
        binding.cena.setOnClickListener {
            intent.putExtra("bottone","CENA")
            startActivity(intent)
        }

        binding.spuntino.setOnClickListener {
            intent.putExtra("bottone","SPUNTINO")
            startActivity(intent)
        }

        binding.esercizio.setOnClickListener {
            intent.putExtra("bottone","ESERCIZIO")
            startActivity(intent)
        }
    }

}
