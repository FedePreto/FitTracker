package com.example.fittracker.aggiungi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.example.fittracker.databinding.FragmentPersonalizzatiBinding


class PersonalizzatiFragment : Fragment() {

    lateinit private var binding: FragmentPersonalizzatiBinding


    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>


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

        newRecyclerView.adapter = MyAdapter(newArrayList,requireContext())
    }


}