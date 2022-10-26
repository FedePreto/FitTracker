package com.example.fittracker.aggiungi

import android.content.Intent
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
import com.example.fittracker.model.Prodotto
import com.example.fittracker.prodotto.ProdottoActivity


class RicercaFragment : Fragment() {

    private lateinit var binding: FragmentRicercaBinding
    private var model = AggiungiViewModel()

    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var foods : ArrayList<Prodotto>
    //private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var news : Array<String>

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
        //searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                model.getFoodFromNameorUPC(query!!,"")
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Toast.makeText(requireContext(),query!!,Toast.LENGTH_SHORT).show()
                return true
            }
        })
/*
        imageId = arrayOf(R.drawable.a,R.drawable.a,R.drawable.a,
            R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,
            R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,
            R.drawable.a,R.drawable.a,R.drawable.a,)

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
            "dkjfkjfdsbj")

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
*/


        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)
        val foodObserver = Observer<ArrayList<Prodotto>>{
            Log.d("Food",model.foodLiveData.value.toString())
            val adapter = MyAdapter(model.foodLiveData.value!!)
            newRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(requireContext(), "Hai cliccato sull'elemento $position", Toast.LENGTH_SHORT).show()
                    val intent = prepareIntent(position)
                    startActivity(intent)
                }
            })
        }
        model.foodLiveData.observe(viewLifecycleOwner,foodObserver)

/*

        val foodObserver = Observer<ArrayList<Prodotto>>{
            Log.d("Food",model.foodLiveData.value.toString())
            val adapter = MyAdapter(model.foodLiveData.value!!)
            newRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {

                    Toast.makeText(requireContext(), "Hai cliccato sull'elemento $position", Toast.LENGTH_SHORT).show()

                   /* val intent = Intent(requireContext(), ProdottoActivity::class.java)
                    intent.putExtra("heading", model.foodLiveData.value!![position].label)
                    intent.putExtra("imageID", model.foodLiveData.value!![position].image)
                    intent.putExtra("news", model.foodLiveData.value!![position].foodContentsLabel)
                    startActivity(intent)

                    */


                }
            })
        }
       model.foodLiveData.observe(viewLifecycleOwner,foodObserver)

 */



    }

    private fun prepareIntent(position: Int) : Intent{
        var intent = Intent(requireContext(), ProdottoActivity::class.java)
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
