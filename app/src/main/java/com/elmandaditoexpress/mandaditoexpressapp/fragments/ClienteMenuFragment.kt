package com.elmandaditoexpress.mandaditoexpressapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.elmandaditoexpress.mandaditoexpressapp.CotizacionesActivity
import com.elmandaditoexpress.mandaditoexpressapp.CreditosActivity
import com.elmandaditoexpress.mandaditoexpressapp.PagosActivity
import com.elmandaditoexpress.mandaditoexpressapp.R

class ClienteMenuFragment(private val message:String?) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val mView=inflater.inflate(R.layout.fragment_cliente_menu, container, false)

        mView.findViewById<TextView>(R.id.vTxtSaludo).text = message
        mView.findViewById<CardView>(R.id.vCardCreditos).setOnClickListener { goToListCreditos() }
        mView.findViewById<CardView>(R.id.vCardPagos).setOnClickListener { goToListPagos() }
        mView.findViewById<CardView>(R.id.vCardCotizaciones).setOnClickListener { goToListCotizaciones() }

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
}