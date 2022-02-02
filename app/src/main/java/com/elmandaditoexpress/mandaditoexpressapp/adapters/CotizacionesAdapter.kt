package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizacion

class CotizacionesAdapter(private var cotizaciones:ArrayList<ResponseWsCotizacion>): RecyclerView.Adapter<CotizacionesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cotizacion, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(cotizaciones[position])
    }

    override fun getItemCount(): Int {
       return cotizaciones.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val fechaCotizacion: TextView = itemView.findViewById(R.id.vFechaCotizacion)
        private val descripcion: TextView = itemView.findViewById(R.id.vDescripcion)
        private val nombresDelCliente: TextView = itemView.findViewById(R.id.vCliente)
        private val montoCotizacion: TextView = itemView.findViewById(R.id.vMonto)
        private val fechaValidez: TextView = itemView.findViewById(R.id.vFechaValidez)

        fun bindItem(cotizacion: ResponseWsCotizacion){
            fechaCotizacion.text = cotizacion.fecha
            descripcion.text = cotizacion.descripcion
            nombresDelCliente.text = cotizacion.nombresDelCliente
            montoCotizacion.text = cotizacion.montoTotal.toString()
            fechaValidez.text = cotizacion.fechaDeValidez
        }
    }
}