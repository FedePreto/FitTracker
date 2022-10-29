package com.example.fittracker.aggiungi

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.set
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
                    aggiungiProdotto(position,"clickItem", model.personalizzatiLiveData.value!![position].id)
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
                aggiungiProdotto(0,"clickAggiungi","")//idEsercizio)
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

    private fun aggiungiProdotto(position : Int, bottone:String, id : String){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registrazione_rapida_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)
        val btnAggiungiLista = dialogLayout.findViewById<Button>(R.id.btnAggiungi)
        val btnElimina = dialogLayout.findViewById<Button>(R.id.buttonElimina)
        val btnAggiungiDiario = dialogLayout.findViewById<Button>(R.id.btnAggiungiDiario)
        val layout_info = dialogLayout.findViewById<LinearLayout>(R.id.layout_info)
        if(bottone == "clickItem"){
            btnAggiungiLista.text = "AGGIORNA\nNELLA LISTA"
            layout_info.visibility = View.GONE
            btnElimina.visibility = View.VISIBLE
            btnAggiungiDiario.visibility = View.VISIBLE
            hideLayout(arrayOf(titolo,kcal,carbo,grassi,proteine),position)

        }else{
            btnAggiungiLista.text = "AGGIUNGI\nALLA LISTA"
            btnElimina.visibility = View.GONE
            btnAggiungiDiario.visibility = View.GONE
        }




        btnAggiungiLista.setOnClickListener {
            var kcal_salva = kcal.text.toString()
            var carbo_salva = carbo.text.toString()
            var proteine_salva = proteine.text.toString()
            var grassi_salva = grassi.text.toString()
            var titolo = titolo.text.toString().trim()
            if(kcal_salva != "" && carbo_salva != "" && proteine_salva != "" && grassi_salva != "" && titolo != "") {
                if(bottone == "clickItem"){
                    model.updatePersonalizzatoOnDB(
                        id,requireArguments().getString("bottone")!!, titolo, kcal_salva.toDouble(),
                        proteine_salva.toDouble(), carbo_salva.toDouble(), grassi_salva.toDouble(), requireContext())
                }else {
                    model.setPersonalizzatiOnDB(
                        requireArguments().getString("bottone")!!, titolo, kcal_salva.toDouble(),
                        proteine_salva.toDouble(), carbo_salva.toDouble(), grassi_salva.toDouble(), requireContext()
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

    private fun hideLayout(edit : Array<EditText>,position : Int){
        edit[0].setText(model.personalizzatiLiveData.value!![position].nome)
        edit[1].setText(model.personalizzatiLiveData.value!![position].calorie.toString())
        edit[2].setText(model.personalizzatiLiveData.value!![position].carboidrati.toString())
        edit[3].setText(model.personalizzatiLiveData.value!![position].grassi.toString())
        edit[4].setText(model.personalizzatiLiveData.value!![position].proteine.toString())
    }


}