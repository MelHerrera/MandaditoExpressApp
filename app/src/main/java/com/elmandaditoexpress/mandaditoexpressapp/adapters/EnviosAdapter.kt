package com.elmandaditoexpress.mandaditoexpressapp.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsFinalizarEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumEstadoDeEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumRoles
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.createDrawable
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.personaRol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnviosAdapter(private var envios:ArrayList<ResponseWsEnvio>, var context: Context): RecyclerView.Adapter<EnviosAdapter.ViewHolder>() {

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

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val estado:TextView = itemView.findViewById(R.id.vEstado)
        private val descripcion: TextView = itemView.findViewById(R.id.vDescripcion)
        private val nombresDelCliente: TextView = itemView.findViewById(R.id.vCliente)
        private val monto: TextView = itemView.findViewById(R.id.vMonto)
        private val motorizado: TextView = itemView.findViewById(R.id.vMotorizado)
        private val motivoRechazo: TextView = itemView.findViewById(R.id.vMotivoRechazo)
        private val fecha: TextView = itemView.findViewById(R.id.vFecha)
        private val finalizar: Button = itemView.findViewById(R.id.vBtnFinalizarEnvio)

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

            if(envio.estado == EnumEstadoDeEnvio.ENPROCESO.valor && personaRol?.name == EnumRoles.Motorizado.name)
                finalizar.visibility = View.VISIBLE

            finalizar.setOnClickListener {
                finalizarEnvioAlert(envio.id)
            }
        }

        fun finalizarEnvioAlert(envioId:Int){
            val mAlert = AlertDialog.Builder(itemView.context as Context)
            mAlert.setMessage("¿Esta seguro(a) que desea finalizar este envío?")
                .setPositiveButton("Si, seguro") { dialogInterface, i ->
                    finalizarEnvio(envioId)
                }
                .setNegativeButton("No, cancelar") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            mAlert.create()
            mAlert.show()
        }

        fun finalizarEnvio(envioId: Int) {
            lateinit var apiResponse: ResponseWsFinalizarEnvio

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    apiResponse = ApiAdapter.apiClient.finalizarEnvio(envioId)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }

                if (apiResponse != null){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, apiResponse.mensaje, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}