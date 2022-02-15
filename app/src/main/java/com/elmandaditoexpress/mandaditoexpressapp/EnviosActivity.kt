package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.adapters.CotizacionesAdapter
import com.elmandaditoexpress.mandaditoexpressapp.adapters.EnviosAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityEnviosBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizacion
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizaciones
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvio
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsEnvios
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnviosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnviosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getEnviosDelCliente(Utils.personaId)

        binding.vSwipeRefresh.setOnRefreshListener {
            getEnviosDelCliente(Utils.personaId)
            binding.vSwipeRefresh.isRefreshing= false
        }
    }

    private fun getEnviosDelCliente(clienteId:Int){
        lateinit var apiResponse: ResponseWsEnvios

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getEnviosDelCliente(clienteId)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
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