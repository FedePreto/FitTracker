package com.example.fittracker.aggiungi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracker.R
import com.google.android.material.imageview.ShapeableImageView
import kotlin.coroutines.coroutineContext

class MyAdapter(private val newsList : ArrayList<News>, val context : Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.layout_item_prodotto,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return newsList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = newsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.headings
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Hai premuto l'item numero " + position.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        val titleImage : ShapeableImageView = itemView.findViewById(R.id.imageProdotto)
        val tvHeading : TextView = itemView.findViewById(R.id.tvNomeProdotto)


    }
}