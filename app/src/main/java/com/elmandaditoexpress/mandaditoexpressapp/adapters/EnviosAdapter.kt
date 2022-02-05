package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvio
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.createDrawable

class EnviosAdapter(private var envios:ArrayList<ResponseWsEnvio>): RecyclerView.Adapter<EnviosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_envio, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(envios[position])
    }

    override fun getItemCount(): Int {
        return envios.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val estado:TextView = itemView.findViewById(R.id.vEstado)
        private val descripcion: TextView = itemView.findViewById(R.id.vDescripcion)
        private val nombresDelCliente: TextView = itemView.findViewById(R.id.vCliente)
        private val monto: TextView = itemView.findViewById(R.id.vMonto)
        private val motorizado: TextView = itemView.findViewById(R.id.vMotorizado)
        private val motivoRechazo: TextView = itemView.findViewById(R.id.vMotivoRechazo)
        private val fecha: TextView = itemView.findViewById(R.id.vFecha)

        fun bindItem(envio: ResponseWsEnvio){
            val estadoEnvio = Utils.getEnumEstadoDelEnvioValueOf(envio.estado)

            estado.text = estadoEnvio.name
            estado.background = GradientDrawable().createDrawable(Color.parseColor(estadoEnvio.color),20,20,GradientDrawable.RECTANGLE,100f,100f,100f,100f)
            descripcion.text = envio.descripcion
            nombresDelCliente.text = envio.nombresDelCliente
            monto.text = envio.montoTotal.toString()
            motorizado.text = envio.nombresDelMotorizado
            fecha.text = envio.fecha

            if(envio.motivoRechazo != null) {
                motivoRechazo.text = envio.motivoRechazo
                motivoRechazo.visibility = View.VISIBLE
            }
            else
                motivoRechazo.visibility = View.GONE
        }
    }
}