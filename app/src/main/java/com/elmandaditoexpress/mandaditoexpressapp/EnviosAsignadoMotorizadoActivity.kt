package com.elmandaditoexpress.mandaditoexpressapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.adapters.EnviosAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityEnviosAsignadoMotorizadoBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvios
import com.elmandaditoexpress.mandaditoexpressapp.dto.enums.EnumEstadoDeEnvio
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.getEnumEstadoDelEnvioValueOf
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.personaId
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class EnviosAsignadoMotorizadoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEnviosAsignadoMotorizadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviosAsignadoMotorizadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getEnviosDelMotorizado(personaId,-1)

        binding.vSwipeRefresh.setOnRefreshListener {
            getEnviosDelMotorizado(personaId, -1)
            binding.vSwipeRefresh.isRefreshing= false
        }
        binding.vFiltrarPorEstado.setOnClickListener {
            mostrarFiltroEstados()
        }
    }

    private fun mostrarFiltroEstados() {
        try
        {
            val mAlert = AlertDialog.Builder(this)
            mAlert.setTitle(getString(R.string.seleccione)).setIcon(R.drawable.ic_filter_purple)

            val estadosDelEnvio:MutableList<String> =  EnumEstadoDeEnvio.values().map { it.name.lowercase(Locale.getDefault()) }.toMutableList()
            val predeterminado = estadosDelEnvio.firstOrNull { it.uppercase(Locale.getDefault()) == EnumEstadoDeEnvio.PREDETERMINADO.name }

            if(!predeterminado.isNullOrEmpty())
                estadosDelEnvio.remove(predeterminado)

            estadosDelEnvio.add(0,"Todos")

            mAlert.setSingleChoiceItems(estadosDelEnvio.toTypedArray(), -1) { dialogInterface: DialogInterface?, i: Int ->
                binding.vEstadoFiltrado.text = estadosDelEnvio[i]
                getEnviosDelMotorizado(personaId, getEnumEstadoDelEnvioValueOf(estadosDelEnvio[i]))
                dialogInterface?.dismiss()
            }
            mAlert.create()
            mAlert.show()
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getEnviosDelMotorizado(motorizadoId:Int, estado:Int){
        lateinit var apiResponse: ResponseWsEnvios

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getEnviosDelMotorizado(motorizadoId, estado)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
            binding.vEnviosEmpty.visibility = when {
                !apiResponse.exito || apiResponse.envios.size<=0 -> View.VISIBLE
                else-> View.GONE }

            if(apiResponse.exito) {
                mostrarEnvios(apiResponse.envios)
            }
            else
                Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarEnvios(envios:ArrayList<ResponseWsEnvio>){
        binding.vRecyclerEnvios.layoutManager = FlexboxLayoutManager(applicationContext)
        binding.vRecyclerEnvios.adapter = EnviosAdapter(envios, applicationContext)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}