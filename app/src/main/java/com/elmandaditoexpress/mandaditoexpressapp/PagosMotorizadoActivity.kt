package com.elmandaditoexpress.mandaditoexpressapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.adapters.PagosAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityPagosBinding
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityPagosMotorizadoBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsPago
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsPagos
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PagosMotorizadoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPagosMotorizadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagosMotorizadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.vSwipeRefresh.isRefreshing=true
        getPagosDelMotorizado(Utils.personaId)//obtener inicialmente los pagos cuando carga la pantalla
        binding.vSwipeRefresh.isRefreshing=false

        binding.vSwipeRefresh.setOnRefreshListener {
            getPagosDelMotorizado(Utils.personaId)
            binding.vSwipeRefresh.isRefreshing=false
        }
    }

    fun getPagosDelMotorizado(motorizadoId:Int){
        lateinit var apiResponse: ResponseWsPagos

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getPagosDelMotorizado(motorizadoId)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
                }
            }

            if(!apiResponse.exito || apiResponse.pagos.size<=0)
                binding.vPagosEmpty.visibility = View.VISIBLE
            else
                binding.vPagosEmpty.visibility = View.GONE

            if(apiResponse.exito)
                mostrarPagos(apiResponse.pagos)
            else
                Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarPagos(pagos:ArrayList<ResponseWsPago>){
        binding.vRecyclerPagos.layoutManager = FlexboxLayoutManager(applicationContext)
        binding.vRecyclerPagos.adapter = PagosAdapter(pagos, applicationContext)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}