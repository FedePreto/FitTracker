package com.example.fittracker.aggiungi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentRicercaBinding
import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.prodotto.ProdottoActivity


class RicercaFragment : Fragment() {

    private lateinit var binding: FragmentRicercaBinding
    private var model = AggiungiViewModel()

    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricerca, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar = binding.searchBar1
        searchBar.queryHint = "Cerca il tuo prodotto"
        searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                model.getFoodFromNameorUPC(query!!,"")
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)
        val foodObserver = Observer<ArrayList<Prodotto>>{

            val adapter = MyAdapterRicerca(model.foodLiveData.value!!)
            newRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterRicerca.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = prepareIntent(position)
                    startActivity(intent)
                }
            })
        }
        model.foodLiveData.observe(viewLifecycleOwner,foodObserver)



    }

    private fun prepareIntent(position: Int) : Intent{
        var intent = Intent(requireContext(), ProdottoActivity::class.java)
        intent.putExtra("tipologiaPasto", requireArguments().getString("bottone"))
        intent.putExtra("brand", model.foodLiveData.value!![position].brand)
        intent.putExtra("category", model.foodLiveData.value!![position].category)
        intent.putExtra("foodContents", model.foodLiveData.value!![position].foodContentsLabel)
        intent.putExtra("foodId", model.foodLiveData.value!![position].foodId)
        intent.putExtra("image", model.foodLiveData.value!![position].image)
        intent.putExtra("knownAs", model.foodLiveData.value!![position].knownAs)
        intent.putExtra("label", model.foodLiveData.value!![position].label)
        intent.putExtra("nutrients", model.foodLiveData.value!![position].nutrients)
        return intent
    }


}
