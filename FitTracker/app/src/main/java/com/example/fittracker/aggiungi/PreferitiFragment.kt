package com.example.fittracker.aggiungi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPreferitiBinding
import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.model.Pasto


class PreferitiFragment : Fragment() {

    lateinit private var binding: FragmentPreferitiBinding


    //Prova adapter
    private lateinit var recyclerViewPreferiti: RecyclerView
    private lateinit var preferitiList: ArrayList<Prodotto>

    private val model = AggiungiViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_preferiti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPreferiti(requireArguments().getString("bottone")!!)
        recyclerViewPreferiti = binding.gridPreferiti
        recyclerViewPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPreferiti.setHasFixedSize(true)

        val preferitiObserver = Observer<List<Pasto>>{
            val adapter = MyAdapterPrefPers(model.preferitiLiveData.value!! as ArrayList<Pasto>)
            recyclerViewPreferiti.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPers.onItemClickListener {
                override fun onItemClick(position: Int) {
                    openUpdateDeleteDialog(position)
                }
            })
        }
        model.preferitiLiveData.observe(viewLifecycleOwner,preferitiObserver)


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

    private fun openUpdateDeleteDialog(position: Int){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.delete_layout, null)
        val item = dialogLayout.findViewById<TextView>(R.id.tV_elimina)
            item.text = "Vuoi eliminare $position dalla lista?"

        with(builder){
            setTitle("ELIMINARE")
            setPositiveButton("Elimina"){dialog, which ->


            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }




}