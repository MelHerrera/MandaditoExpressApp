package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCredito

class CreditosAdapter(private val creditos: ArrayList<ResponseWsCredito>): RecyclerView.Adapter<CreditosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_credito, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(creditos[position])
    }

    override fun getItemCount(): Int {
       return creditos.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val descripcion: TextView = itemView.findViewById(R.id.vDescripcion)
        private val nombresDelCliente: TextView = itemView.findViewById(R.id.vCliente)
        private val fechaInicio: TextView = itemView.findViewById(R.id.vFechaInicio)

        fun bindItem(credito: ResponseWsCredito){
            descripcion.text = credito.descripcion
            nombresDelCliente.text = credito.NombresDelCliente
            fechaInicio.text = credito.fechaDeInicio
        }
    }
}