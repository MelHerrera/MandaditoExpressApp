package com.elmandaditoexpress.mandaditoexpressapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.adapters.CreditosAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityCreditosBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCredito
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCreditos
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreditosActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreditosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.vSwipeRefresh.isRefreshing=true
        getCreditosDelCliente(Utils.personaId)//obtener inicialmente los creditos cuando carga la pantalla
        binding.vSwipeRefresh.isRefreshing=false

        binding.vSwipeRefresh.setOnRefreshListener {
            getCreditosDelCliente(Utils.personaId)
            binding.vSwipeRefresh.isRefreshing=false
        }
    }

    private fun getCreditosDelCliente(clienteId:Int) {
        lateinit var apiResponse: ResponseWsCreditos

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getCreditosDelCliente(clienteId)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
                }
            }

            if(!apiResponse.exito || apiResponse.creditos.size<=0)
                binding.vCreditosEmpty.visibility = View.VISIBLE
            else
                binding.vCreditosEmpty.visibility = View.GONE

            if(apiResponse.exito)
                mostrarCreditos(apiResponse.creditos)
            else
                Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarCreditos(creditos:ArrayList<ResponseWsCredito>){
        binding.recyLstCreditos.layoutManager = FlexboxLayoutManager(applicationContext)
        binding.recyLstCreditos.adapter = CreditosAdapter(creditos)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}