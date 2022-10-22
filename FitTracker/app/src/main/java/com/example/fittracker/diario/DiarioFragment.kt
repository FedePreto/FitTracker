package com.example.fittracker.diario

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDiarioBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class DiarioFragment : Fragment() {
    private lateinit var binding: FragmentDiarioBinding
    private lateinit var viewModel: DiarioViewModel
    private var calorie_giornaliere = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diario, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var img = ImageView(context)
        var id = 0
        img.setOnClickListener{
            id = view.id
            Toast.makeText(context,id.toString(),Toast.LENGTH_LONG).show()
        }
/*
        val glass1 = binding.glass1
        val glass2 = binding.glass2
        glass1.setImageResource(R.drawable.empty_glass_plus)

        glass1.setOnClickListener {
            Toast.makeText(context,"Bicchiere1 riempito",Toast.LENGTH_LONG).show()
            val frameAnimation: AnimationDrawable = glass1.background as AnimationDrawable
           frameAnimation.start()
            glass2.setImageResource(R.drawable.empty_glass_plus)


        }

 */
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

    private fun addGlassImage(){
        val img = ImageView(context)
        img.setImageResource(R.drawable.filling_animation)
        binding.linearLayoutGlass.addView(img)
    }
    private fun startAnimation(glass : ImageView){
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()

    }
    }
