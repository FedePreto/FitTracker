package com.example.fittracker.aggiungi

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPersonalizzatiBinding
import com.example.fittracker.model.Json_Parsing.Prodotto
import com.example.fittracker.model.Pasto
import com.example.fittracker.prodotto.ProdottoActivity
import kotlinx.android.synthetic.main.add_delete_layout.*


class PersonalizzatiFragment : Fragment() {

    lateinit private var binding: FragmentPersonalizzatiBinding


    //Prova adapter
    private lateinit var recyclerViewPersonalizzati: RecyclerView

    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var news : Array<String>

    val model = AggiungiViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personalizzati, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPersonalizzati(requireArguments().getString("bottone")!!)
        val searchBar = binding.searchBar2
        searchBar.queryHint = "Cerca il tuo prodotto personalizzato"
        searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //model.getProdottoByName(query!!,"")
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        recyclerViewPersonalizzati = binding.gridProdotto
        recyclerViewPersonalizzati.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPersonalizzati.setHasFixedSize(true)
        val personalizzatiObserver = Observer<List<Pasto>>{

            val adapter = MyAdapterPrefPers(model.personalizzatiLiveData.value!! as ArrayList<Pasto>)
            recyclerViewPersonalizzati.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPers.onItemClickListener {
                override fun onItemClick(position: Int) {
                    aggiungiProdotto("clickItem", model.personalizzatiLiveData.value!![position].id)
                }
            })
        }
        model.personalizzatiLiveData.observe(viewLifecycleOwner,personalizzatiObserver)


        if(requireArguments().getString("bottone") == "ESERCIZIO")
            binding.btnAggiungi.setOnClickListener {
                aggiungiEsercizio()
            }
        else
            binding.btnAggiungi.setOnClickListener {
                aggiungiProdotto("clickAggiungi","")//idEsercizio)
            }



    }


    private fun aggiungiEsercizio() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.calorie_semplici_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)


        with(builder){
            setTitle("AGGIUNGI UN ESERCIZIO PERSONALIZZATO")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var titolo = titolo.text.toString().trim()


            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }

    }

    private fun aggiungiProdotto(bottone:String, id : String){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registrazione_rapida_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)
        val btnAggiungi = dialogLayout.findViewById<Button>(R.id.btnAggiungi)
        val btnElimina = dialogLayout.findViewById<Button>(R.id.buttonElimina)
        if(bottone == "clickItem"){
            btnAggiungi.text = "AGGIORNA"
            btnElimina.visibility= View.VISIBLE
        }else{
            btnAggiungi.text = "AGGIUNGI"
            btnElimina.visibility = View.GONE
        }




        btnAggiungi.setOnClickListener {
            var kcal_salva = kcal.text.toString()
            var carbo_salva = carbo.text.toString()
            var proteine_salva = proteine.text.toString()
            var grassi_salva = grassi.text.toString()
            var titolo = titolo.text.toString().trim()
            if(kcal_salva != "" && carbo_salva != "" && proteine_salva != "" && grassi_salva != "" && titolo != "") {
                if(bottone == "clickItem"){
                    model.updatePersonalizzatoOnDB(
                        id,requireArguments().getString("bottone")!!, titolo, kcal_salva.toInt(),
                        proteine_salva.toInt(), carbo_salva.toInt(), grassi_salva.toInt(), requireContext())
                }else {
                    model.setPersonalizzatiOnDB(
                        requireArguments().getString("bottone")!!, titolo, kcal_salva.toInt(),
                        proteine_salva.toInt(), carbo_salva.toInt(), grassi_salva.toInt(), requireContext()
                    )
                }
                model.getPersonalizzati(requireArguments().getString("bottone")!!)
            }
            else
                Toast.makeText(requireContext(),"Per favore completa tutti i campi prima di salvare",Toast.LENGTH_LONG).show()

        }


        builder.setNegativeButton("Annulla"){ dialog, which ->
            dialog.dismiss()
        }

        btnElimina.setOnClickListener {
            model.deletePersonalizzato(id, requireArguments().getString("bottone")!!,requireContext())
        }



        builder.setView(dialogLayout)
        builder.show()

    }


}