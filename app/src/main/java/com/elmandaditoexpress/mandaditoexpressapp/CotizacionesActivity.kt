package com.elmandaditoexpress.mandaditoexpressapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.adapters.CotizacionesAdapter
import com.elmandaditoexpress.mandaditoexpressapp.databinding.ActivityCotizacionesBinding
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizacion
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsCotizaciones
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsTipoDeServicio
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsTiposDeServicio
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.arrayListValuesOfField
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CotizacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCotizacionesBinding
    private  lateinit var tiposDeServicio: ArrayList<ResponseWsTipoDeServicio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCotizacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cargarTiposDeServicio()
        getCotizacionesDelCliente(-1, Utils.personaId)//Inicialmente cargar todas las cotizaciones

        binding.filtroTipoServicio.setOnClickListener { mostrarFiltroTipoServicio() }
        binding.vSwipeRefresh.setOnRefreshListener {
            binding.vTipoServicioFiltrado.text = getString(R.string.todos)
            getCotizacionesDelCliente(-1, Utils.personaId)//Inicialmente cargar todas las cotizaciones
            binding.vSwipeRefresh.isRefreshing= false
        }
    }

    private fun cargarTiposDeServicio(){
        lateinit var apiResponse: ResponseWsTiposDeServicio

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getTiposDeServicio()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
                }
            }

            if(apiResponse.exito) {
                tiposDeServicio =  ArrayList()//sobrescribir los valores de la lista cada vez que vuelve del server
                tiposDeServicio = apiResponse.tiposDeServicio
                tiposDeServicio.add(0, ResponseWsTipoDeServicio(-1, "Todos", true))
            }
            else
                Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }
    private fun mostrarFiltroTipoServicio() {
        try
        {
            val mAlert = AlertDialog.Builder(this)
            mAlert.setTitle(getString(R.string.seleccione)).setIcon(R.drawable.ic_filter_purple)

            val tiposDeServicioDescripcion = tiposDeServicio.arrayListValuesOfField(ResponseWsTipoDeServicio::descripcion)

            mAlert.setSingleChoiceItems(tiposDeServicioDescripcion.toTypedArray(), -1) { dialogInterface: DialogInterface?, i: Int ->
                getCotizacionesDelCliente(tiposDeServicio[i].id, Utils.personaId)
                binding.vTipoServicioFiltrado.text = tiposDeServicio[i].descripcion
                dialogInterface?.dismiss()
            }
            mAlert.create()
            mAlert.show()
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getCotizacionesDelCliente(tipoDeServicio:Int, clienteId:Int){
        lateinit var apiResponse: ResponseWsCotizaciones

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getCotizacionesClienteTipoDeServicio(tipoDeServicio, clienteId)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
                }
            }

            binding.vCotizacionesEmpty.visibility = when {
                !apiResponse.exito || apiResponse.cotizaciones.size<=0 -> View.VISIBLE
                else-> View.GONE }

            if(apiResponse.exito) {
                mostrarCotizaciones(apiResponse.cotizaciones)
            }
            else
                Toast.makeText(applicationContext, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarCotizaciones(cotizaciones:ArrayList<ResponseWsCotizacion>){
        binding.vRecyclerCotizaciones.layoutManager = FlexboxLayoutManager(applicationContext)
        binding.vRecyclerCotizaciones.adapter = CotizacionesAdapter(cotizaciones)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}