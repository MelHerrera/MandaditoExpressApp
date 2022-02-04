package com.elmandaditoexpress.mandaditoexpressapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val mView=inflater.inflate(R.layout.fragment_motorizado_menu, container, false)

        vUserImage = mView.findViewById(R.id.vImageUser)
        mView.findViewById<TextView>(R.id.vTxtSaludo).text = message
        getUserImage()
        /*   setHasOptionsMenu(true)*/

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
}