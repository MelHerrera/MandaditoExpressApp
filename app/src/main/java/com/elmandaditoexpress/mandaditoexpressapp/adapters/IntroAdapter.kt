package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.Intro
import com.elmandaditoexpress.mandaditoexpressapp.R

class IntroAdapter(private val Intro:List<Intro>): RecyclerView.Adapter<IntroAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(Intro[position])
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_intro,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return  Intro.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name) as TextView
        private val des: TextView = itemView.findViewById(R.id.des) as TextView
        private val foto: ImageView = itemView.findViewById(R.id.foto) as ImageView
        fun bindItem(Intro: Intro){

            name.text = Intro.name
            des.text = Intro.des
            foto.setImageResource(Intro.foto!!)
        }

    }
}