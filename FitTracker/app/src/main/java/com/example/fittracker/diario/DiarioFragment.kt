package com.example.fittracker.diario

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.layout.Layout
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import com.example.fittracker.R
import com.example.fittracker.aggiungi.AggiungiActivity
import com.example.fittracker.databinding.FragmentDiarioBinding
import com.example.fittracker.model.Diario
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_diario.*

class DiarioFragment : Fragment() {
    private lateinit var binding: FragmentDiarioBinding
    private val model = DiarioViewModel()

    private lateinit var intent : Intent
    private lateinit var diario : Diario
    private var contatore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getUserDiarioDB()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diario, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAllGlasses()
        setOnClickAllGlasses()
        setOnclick()

        val diarioObserver = Observer<Diario>{
            Log.e("msg","Sono dentro l'observer")
            if(model.diario.value != null && contatore >= 1)
                Toast.makeText(context, model.diario.value!!.utente.toString(), Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context, "Non è ancora stato creato un diario", Toast.LENGTH_LONG).show()
            contatore+=1
        }
        model.diario.observe(viewLifecycleOwner,diarioObserver)




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
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()

    }

    private fun setUpAllGlasses(){
        setUpGlass(binding.glass1,0)
        setUpGlass(binding.glass2,1)
        setUpGlass(binding.glass3,2)
        setUpGlass(binding.glass4,3)
        setUpGlass(binding.glass5,4)
        setUpGlass(binding.glass6,5)
        setUpGlass(binding.glass7,6)
        setUpGlass(binding.glass8,7)
    }
    private fun setOnClickAllGlasses(){
        binding.glass1.setOnClickListener { setUpGlass(binding.glass1,0) }
        binding.glass2.setOnClickListener { setUpGlass(binding.glass2,1) }
        binding.glass3.setOnClickListener { setUpGlass(binding.glass3,2) }
        binding.glass4.setOnClickListener { setUpGlass(binding.glass4,3) }
        binding.glass5.setOnClickListener { setUpGlass(binding.glass5,4) }
        binding.glass6.setOnClickListener { setUpGlass(binding.glass6,5) }
        binding.glass7.setOnClickListener { setUpGlass(binding.glass7,6) }
        binding.glass8.setOnClickListener { setUpGlass(binding.glass8,7) }
    }

    private fun setUpGlass(glass : ImageView,  i: Int){
        glass.setBackgroundResource(R.drawable.filling_animation)
        var frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        if(model.isFull[i]){
            frameAnimation.start()
            model.isFull[i] = false
        }else {
            glass.setBackgroundResource(R.drawable.empty_glass_plus)
            model.isFull[i] = true
        }

    }
    private fun setOnclick(){
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
