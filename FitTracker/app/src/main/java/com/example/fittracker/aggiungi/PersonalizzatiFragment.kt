package com.example.fittracker.aggiungi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPersonalizzatiBinding
import com.example.fittracker.prodotto.ProdottoActivity


class PersonalizzatiFragment : Fragment() {

    lateinit private var binding: FragmentPersonalizzatiBinding


    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView

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

        imageId = arrayOf(R.drawable.b,R.drawable.b, R.drawable.b,
            R.drawable.b,R.drawable.b,R.drawable.b,R.drawable.b,
            R.drawable.b,R.drawable.b,R.drawable.b,R.drawable.b,
            R.drawable.b,R.drawable.b,R.drawable.b,)

        news = arrayOf("fsjdfsgdgddsbfsdbhjbhfdsjdsjhfhdjfhhfdsvhjfdsvhjdsvhjdsvhvhjvdbjdfbhjvbdhjbhjfdbjfdbvbdjvbxczjbdgfd",
            "dkjfkjfbadnkjbfkjbkvcxjvnkjdzsjknzvkdnjdnkvzdjnkjvdzsknjvdjknvdnsjvdknsjkvdjsknjvzdsknjvdsds",
            "fsjdfkjdsfdsvdsvdsvsdvdsvdsvsdvzdsvzdsvzdvzdsbgfdnfdjjhgghnghghchgcnnhcncnhchnccgnhnhhk",
            "dkjfkjchgncghnhcgncnhgnhcgcnnhgghmmhmghmhgmhgmhmmhgmhgmhgmhghmgfdfdsrgrgfbdjk",
            "fsjhsfsgdffgdgdgdffdbbvc vcngfgfgfjhgghfhgffgsgsgshgshgshgshgfshffsfssfsfdhsddfkjhfsd",
            "dkdshfdhfdshdhsdhsfdshdshfdhfsdshfdshffshdfshdhfdsshfdhfsdhsfdhfsdshfdhfsdshfdsdshdhsfdbj",
            "fsjdfsgdgddsbfsdbhjbhfdsjdsjhfhdjfhhfdsvhjfdsvhjdsvhjdsvhvhjvdbjdfbhjvbdhjbhjfdbjfdbvbdjvbxczjbdgfd",
            "fsjdfkjdsfdsvdsvdsvsdvdsvdsvsdvzdsvzdsvzdvzdsbgfdnfdjjhgghnghghchgcnnhcncnhchnccgnhnhhk",
            "dkjfkjchgncghnhcgncnhgnhcgcnnhgghmmhmghmhgmhgmhmmhgmhgmhgmhghmgfdfdsrgrgfbdjk",
            "fsjhsfsgdffgdgdgdffdbbvc vcngfgfgfjhgghfhgffgsgsgshgshgshgshgfshffsfssfsfdhsddfkjhfsd",
            "dkdshfdhfdshdhsdhsfdshdshfdhfsdshfdshffshdxzxzxzxzxxzfshdhfdsshfdhfsdhsfdhfsdshfdhfsdshfdsdshdhsfdbj",
            "fsjhsfsgdffgdgdgdffdbbvc xxzzcxcxzcxzvcngfgfgfjhgghfhgffgsgsgshgshgshgshgfshffsfssfsfdhsddfkjhfsd",
            "dkdshfdhfdshdhsdhsfdshdshfdzxcxzxzcxchfsdshfdshffshdfshdhfdsshfdhfsdhsfdhfsdshfdhfsdshfdsdshdhsfdbj",
            "dkdshfdhfdshdhsdhsfdshdshfdzxcxzxzcxchfsdshfdshffshdfshdhfdsshfdhfsdhsfdhfsdshfdhfsdshfdsdshdhsfdbj")

        heading = arrayOf("fsjdfdgfd",
            "dkjfkjfbds",
            "fsjdfkjdsfhk",
            "dkjfkjfbdjk",
            "fsjdfkjhfsd",
            "dkdsbj",
            "fssjjhfsd",
            "dkjfkjfj",
            "fsjdfkshfdshfdd",
            "dkjfkjffdsjkbfdsbj",
            "fsjhfsd",
            "dkjfkbfdsbj",
            "fsjdsjjhfsd",
            "dkjfkjfdsbj",)

        if(requireArguments().getString("bottone")=="ESERCIZIO"){
            binding.btnAggiungi.setOnClickListener {
                aggiungiEsercizio()
            }
        }else{
            binding.btnAggiungi.setOnClickListener {
                aggiungiProdotto()
            }
        }


        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        //newArrayList = arrayListOf()
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
    }

 */

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

    private fun aggiungiProdotto(){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registrazione_rapida_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_titolo)

        with(builder){
            setTitle("AGGIUNGI UN PRODOTTO PERSONALIZZATO")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var carbo_salva = carbo.text.toString()
                var proteine_salva = proteine.text.toString()
                var grassi_salva = grassi.text.toString()
                var titolo = titolo.text.toString().trim()

            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }


}