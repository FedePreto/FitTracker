package com.example.fittracker.diete

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDieteBinding
import com.example.fittracker.model.Dieta
import java.text.FieldPosition

class DieteFragment : Fragment() {

    private lateinit var binding: FragmentDieteBinding
    private var model = DieteViewModel()

    private lateinit var recyclerViewDiete: RecyclerView
    private lateinit var dietaList: ArrayList<Dieta>

    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var news : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diete, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getDiete()

        recyclerViewDiete = binding.gridDiete
        recyclerViewDiete.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerViewDiete.setHasFixedSize(true)
        val dietaObserver = Observer<List<Dieta>>{
            val adapter = MyAdapterDiete(model.dieteLiveData.value!! as ArrayList<Dieta>)
            recyclerViewDiete.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterDiete.onItemClickListener{
                override fun onItemClick(position: Int) {
                    openDettagliDieta(position)
                }

            })
        }
        model.dieteLiveData.observe(viewLifecycleOwner,dietaObserver)

       // dietaList = arrayListOf()
        //getUserdata()
    }
/*
   private fun getUserdata() {
        for (i in imageId.indices) {
            val news = News(imageId[i], heading[i])
            newArrayList.add(news)
        }

        val adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                Toast.makeText(requireContext(), "Hai cliccato sull'elemento $position", Toast.LENGTH_SHORT).show()

                val intent = Intent(requireContext(), ProdottoActivity::class.java)
                intent.putExtra("heading", newArrayList[position].headings)
                intent.putExtra("imageID", newArrayList[position].titleImage)
                intent.putExtra("news", news[position])
                startActivity(intent)
            }
        })
    }*/

    private fun openDettagliDieta(position: Int){
                val builder = AlertDialog.Builder(requireContext())
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.descrizione_dieta_layout, null)
                val titolo_dieta_scelta = dialogLayout.findViewById<TextView>(R.id.titolo_dieta)
                val perc_carbo = dialogLayout.findViewById<TextView>(R.id.perc_carb)
                val perc_prot = dialogLayout.findViewById<TextView>(R.id.perc_proteine)
                val perc_grassi = dialogLayout.findViewById<TextView>(R.id.perc_grassi)
                val descrizione = dialogLayout.findViewById<TextView>(R.id.descrizione_dieta)
                val immagine = dialogLayout.findViewById<ImageView>(R.id.image_dieta)

                titolo_dieta_scelta.text = model.dieteLiveData.value!![position].titolo
                perc_carbo.text = model.dieteLiveData.value!![position].perc_carb.toString() + "%"
                perc_prot.text = model.dieteLiveData.value!![position].perc_prot.toString() + "%"
                perc_grassi.text = model.dieteLiveData.value!![position].perc_grassi.toString() + "%"
                descrizione.text = model.dieteLiveData.value!![position].descrizione.replace(".",".\n\n")
                Glide.with(requireContext())
                    .load(model.dieteLiveData.value!![position].image)
                    .placeholder(R.drawable.no_image)
                    .into(immagine)

                    with(builder){
                        setTitle("Dettagli della dieta")
                        setPositiveButton("Seleziona"){dialog, which ->
                            model.updateDieta(model.dieteLiveData.value!![position].titolo, requireContext())
                        }
                        setNegativeButton("Annulla"){ dialog, which ->
                            Log.d("Main", "Negative button clicked")
                        }
                        setView(dialogLayout)
                        show()
                    }

    }



}