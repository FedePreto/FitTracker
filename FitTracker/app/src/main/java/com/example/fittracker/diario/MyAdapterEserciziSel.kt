package com.example.fittracker.diario

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.model.Json_Parsing.Esercizio
import com.example.fittracker.model.Pasto
import com.google.android.material.imageview.ShapeableImageView

class MyAdapterEserciziSel(private val selezionatiList : ArrayList<Esercizio>): RecyclerView.Adapter<MyAdapterEserciziSel.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_esercizio_pref,parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = selezionatiList[position]
        Glide.with(holder.itemView)
            .load("https://cdn.vectorstock.com/i/preview-1x/38/32/square-barbell-icon-vector-5293832.webp")
            .into(holder.immagine)
        holder.tvNomeEsercizio.text = currentItem.nome

    }

    override fun getItemCount(): Int {
        return selezionatiList.size

    }

    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val immagine : ShapeableImageView = itemView.findViewById(R.id.immagine)
        val tvNomeEsercizio : TextView = itemView.findViewById(R.id.tvNomeEsercizio)



        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}

