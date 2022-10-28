package com.example.fittracker.diete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentDieteBinding
import com.example.fittracker.model.Dieta

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

        imageId = arrayOf(
            R.drawable.c, R.drawable.c, R.drawable.c,
            R.drawable.c, R.drawable.c, R.drawable.c, R.drawable.c,
            R.drawable.c, R.drawable.c, R.drawable.c, R.drawable.c,
            R.drawable.c, R.drawable.c, R.drawable.c
        )

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

        model.getDiete()

        recyclerViewDiete = binding.gridDiete
        recyclerViewDiete.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerViewDiete.setHasFixedSize(true)
        val dietaObserver = Observer<List<Dieta>>{
            val adapter = MyAdapterDiete(model.dieteLiveData.value!! as ArrayList<Dieta>)
            recyclerViewDiete.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterDiete.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(requireContext(),"Hai premuto l'item $position", Toast.LENGTH_LONG).show()
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



}