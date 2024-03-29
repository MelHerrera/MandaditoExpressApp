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
import com.elmandaditoexpress.mandaditoexpressapp.EnviosAsignadoMotorizadoActivity
import com.elmandaditoexpress.mandaditoexpressapp.PagosMotorizadoActivity
import com.elmandaditoexpress.mandaditoexpressapp.R
import com.elmandaditoexpress.mandaditoexpressapp.dto.ResponseWsImagenPersona
import com.elmandaditoexpress.mandaditoexpressapp.network.ApiAdapter
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils
import com.elmandaditoexpress.mandaditoexpressapp.services.Utils.Companion.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MotorizadoMenuFragment(val message:String?) : Fragment() {
    private lateinit var vUserImage: ImageView
    private lateinit var vCardEnvios:CardView
    private lateinit var vCardPagosMotorizados:CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val mView=inflater.inflate(R.layout.fragment_motorizado_menu, container, false)

        vUserImage = mView.findViewById(R.id.vImageUser)
        vCardEnvios = mView.findViewById(R.id.vCardEnviosMotorizado)
        vCardPagosMotorizados = mView.findViewById(R.id.vCardPagosMotorizado)
        mView.findViewById<TextView>(R.id.vTxtSaludo).text = message
        getUserImage()
        setHasOptionsMenu(true)

        vCardEnvios.setOnClickListener{
            startActivity(Intent(context, EnviosAsignadoMotorizadoActivity::class.java))
        }
        vCardPagosMotorizados.setOnClickListener {
            startActivity(Intent(context,PagosMotorizadoActivity::class.java))
        }

        return mView
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