package com.example.fittracker.aggiungi_esercizio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fittracker.R
import com.example.fittracker.model.Json_Parsing.Esercizio

class MyAdapterRicercaEsercizio(private val eserciziList : ArrayList<Esercizio>): RecyclerView.Adapter<MyAdapterRicercaEsercizio.MyViewHolder>() {

    private  lateinit var mListener: onItemClickListener //Interfaccia che serve per associare un clickListener agli elementi della recycler view
    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_esercizio,parent,false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = eserciziList[position]
        Glide.with(holder.itemView)
            .load("https://cdn.vectorstock.com/i/preview-1x/38/32/square-barbell-icon-vector-5293832.webp")
            .into(holder.image)
        holder.tvNomeEsercizio.text = currentItem.nome
        holder.tvCaloriaOra.text = currentItem.calorieOra.toString()


    }

    override fun getItemCount(): Int {
        return eserciziList.size

    }



    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val image : ImageView = itemView.findViewById(R.id.imageViewEsercizio)
        val tvNomeEsercizio : TextView = itemView.findViewById(R.id.tvNomeEsercizio)
        val tvCaloriaOra : TextView = itemView.findViewById(R.id.tvKcal_h_esercizio)


        init{
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }
}