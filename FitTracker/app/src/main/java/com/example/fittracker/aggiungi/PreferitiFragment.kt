package com.example.fittracker.aggiungi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPreferitiBinding
import com.example.fittracker.databinding.FragmentRicercaBinding
import com.example.fittracker.prodotto.ProdottoActivity


class PreferitiFragment : Fragment() {

    lateinit private var binding: FragmentPreferitiBinding


    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var news : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_preferiti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageId = arrayOf(R.drawable.c,R.drawable.c,R.drawable.c,
            R.drawable.c,R.drawable.c,R.drawable.c,R.drawable.c,
            R.drawable.c,R.drawable.c,R.drawable.c,R.drawable.c,
            R.drawable.c,R.drawable.c,R.drawable.c)

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

        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getUserdata()
    }
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


}