package com.example.fittracker.aggiungi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentRicercaBinding
import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.prodotto.ProdottoActivity
import com.example.fittracker.scanner.ScannerActivity


class RicercaFragment : Fragment() {

    private lateinit var binding: FragmentRicercaBinding
    private var model = AggiungiViewModel()

    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView

    companion object{
        const val RESULT = "RESULT"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricerca, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(requireArguments().getString("upc") != null)
            model.getFoodFromNameorUPC("",requireArguments().getString("upc")!!)

        if(requireArguments().getString("bottone") != null){
            if(requireArguments().getString("bottone") == "ESERCIZIO"){
                binding.btnScanner.visibility = View.GONE
                binding.searchBar1.minimumWidth = 1100
            }

        }



        val searchBar = binding.searchBar1
        searchBar.queryHint = "Cerca il tuo prodotto"
        searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(requireArguments().getString("bottone") != null) {
                    if (requireArguments().getString("bottone") == "ESERCIZIO") {
                        model.getEsercizi(query!!)
                    }
                }else
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
                    binding.searchBar1.clearFocus()
                    val intent = prepareIntent(position)
                    startActivity(intent)
                }
            })
        }
        model.foodLiveData.observe(viewLifecycleOwner,foodObserver)


        binding.btnScanner.setOnClickListener{
            /*val intent = Intent(requireContext(),ScannerActivity::class.java)
            Log.d("bottone",requireArguments().getString("bottone")!!)
            intent.putExtra("bottone",requireArguments().getString("bottone"))
            startActivity(intent)
            requireActivity().finish()
             */
            val intent = Intent(requireContext().applicationContext,ScannerActivity::class.java)
            startActivity(intent)

            val result = intent.getStringExtra(RESULT)

            if (result != null) {
                if(result.contains("https://") || result.contains("http://")){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result))
                    startActivity(intent)
                } else {
                      val codice = result.toString()
                    Toast.makeText(requireContext(), codice, Toast.LENGTH_LONG).show()
                }
            }
        }





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
