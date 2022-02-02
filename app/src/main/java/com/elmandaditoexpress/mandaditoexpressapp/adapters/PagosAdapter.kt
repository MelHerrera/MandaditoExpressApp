package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsPago

class PagosAdapter(private val pagos: ArrayList<ResponseWsPago>, val context: Context):RecyclerView.Adapter<PagosAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pago, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pagos[position], context)
    }

    override fun getItemCount(): Int {
        return pagos.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val fechaPago: TextView = itemView.findViewById(R.id.vFechaPago)
        private val descripcion: TextView = itemView.findViewById(R.id.vDescripcion)
        private val nombresDelCliente: TextView = itemView.findViewById(R.id.vCliente)
        private val monto: TextView = itemView.findViewById(R.id.vMonto)

        fun bindItem(pago: ResponseWsPago, context: Context){
            fechaPago.text = pago.fechaDePago
            descripcion.text = context.getString(R.string.descripcionDelPago, pago.conceptoDelPago, pago.envioCodigo ?: pago.creditoCodigo, pago.numeroDePago)
            nombresDelCliente.text = pago.nombresDelCliente
            monto.text = context.getString(R.string.montoDelPago,pago.montoDelPago.toString())
        }
    }
}