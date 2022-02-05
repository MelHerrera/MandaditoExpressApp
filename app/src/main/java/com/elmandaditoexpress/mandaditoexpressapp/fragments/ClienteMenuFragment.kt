package com.elmandaditoexpress.mandaditoexpressapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.elmandaditoexpress.mandaditoexpressapp.*
import com.elmandaditoexpress.mandaditoexpressapp.adapters.EnviosAdapter
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsImagenPersona
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClienteMenuFragment(private val message:String?) : Fragment() {
    private lateinit var vUserImage: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val mView=inflater.inflate(R.layout.fragment_cliente_menu, container, false)

        setHasOptionsMenu(true)//Indicar que muestre los 3 puntituos de menu

        vUserImage = mView.findViewById(R.id.vImageUser)
        mView.findViewById<TextView>(R.id.vTxtSaludo).text = message
        mView.findViewById<CardView>(R.id.vCardCreditos).setOnClickListener { goToListCreditos() }
        mView.findViewById<CardView>(R.id.vCardPagos).setOnClickListener { goToListPagos() }
        mView.findViewById<CardView>(R.id.vCardCotizaciones).setOnClickListener { goToListCotizaciones() }
        mView.findViewById<CardView>(R.id.vCardEnvios).setOnClickListener { goToListEnvios() }

        getUserImage()

        return mView
    }

    private fun goToListCreditos(){
        startActivity(Intent(context, CreditosActivity::class.java))
    }
    private fun goToListPagos(){
        startActivity(Intent(context, PagosActivity::class.java))
    }
    private fun goToListCotizaciones(){
        startActivity(Intent(context, CotizacionesActivity::class.java))
    }
    private fun goToListEnvios(){
        startActivity(Intent(context, EnviosActivity::class.java))
    }

    private fun getUserImage(){
        lateinit var apiResponse: ResponseWsImagenPersona

        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                try {
                    apiResponse = ApiAdapter.apiClient.getImagenPerfilPersona(Utils.personaId)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()//creo que se debe cambiar al Dispatcher.Main sino el toast causara una exepcion
                }
            }

            if(apiResponse.exito && apiResponse.imagen != null )
                vUserImage.setImageBitmap(apiResponse.imagen.toBitmap())
            else
                Toast.makeText(context, apiResponse.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.preferencias_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}