package com.elmandaditoexpress.mandaditoexpressapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elmandaditoexpress.mandaditoexpressapp.R

class MotorizadoMenuFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val mView=inflater.inflate(R.layout.fragment_motorizado_menu, container, false)

        /*   setHasOptionsMenu(true)*/

        return mView
    }
}